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

public class GetFileFromEveryone implements Runnable {// 与客户端连接的类（监听客户端连接请求）
	public static ServerSocket serverSock;// 服务器监听文件传输的监听Socket
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
						.substring(1)+"已连接到文件端口");
				
				// 把连接到客户端文件Socket连接到该客户端的按	钮里
				Thread t = new Thread(
						new ListenFile(ClientSocket, clientbutton));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ListenFile implements Runnable {// 监听来自客户端的文件的线程类(已经连接到客户端），一个客户端一个线程（对象）
	Socket socket;// 该线程监听的客户端的Socket
	ClientButton clientbuton;// 该线程监听的客户端的按钮

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
				// 接受完毕之后关闭文件输出流，重置计数器sum
				if (sum == this.clientbuton.filelegth) {
					this.clientbuton.fos.close();
					sum = 0;
					System.out.println("接受到一个文件");
				}
			}

		} catch (SocketException s) {
			// 客户端断开连接时的处理
			//s.printStackTrace();
			System.out.println("客户端断开文件连接");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// run结束

}
