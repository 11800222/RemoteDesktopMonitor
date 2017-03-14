package thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import overall.End;
import view.ClientButton;

public class HeardEveyone implements Runnable {

	public static ServerSocket serverSock;// ���촰�ڵļ����˿�

	public void setup() {
		try {
			serverSock = new ServerSocket(5111);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			while (true) {
				Socket clientSocket = serverSock.accept();
				
				ClientButton clientbuton = End.ClientButtons
						.get(clientSocket.getInetAddress().toString()
								.substring(1));
				System.out.println(clientSocket.getInetAddress().toString()
						.substring(1)+"�����ӵ���Ϣ�˿�");
				clientbuton.MsgSocket = clientSocket;
				clientbuton.writer = new PrintWriter(
						clientSocket.getOutputStream());

				InputStreamReader isReader = new InputStreamReader(
						clientSocket.getInputStream());
				clientbuton.reader = new BufferedReader(isReader);

				// ����clientbutton�Ϳ���
				Thread t = new Thread(new ListenMessage(clientbuton));
				t.start();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class ListenMessage implements Runnable {// �������Կͻ��˵���Ϣ���߳���(�Ѿ����ӵ��ͻ��ˣ���һ���ͻ���һ���̣߳�����
	BufferedReader reader;

	public ListenMessage(ClientButton clientbutton) {
		try {
			reader = clientbutton.reader;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {// ��ʼ�����ض�ĳ���ͻ��˵�����˿�
				// System.out.println("read " + message);//������ͻ��˽��յ��Ĺ㲥����Ϣ
				// ���ﲻ�̣߳������Ѿ����߳��� �����߳̿��ܾͻ�����ȶ�������Ϣ��û���ȥ���������Ϣ���ܾͱ����ȥ
				// �ڵ�����Ͱ�ťʱ��ʹ���̷߳�����
				/*
				 * Thread t = new Thread(new TellEveryone(message)); t.start();
				 */

				End.sgui.textArea.append(message+"\n");
				TellEveryone t = new TellEveryone(message);
				t.run();
			}
			System.out.println("----");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("�ͻ��˶Ͽ���Ϣ����");
		}
	}
}
