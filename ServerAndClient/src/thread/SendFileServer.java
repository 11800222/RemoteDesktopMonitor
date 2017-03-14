package thread;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;

import javax.swing.JOptionPane;

import Rmi.MyClientRemote;
import Rmi.MyRemote;
import overall.End;

//只新建一个对象，重复调用run方法。
public class SendFileServer implements Runnable {
	// 专门传输数据到服务端的文件接受端的线程类。
	public static boolean SendFileDone = true;
	// 避免上一次文件还未发送给服务端完毕，就发送下一个文件给服务端。
	public File file;
	public String filename;
	public Socket socket;


	public SendFileServer() {
		try {
			socket = GetFileFromServer.serverSock;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 设定接下来要发送的文件路径和文件名
	public void setFile(File file, String filename) {
		this.file = file;
		this.filename = filename;

	}

	public void run() {
		try {
			// 告诉远程服务端的接受线程接下来要传输的文件的大小和文件的名字；
			boolean finished = End.serice.sendFile(End.user.getIp(),
					this.file.length(), this.filename);
           
			if (finished) {
				int length = 0;
				byte[] sendBytes = null;
				FileInputStream fis = new FileInputStream(file);
				sendBytes = new byte[1024];
				while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
					End.dos.write(sendBytes, 0, length);
					End.dos.flush();
				}
				JOptionPane.showMessageDialog(null, "已发送文件", "提示",JOptionPane.PLAIN_MESSAGE);  
				
			}
		} catch (Exception e) {
			System.out.println("客户端文件传输异常");
			JOptionPane.showMessageDialog(null, "客户端文件传输异常", "提示",JOptionPane.PLAIN_MESSAGE);  
			e.printStackTrace();
		}

	}
}
