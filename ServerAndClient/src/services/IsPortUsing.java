package services;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class IsPortUsing {
	public static boolean isLoclePortUsing(int port){  
        boolean flag = true;  
        try {  
            flag = isPortUsing("127.0.0.1", port);  
        } catch (Exception e) {  
        }  
        return flag;  
    }  
	public static boolean isPortUsing(String host,int port) throws UnknownHostException{  
        boolean flag = false;  
        InetAddress theAddress = InetAddress.getByName(host);  
        try {  
            Socket socket = new Socket(theAddress,port);  
            flag = true;  
        } catch (IOException e) {  
              
        }  
        return flag;  
    }  
}  

