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

import overall.End;
import services.ButtonControler;
import services.SignIn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class SignInGuiForSever extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SignInGuiForSever() {
		setTitle("\u767B\u5F55\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(98, 52, 274, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 93, 274, 33);
		panel.add(passwordField);
		
		JButton btnNewButton_2 = new JButton("\u767B\u5F55");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText();
				String password =new String(passwordField.getPassword());
				if(SignIn.sign(account, password)){
					try{
					End.sgui = new MainGuiForSever();
					End.sgui.setVisible(true);
					End.DBp.setAccount(account, End.user);
					End.buttonControler = new ButtonControler(End.sgui.panel);
					End.signgui.dispose();
					}catch(Exception e2){
						e2.printStackTrace();
					}
					}
				else {
					JOptionPane.showMessageDialog(null, "µ«¬º ß∞‹£¨«ÎºÏ≤È’À∫≈∫Õ√‹¬Î");  
				              
					
				}
			}
		});
		btnNewButton_2.setBounds(173, 139, 70, 41);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7 \uFF1A ");
		lblNewLabel_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(33, 52, 72, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801  : ");
		lblNewLabel_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(33, 93, 84, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("<html><p>\r\n\t\u8BF7\u8F93\u5165\u8D26\u53F7\u548C\u5BC6\u7801\r\n</p>\r\n<p>\r\n\t&nbsp; &nbsp; &nbsp; \u8FDB\u884C\u767B\u5F55\r\n</p></html>");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}
	public SignInGuiForSever(String s){
		super(s);
	}

}
