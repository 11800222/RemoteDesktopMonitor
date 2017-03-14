package thread;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import Rmi.MyClientRemote;
import Rmi.MyRemote;
import overall.End;
import view.ClientButton;

public class SendFileEveryone implements Runnable {
	public static boolean SendFileDone = true;
	// ������һ���ļ���δ���͸����пͻ�����ϣ��ͷ�����һ���ļ������пͻ��ˡ�
	// ��Ȼ��һ��Ҫ���͸�"����"�ͻ�����ϣ���Ϊĳ�����������һ���ļ����ܵĿͻ��˿������Ͻ��յڶ��Σ�
	// �����������ϴ��ļ�����û�н��ܵĿͻ��˲���ƽ�����ǿ��ܳٳٲ��ܽ��ܵ��ϴ��ļ���
	public File file;
	public String filename;

	public void setFile(File file, String filename) {
		this.file = file;
		this.filename = filename;
	}

	public void run() {
		Iterator<Map.Entry<String, ClientButton>> it = End.ClientButtons
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ClientButton> entry = it.next();
			String ip = entry.getKey();
			try {
				// ����Զ�̿ͻ��˵Ľ����߳̽�����Ҫ������ļ��Ĵ�С���ļ������֣�
				boolean fin=entry.getValue().serice.ClientsendFile(this.file.length(),this.filename);

				if (fin) {
					int length = 0;
					byte[] sendBytes = null;
					FileInputStream fis = new FileInputStream(file);
					sendBytes = new byte[1024];
					while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
						entry.getValue().dos.write(sendBytes, 0, length);
						entry.getValue().dos.flush();
					}
				}
				else {
					throw new Exception();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "�ļ������쳣", "��ʾ",JOptionPane.PLAIN_MESSAGE);  
					e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "�ļ��������", "��ʾ",JOptionPane.PLAIN_MESSAGE);  
		
	}

}
