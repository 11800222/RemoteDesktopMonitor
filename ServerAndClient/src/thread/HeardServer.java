package thread;

import java.io.*;
import java.net.*;

import overall.End;



public class HeardServer implements Runnable {//连接到服务器的类
public static Socket serverSock;//连接到服务器接收信息端的Socket

public void setup(){
	try {
		serverSock = new Socket(End.Severhost, 5111);// 连接到服务器
		
		InputStreamReader isReader = new InputStreamReader(serverSock.getInputStream());
		End.reader = new BufferedReader(isReader);
		End.isWriter=new PrintWriter(serverSock.getOutputStream());// 要广播到所有客户端的信息
        End.MsgSocket=serverSock;
        System.out.println("msgsocket IP:"+serverSock.getInetAddress());
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void run() {
	      try {
				String message=new String();
				while ((message =End.reader.readLine()) != null) {
				//接收到的消息	
					//System.out.println("received " + message);
				   End.gui.textArea.append(message+"\n");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
	}
}
/*
class ListenMessage implements Runnable {//监听来自服务器的消息的线程类 (已经连接到服务器）
		Socket sock;// 该线程监听的服务器的Socket
		public ListenMessage(Socket sock){
			this.sock=sock;
		}
		public void run() {//监听的方法
			String message;
			try {
				
				BufferedReader reader = new BufferedReader(isReader);
				while ((message = reader.readLine()) != null) {
					System.out.println("received " + message);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
*/

