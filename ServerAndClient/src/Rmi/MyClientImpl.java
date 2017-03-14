package Rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Rmi.MyClientRemote;
import overall.End;
import services.ScreenCapture;
import services.Setfos;

public class MyClientImpl extends UnicastRemoteObject implements MyClientRemote {// 要继承UnicastRemoteObject
	public MyClientImpl() throws RemoteException {// 要处理父类构造函数抛出的异常
		super();
	}

	public boolean Shutdown() throws RemoteException {
	boolean flag=true;
	try{
		Runtime.getRuntime().exec("shutdown -s -t 1");  
	}catch(Exception e){
		flag=false;
	}
		return flag;
	}

	public Icon Cap() throws RemoteException {
		return  ScreenCapture.Cap();
	}

	public boolean ClientsendFile(long length, String filename)
			throws RemoteException {
		try {
			End.filelength = length;
			Setfos.setfos(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}