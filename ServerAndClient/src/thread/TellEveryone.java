package thread;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

import overall.End;
import view.ClientButton;

public class TellEveryone implements Runnable{
    public static boolean sendMsgDone=true;
	public String message;
	public TellEveryone(String message) {
		this.message = message;
	}
	public void run() {
		 Iterator<Map.Entry<String, ClientButton>> it = End.ClientButtons.entrySet().iterator();
	        while (it.hasNext()) {
	            try {
	            	Map.Entry<String, ClientButton> entry = it.next();
	            	Socket socket=entry.getValue().MsgSocket;
	            	PrintWriter writer=entry.getValue().writer;
	            	
	            	
				writer.println(message);
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
