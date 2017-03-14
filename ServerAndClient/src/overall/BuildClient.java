package overall;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.UIManager;

import Rmi.MyClientImpl;
import Rmi.MyClientRemote;
import services.IsPortUsing;
import thread.GetFileFromServer;
import thread.HeardServer;
import view.SignInGuiForClient;

public class BuildClient  extends Builder{


	public void buildGUI() throws Exception {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		End.sg=new SignInGuiForClient();
		End.sg.setVisible(true);
		while(End.gui==null)
		{
		Thread.sleep(1000);
		}
	}

	
	public void buildChatRoom() throws Exception {
		HeardServer hs = new HeardServer();
		hs.setup();
		Thread t = new Thread(hs);
		t.start();

	}


	public void buildFileTransfer() throws Exception {
		GetFileFromServer gf = new GetFileFromServer();
		gf.setup();
		Thread t1 = new Thread(gf);
		t1.start();
	}

	
	public void buildRmi() throws Exception {
		if (IsPortUsing.isLoclePortUsing(1098))
			throw new Exception();
		MyClientRemote service=new MyClientImpl();
		Registry r = LocateRegistry.createRegistry(8988); 
	    r.bind("ClientMethod",service); 
	}

	
	public void buildarguments() throws Exception {
		ServerSocket ss = new ServerSocket(2009);
		Socket so = new Socket("127.0.0.1", 2009);
		End.Localhost = so.getInetAddress().toString();
		ss.close();
		so.close();
		End.path = System.getProperty("user.dir").replace('\\', '/');
	    File file=new File("ClientFile");
	     if(!file.exists()){
	    	 file.mkdir();
	     }
	}

}
