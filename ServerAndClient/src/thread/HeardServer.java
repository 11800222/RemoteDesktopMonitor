package thread;

import java.io.*;
import java.net.*;

import overall.End;



public class HeardServer implements Runnable {//���ӵ�����������
public static Socket serverSock;//���ӵ�������������Ϣ�˵�Socket

public void setup(){
	try {
		serverSock = new Socket(End.Severhost, 5111);// ���ӵ�������
		
		InputStreamReader isReader = new InputStreamReader(serverSock.getInputStream());
		End.reader = new BufferedReader(isReader);
		End.isWriter=new PrintWriter(serverSock.getOutputStream());// Ҫ�㲥�����пͻ��˵���Ϣ
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
				//���յ�����Ϣ	
					//System.out.println("received " + message);
				   End.gui.textArea.append(message+"\n");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
	}
}
/*
class ListenMessage implements Runnable {//�������Է���������Ϣ���߳��� (�Ѿ����ӵ���������
		Socket sock;// ���̼߳����ķ�������Socket
		public ListenMessage(Socket sock){
			this.sock=sock;
		}
		public void run() {//�����ķ���
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

