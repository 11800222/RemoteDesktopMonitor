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

	public static ServerSocket serverSock;// 聊天窗口的监听端口

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
						.substring(1)+"已连接到消息端口");
				clientbuton.MsgSocket = clientSocket;
				clientbuton.writer = new PrintWriter(
						clientSocket.getOutputStream());

				InputStreamReader isReader = new InputStreamReader(
						clientSocket.getInputStream());
				clientbuton.reader = new BufferedReader(isReader);

				// 传入clientbutton就可以
				Thread t = new Thread(new ListenMessage(clientbuton));
				t.start();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class ListenMessage implements Runnable {// 监听来自客户端的消息的线程类(已经连接到客户端），一个客户端一个线程（对象）
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
			while ((message = reader.readLine()) != null) {// 开始监听特定某个客户端的聊天端口
				// System.out.println("read " + message);//从这个客户端接收到的广播的信息
				// 这里不线程，本来已经在线程内 ，再线程可能就会出现先读到的消息还没输出去，后读的消息可能就被输出去
				// 在点击发送按钮时再使用线程方法。
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
			System.out.println("客户端断开消息连接");
		}
	}
}
