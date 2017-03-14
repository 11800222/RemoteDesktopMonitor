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
	public static HashMap<String, ClientButton> ClientButtons;// 里面有‘正在’连接的客户端账户，客户端IP也在里面,
	public static DBproxy DBp;
	public static Account user;// 在SignIn里进行初始化，本机IP也在里面
	public static ButtonControler buttonControler;
	public static MainGuiForSever sgui;
	public static SignInGuiForSever signgui;

	//
	//public static String path;
	public static String Severhost;// 存下‘正在’连接的服务端
	public static String Localhost;// 存下‘正在’连接的服务端
	public static MainGuiForClient gui;

    //public static Account user;// 在SignIn里进行初始化，本机IP也在里面
	public static Socket FileSocket;
	public static Socket MsgSocket;

	public static DataOutputStream dos;
	public static DataInputStream dis;
	public static FileOutputStream fos;// 每次服务端要传输文件的时候都设置一次

	public static PrintWriter isWriter;
	public static BufferedReader reader;

	public static long filelength;

	public static MyRemote serice;

	public static SignInGuiForClient sg;
	
}
