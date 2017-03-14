package thread;

import java.io.PrintWriter;
import java.net.Socket;

import overall.End;

public class TellServer implements Runnable {
    public static boolean SendMsgDone=true;//避免先点击发送按钮发送的信息后发送出去
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
