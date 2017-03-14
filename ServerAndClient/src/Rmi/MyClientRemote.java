package Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public interface MyClientRemote extends Remote {
     public boolean Shutdown() throws RemoteException;
     public Icon Cap() throws RemoteException;
     public boolean ClientsendFile(long length, String filename)throws RemoteException;
}