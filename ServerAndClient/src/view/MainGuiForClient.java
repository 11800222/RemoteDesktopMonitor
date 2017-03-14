package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import DAO.Account;
import thread.HeardServer;
import thread.SendFileServer;
import thread.TellServer;
import overall.End;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public class MainGuiForClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public JTextArea textArea;
	public JTextArea textArea_1;// 个人信息

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public MainGuiForClient() {
		setTitle("\u5BA2\u6237\u7AEF");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {  
			super.windowClosing(e);  
			//加入动作  
			System.out.print(true);
			try {
				End.serice.disconnect("127.0.0.1");
			} catch (RemoteException e1) {
			
				e1.printStackTrace();
			}
			 }}  );
		
		
		
		setBounds(100, 100, 541, 447);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 textArea = new JTextArea();
		 textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		 textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		 textArea.setBackground(SystemColor.control);
		// textArea.setBounds(277, 57, 251, 272);
		 JScrollPane jScrollPane = new JScrollPane(textArea);
			jScrollPane.setBounds(277, 57, 251, 272);
		contentPane.add(jScrollPane);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.control);
		textField.setBounds(277, 339, 174, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u63D0\u95EE\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg=textField.getText();
				textField.setText("");
				TellServer t=new TellServer("客户端1："+msg);
                Thread ts=new Thread(t);
                ts.start();
			}
		});
		btnNewButton.setBounds(448, 339, 71, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u63D0\u4EA4\u4F5C\u4E1A\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSave = new JFileChooser();
				fileSave.showSaveDialog(End.gui);
				File file =fileSave.getSelectedFile();
				
				if(file==null){
					System.out.println("未选择文件");
				}else{
				SendFileServer sf=new SendFileServer();
				sf.setFile(file, file.getName());
				Thread t=new Thread(sf);
				t.start();
				}
			}
		});
		btnNewButton_1.setBounds(60, 339, 104, 36);
		contentPane.add(btnNewButton_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textArea_1.setBackground(new Color(240, 240, 240));
		textArea_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textArea_1.setBounds(14, 99, 211, 230);

		contentPane.add(textArea_1);
		
		JLabel lblNewLabel = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(SystemColor.inactiveCaption);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(14, 57, 116, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6D88\u606F\u680F");
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setBackground(SystemColor.inactiveCaption);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(277, 13, 104, 36);
		contentPane.add(lblNewLabel_1);
	}
}
