package services;

import java.net.Socket;

import overall.End;
import view.ClientButton;

public class Disconnect {
public static void disconnect(String ip){
	//ɾ��ClientSocketsForFile��ClientSocketsForMsg ��ÿͻ��˵�Socket��
	// ��ȻSendFileEveryone��TellEveryone���ڹ㲥��ʱ���׳��쳣������Ȼ�׳�����Ȼ�ܼ������У�
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
	//���ɾ����ť
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
