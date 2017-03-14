package Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import DAO.Account;

public interface MyRemote extends Remote {
	public Account connect(String IP, String account)  throws RemoteException;
	public boolean SignIn(String accountname, String password) throws RemoteException;
	public boolean sendFile(String socketsIP,long length, String filename)throws RemoteException;
	public boolean disconnect(String IP)  throws RemoteException;
}