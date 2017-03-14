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

public class GetFileFromServer implements Runnable {// ���������ӵ��ࣨ�������ӷ���ˣ�
	public static Socket serverSock;// ���ӵ��ķ������Ľ����ļ��Ķ˿�

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
						//�������֮��ر��ļ�����������ü�����sum
				if(sum==End.filelength){
					End.fos.close();
					sum=0;	
					JOptionPane.showMessageDialog(null, "�ӷ���˽��ܵ��ļ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);  
					
				}
			}
		}catch(SocketException s){
			//�ͻ��˶Ͽ�����ʱ�Ĵ���
			s.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


