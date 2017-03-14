package overall;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import services.ButtonControler;
import services.IsPortUsing;
import view.ClientButton;
import view.MainGuiForClient;
import view.MainGuiForSever;
import view.SignInGuiForClient;
import view.SignInGuiForSever;
import DAO.Account;
import DAO.DBproxy;
import Rmi.MyRemote;
import Rmi.ServerRmiSetup;

public class End  {
	public static String path;
	public static String filepath;
	public static HashMap<String, ClientButton> ClientButtons;// �����С����ڡ����ӵĿͻ����˻����ͻ���IPҲ������,
	public static DBproxy DBp;
	public static Account user;// ��SignIn����г�ʼ��������IPҲ������
	public static ButtonControler buttonControler;
	public static MainGuiForSever sgui;
	public static SignInGuiForSever signgui;

	//
	//public static String path;
	public static String Severhost;// ���¡����ڡ����ӵķ����
	public static String Localhost;// ���¡����ڡ����ӵķ����
	public static MainGuiForClient gui;

    //public static Account user;// ��SignIn����г�ʼ��������IPҲ������
	public static Socket FileSocket;
	public static Socket MsgSocket;

	public static DataOutputStream dos;
	public static DataInputStream dis;
	public static FileOutputStream fos;// ÿ�η����Ҫ�����ļ���ʱ������һ��

	public static PrintWriter isWriter;
	public static BufferedReader reader;

	public static long filelength;

	public static MyRemote serice;

	public static SignInGuiForClient sg;
	
}
