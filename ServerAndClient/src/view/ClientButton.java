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
	public DataOutputStream dos;// 设置FileSocket时顺便设置dos，用于输出文件数据
	public DataInputStream dis;// 设置FileSocket时顺便设置fos；用于读取传过来的文件
	public FileOutputStream fos;// 每次客户端要传输文件的时候都设置一次
	//
	public PrintWriter writer;// 设置MsgSocket时顺便设置writer
	public BufferedReader reader;// 设置MsgSocket时顺便设置writer

	// 客户端要传输的文件的信息也在按钮里
	public long filelegth = 0;
	// 远程方法	
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
