package services;

import java.net.Socket;

import overall.End;
import view.ClientButton;

public class Disconnect {
public static void disconnect(String ip){
	//删掉ClientSocketsForFile和ClientSocketsForMsg 里该客户端的Socket；
	// 不然SendFileEveryone和TellEveryone会在广播的时候抛出异常。（虽然抛出后仍然能继续运行）
	/*
	for(Socket sock : Overall.ClientSocketsForFile){
		if(sock.getInetAddress().toString().equals("/"+ip)){
			Overall.ClientSocketsForFile.remove(sock);
			break;
		}
	}*/
//	Overall.ClientSocketsForFile.remove(Overall.ClientSocketsForFile.get(ip));
	/*for(Socket sock : Overall. ClientSocketsForMsg){
		if(sock.getInetAddress().toString().equals("/"+ip)){
			Overall.ClientSocketsForMsg.remove(sock);
			break;
		}
	}*/
//	Overall.ClientSocketsForFile.remove(Overall.ClientSocketsForMsg.get(ip));
	//最后删掉按钮
/*	ClientButton thebutton=null;
	for(int i=0;i<Overall.ClientButtons.size();++i)
	{
		if(Overall.ClientButtons.get(i).account.getIp()==ip){
			thebutton=Overall.ClientButtons.get(i);
			break;
		}
	}*/
	ClientButton thebutton=End.ClientButtons.get(ip);
	End.buttonControler.removebutton(thebutton);
	
}
}
