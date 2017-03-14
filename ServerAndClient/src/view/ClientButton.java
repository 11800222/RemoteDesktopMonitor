package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Rmi.MyClientRemote;
import DAO.Account;

public class ClientButton extends JButton {
	public Account account;
	public ButtonObserable bo;
	//
	public Socket FileSocket;
	public Socket MsgSocket;
	//
	public DataOutputStream dos;// ����FileSocketʱ˳������dos����������ļ�����
	public DataInputStream dis;// ����FileSocketʱ˳������fos�����ڶ�ȡ���������ļ�
	public FileOutputStream fos;// ÿ�οͻ���Ҫ�����ļ���ʱ������һ��
	//
	public PrintWriter writer;// ����MsgSocketʱ˳������writer
	public BufferedReader reader;// ����MsgSocketʱ˳������writer

	// �ͻ���Ҫ������ļ�����ϢҲ�ڰ�ť��
	public long filelegth = 0;
	// Զ�̷���	
	public MyClientRemote serice;

	public ClientButton(String s, Account account,Icon icon) {
		super(s,icon);
		this.account = account;
	}

	public void setfos(String filename) {
		try {
			File file = new File("C:\\Server\\" + filename);
			this.fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
