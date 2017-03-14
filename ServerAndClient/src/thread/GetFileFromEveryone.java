package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import overall.End;
import view.ClientButton;

public class GetFileFromEveryone implements Runnable {// ��ͻ������ӵ��ࣨ�����ͻ�����������
	public static ServerSocket serverSock;// �����������ļ�����ļ���Socket
	public static int flag = 0;

	public void setup() {
		try {
			serverSock = new ServerSocket(6216);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				Socket ClientSocket = serverSock.accept();
				ClientButton clientbutton = End.ClientButtons
						.get(ClientSocket.getInetAddress().toString()
								.substring(1));
				clientbutton.FileSocket = ClientSocket;
				clientbutton.dos = new DataOutputStream(ClientSocket
						.getOutputStream());
				clientbutton.dis = new DataInputStream(ClientSocket
						.getInputStream());
				System.out.println(ClientSocket.getInetAddress().toString()
						.substring(1)+"�����ӵ��ļ��˿�");
				
				// �����ӵ��ͻ����ļ�Socket���ӵ��ÿͻ��˵İ�	ť��
				Thread t = new Thread(
						new ListenFile(ClientSocket, clientbutton));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ListenFile implements Runnable {// �������Կͻ��˵��ļ����߳���(�Ѿ����ӵ��ͻ��ˣ���һ���ͻ���һ���̣߳�����
	Socket socket;// ���̼߳����Ŀͻ��˵�Socket
	ClientButton clientbuton;// ���̼߳����Ŀͻ��˵İ�ť

	public ListenFile(Socket clientSocket, ClientButton clientbutton) {
		this.socket = clientSocket;
		this.clientbuton = clientbutton;
	}

	public void run() {
		byte[] inputByte = null;
		int length = 0;
		long sum = 0;
		inputByte = new byte[1024];
		try {
			while ((length = this.clientbuton.dis.read(inputByte, 0,
					inputByte.length)) > 0) {

				sum += length;
				this.clientbuton.fos.write(inputByte, 0, length);
				this.clientbuton.fos.flush();
				// �������֮��ر��ļ�����������ü�����sum
				if (sum == this.clientbuton.filelegth) {
					this.clientbuton.fos.close();
					sum = 0;
					System.out.println("���ܵ�һ���ļ�");
				}
			}

		} catch (SocketException s) {
			// �ͻ��˶Ͽ�����ʱ�Ĵ���
			//s.printStackTrace();
			System.out.println("�ͻ��˶Ͽ��ļ�����");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// run����

}
