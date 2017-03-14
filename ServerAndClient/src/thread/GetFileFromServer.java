package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import javax.swing.JOptionPane;

import overall.End;

public class GetFileFromServer implements Runnable {// 与服务端连接的类（请求连接服务端）
	public static Socket serverSock;// 连接到的服务器的接收文件的端口

	public void setup() {
		try {
			serverSock = new Socket(End.Severhost, 6216);
			End.dos=new DataOutputStream(serverSock.getOutputStream());
			End.dis=new DataInputStream(serverSock.getInputStream());
		    End.FileSocket=serverSock;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		byte[] inputByte = null;
		int length = 0;
		long sum =0;
			inputByte = new byte[1024];
			try{
			while ((length = End.dis.read(inputByte, 0, inputByte.length)) > 0) {
				
				sum+=length;		
				End.fos.write(inputByte, 0, length);
				End.fos.flush();
						//接受完毕之后关闭文件输出流，重置计数器sum
				if(sum==End.filelength){
					End.fos.close();
					sum=0;	
					JOptionPane.showMessageDialog(null, "从服务端接受到文件", "提示",JOptionPane.PLAIN_MESSAGE);  
					
				}
			}
		}catch(SocketException s){
			//客户端断开连接时的处理
			s.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


