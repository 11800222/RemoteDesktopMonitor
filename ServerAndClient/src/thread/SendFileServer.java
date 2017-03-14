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

//ֻ�½�һ�������ظ�����run������
public class SendFileServer implements Runnable {
	// ר�Ŵ������ݵ�����˵��ļ����ܶ˵��߳��ࡣ
	public static boolean SendFileDone = true;
	// ������һ���ļ���δ���͸��������ϣ��ͷ�����һ���ļ�������ˡ�
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
	// �趨������Ҫ���͵��ļ�·�����ļ���
	public void setFile(File file, String filename) {
		this.file = file;
		this.filename = filename;

	}

	public void run() {
		try {
			// ����Զ�̷���˵Ľ����߳̽�����Ҫ������ļ��Ĵ�С���ļ������֣�
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
				JOptionPane.showMessageDialog(null, "�ѷ����ļ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);  
				
			}
		} catch (Exception e) {
			System.out.println("�ͻ����ļ������쳣");
			JOptionPane.showMessageDialog(null, "�ͻ����ļ������쳣", "��ʾ",JOptionPane.PLAIN_MESSAGE);  
			e.printStackTrace();
		}

	}
}
