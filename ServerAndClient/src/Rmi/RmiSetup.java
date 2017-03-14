package Rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import services.IsPortUsing;


public class RmiSetup {
public static void setup() throws RemoteException {
	try{
	//	java.rmi.registry.LocateRegistry.createRegistry(1098);
	  
	//	Naming.rebind("ClientMethod",service);      //以Rmi方式运行代码注册远程服务
		MyClientRemote service=new MyClientImpl();
		Registry r = LocateRegistry.createRegistry(8988); 
	    r.bind("ClientMethod",service); 
	}catch(Exception e){
		e.printStackTrace();
	} 
}
}