package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Rmi.MyRemote;

import overall.End;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.border.BevelBorder;

import services.information;

import java.awt.SystemColor;
import java.awt.Point;

public class SignInGuiForClient extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField Account;
	private JTextField ServerIP;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public SignInGuiForClient() {
		setLocation(new Point(900, 300));
		setTitle("\u767B\u5F55\u7A97\u53E3");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 468, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		Account = new JTextField();
		Account.setBounds(132, 59, 231, 33);
		panel.add(Account);
		Account.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(132, 102, 231, 33);
		panel.add(passwordField);

		JButton Sign = new JButton("\u767B\u5F55");
		Sign.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		Sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = ServerIP.getText();
				String account = Account.getText();
				String password = new String(passwordField.getPassword());
				boolean flag = false;
				try {
					End.Severhost = ip;
					End.serice= (MyRemote) Naming.lookup("rmi://"
							+End.Severhost+ "/ServerMethod");
					
					flag = End.serice.SignIn(account, password);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				if (flag) {
					try {
					    if(End.Severhost.equals("127.0.0.1"))
						End.user = End.serice
								.connect("127.0.0.1", "14251104216");
					    else
					    End.user = End.serice
								.connect(End.Localhost, "14251104216");
					    
						End.sg.dispose();
						End.gui = new MainGuiForClient();
						End.gui.setVisible(true);
						
						End.gui.textArea_1.append(information.list(End.user));
		//				Test2.SetupSocket();
						
						System.out.println(End.path);
						System.out.println(End.Localhost);
						System.out.println(End.Severhost);
		
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"µÇÂ¼Ê§°Ü£¬Çë¼ì²é·þÎñÆ÷IP£¬ÕËºÅ£¬ÃÜÂë");
					}catch(Exception e2){
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"µÇÂ¼Ê§°Ü£¬Çë¼ì²é·þÎñÆ÷IP£¬ÕËºÅ£¬ÃÜÂë");
					}

				} else {
					JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü£¬Çë¼ì²é·þÎñÆ÷IP£¬ÕËºÅ£¬ÃÜÂë");
				}
			}
		});
		Sign.setBounds(142, 150, 92, 54);
		panel.add(Sign);

		ServerIP = new JTextField();
		ServerIP.setBounds(132, 15, 242, 31);
		panel.add(ServerIP);
		ServerIP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u670D\u52A1\u5668IP \uFF1A ");
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(24, 13, 97, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8D26\u53F7 \uFF1A ");
		lblNewLabel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(24, 60, 97, 32);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5BC6\u7801  :  ");
		lblNewLabel_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(24, 103, 97, 32);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel(
				"<html><p>\r\n\t<span style=\"line-height:1.5;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\u8BF7\u8F93\u5165</span> \r\n</p>\r\n<p>\r\n\t<span style=\"line-height:1.5;\">\u670D\u52A1\u5668ip\uFF0C\u8D26\u53F7\u548C\u5BC6\u7801</span> \r\n</p>\r\n<p>\r\n\t&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; \u8FDB\u884C\u767B\u5F55\r\n</p></html>");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

	public SignInGuiForClient(String s) {
		super(s);
	}
}
