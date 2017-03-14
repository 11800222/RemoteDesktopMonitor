package overall;

import java.io.File;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.HashMap;

import javax.swing.UIManager;

import services.IsPortUsing;
import thread.GetFileFromEveryone;
import thread.HeardEveyone;
import view.ClientButton;
import view.SignInGuiForSever;
import DAO.Account;
import DAO.DBproxy;
import Rmi.MyRemote;
import Rmi.MyServerImpl;

public class BuildServer extends Builder {

	public void buildGUI() throws Exception {

		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		overall.signgui = new SignInGuiForSever();
		overall.signgui.setVisible(true);

	}

	public void buildChatRoom() throws Exception {
		if (IsPortUsing.isLoclePortUsing(5111))
			throw new Exception();
		HeardEveyone he = new HeardEveyone();
		he.setup();
		Thread t = new Thread(he);
		t.start();

	}

	public void buildFileTransfer() throws Exception {

		if (IsPortUsing.isLoclePortUsing(6216))
			throw new Exception();
		GetFileFromEveryone gf = new GetFileFromEveryone();
		gf.setup();
		Thread t1 = new Thread(gf);
		t1.start();

	}

	public void buildRmi() throws Exception {

		if (IsPortUsing.isLoclePortUsing(1099))
			throw new Exception();
		java.rmi.registry.LocateRegistry.createRegistry(1099);
		MyRemote service = new MyServerImpl();
		Naming.rebind("ServerMethod", service); // 以Rmi方式运行代码注册远程服务

	}

	public void buildarguments() throws Exception {

		overall.path = System.getProperty("user.dir").replace('\\', '/');
		overall.filepath = "ServerFile";
		overall.user = new Account();
		overall.user.setIp(InetAddress.getLocalHost().toString().split("/")[1]);
		overall.DBp = new DBproxy();
		overall.ClientButtons = new HashMap<String, ClientButton>();
		File f = new File(overall.filepath);
		if (!f.exists()) {
			f.mkdir();
		}

	}
}
