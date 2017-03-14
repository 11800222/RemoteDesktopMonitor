package Rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import DAO.Account;
import overall.End;
import services.SignIn;
import view.ButtonObserable;
import view.CBListener;
import view.ClientButton;

public class MyServerImpl extends UnicastRemoteObject implements MyRemote {// Ҫ�̳�UnicastRemoteObject
	public MyServerImpl() throws RemoteException {// Ҫ�����๹�캯���׳����쳣
		super();
	}

	// ����
	public Account connect(String IP, String account) throws RemoteException {
		Account Clientaccount = new Account();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		Clientaccount.setLoginInTime(df.format(new Date()));// new
															// Date()Ϊ��ȡ��ǰϵͳʱ��
		try {
			End.DBp.setAccount(account, Clientaccount);
			Clientaccount.setIp(IP);// ���¿ͻ��˵��˺ŵ�IP

			String path = "3.png";
			Icon icon = new ImageIcon(path);

			ClientButton clientbutton = new ClientButton(IP.split("\\.")[3],
					Clientaccount, icon);
			clientbutton.addActionListener(new CBListener());
			String ip = clientbutton.account.getIp();// �������ӵ�Զ��rmi����
			clientbutton.serice = (MyClientRemote) Naming.lookup("rmi://" + ip
					+ ":8988" + "/ClientMethod");
			// �Ѱ�ť�۲��߷Ž���ť����ť����̳���JBUTTON���ܼ̳�Observable��
			clientbutton.bo = new ButtonObserable();
			clientbutton.bo.addObserver(End.buttonControler);

			End.buttonControler.addbutton(clientbutton);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Clientaccount;
	}

	// �ʺ�������֤
	public boolean SignIn(String accountname, String password)
			throws RemoteException {
		boolean flag = false;
		flag = SignIn.sign(accountname, password);
		return flag;
	}

	public boolean sendFile(String socketIP, long length, String filename)
			throws RemoteException {
		try {
			ClientButton cb = End.ClientButtons.get(socketIP);
			if (cb == null)
				throw new Exception();
			cb.filelegth = length;
			cb.setfos(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean disconnect(String IP) throws RemoteException {
		boolean flag = false;
		try {
			ClientButton cb = End.ClientButtons.get(IP);
			cb.bo.notifyObservers(cb);
			/*
			 * cb.FileSocket.close(); cb.MsgSocket.close();
			 * End.buttonControler.removebutton(cb);
			 * End.ClientButtons.remove(cb);
			 */
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}