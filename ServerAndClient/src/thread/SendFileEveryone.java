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
	// 避免上一次文件还未发送给所有客户端完毕，就发送下一个文件给所有客户端。
	// 虽然不一定要发送给"所有"客户端完毕，因为某部分已完成上一次文件接受的客户端可以马上接收第二次，
	// 但这样对连上次文件都还没有接受的客户端不公平，他们可能迟迟不能接受到上次文件。
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
				// 告诉远程客户端的接受线程接下来要传输的文件的大小和文件的名字；
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
				JOptionPane.showMessageDialog(null, "文件传输异常", "提示",JOptionPane.PLAIN_MESSAGE);  
					e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "文件传输完成", "提示",JOptionPane.PLAIN_MESSAGE);  
		
	}

}
