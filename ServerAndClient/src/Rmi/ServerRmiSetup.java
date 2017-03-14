package Rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

import services.IsPortUsing;


public class ServerRmiSetup {
public static void setup() throws RemoteException {
	try{
		java.rmi.registry.LocateRegistry.createRegistry(1099);
		MyRemote service=new MyServerImpl();  
		Naming.rebind("ServerMethod",service);      //��Rmi��ʽ���д���ע��Զ�̷���
		
	}catch(Exception e){
		e.printStackTrace();
	} 
}
}
