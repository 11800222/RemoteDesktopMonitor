package thread;

import java.io.PrintWriter;
import java.net.Socket;

import overall.End;

public class TellServer implements Runnable {
    public static boolean SendMsgDone=true;//�����ȵ�����Ͱ�ť���͵���Ϣ���ͳ�ȥ
	public String message;

	public TellServer(String message) {
		this.message = message;
	}
	public void run() {
		try {
			PrintWriter writer = End.isWriter;
			writer.println(message);
			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
