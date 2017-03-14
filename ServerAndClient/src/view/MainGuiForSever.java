package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import overall.End;
import overall.XMLUtil;
import services.information;
import thread.HeardEveyone;
import thread.SendFileEveryone;
import thread.TellEveryone;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;

import DAO.Account;

import javax.swing.JLabel;

public class MainGuiForSever extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JPanel panel;
	public JTextArea textArea;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainGuiForSever() {
		setTitle("\u670D\u52A1\u7AEF ");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 542);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		textArea.setBackground(SystemColor.control);
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane jScrollPane = new JScrollPane(textArea);
		jScrollPane.setBounds(647, 92, 251, 330);
		// contentPane.add(textArea);
		contentPane.add(jScrollPane);

		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField.setBounds(647, 435, 174, 36);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u53D1\u9001\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				textField.setText("");
				End.sgui.textArea.append("服务器：" + msg + "\n");
				TellEveryone t = new TellEveryone("服务器：" + msg);
				Thread ts = new Thread(t);
				ts.start();
			}
		});
		btnNewButton.setBounds(827, 434, 71, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u53D1\u9001\u6587\u4EF6\r\n\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSave = new JFileChooser();
				fileSave.showSaveDialog(End.sgui);
				File file = fileSave.getSelectedFile();

				if (file != null) {
					if (SendFileEveryone.SendFileDone) {
						SendFileEveryone.SendFileDone=false;
						SendFileEveryone sf = new SendFileEveryone();
						sf.setFile(file, file.getName());
						Thread t = new Thread(sf);
						t.start();
					} else {
						JOptionPane.showMessageDialog(null, "请等待上一次文件发送完毕再发送下一个文件",
								"提示", JOptionPane.PLAIN_MESSAGE);
					}
				} 
			}
		});
		btnNewButton_1.setBounds(258, 448, 113, 36);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u4E2A\u4EBA\u4FE1\u606F\r\n");
		//设置对话框
		ActionListener al=(ActionListener)XMLUtil.getBean("dialog");
		btnNewButton_2.addActionListener(al);
		//
		btnNewButton_2.setBounds(399, 13, 104, 36);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_5 = new JButton(
				"\u5173\u95ED\u5B66\u751F\u7535\u8111\r\n");
		btnNewButton_5.setBounds(31, 448, 149, 36);
		contentPane.add(btnNewButton_5);

		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 92, 583, 329);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// String path="C:"+File.separator+"3.png";
		String path = "3.png";
		Icon icon = new ImageIcon(path);
		ClientButton dd = new ClientButton("123", new Account(), icon);

		panel.add(dd);

		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u8FDE\u63A5\u5B66\u751F");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(SystemColor.activeCaptionBorder);
		lblNewLabel.setBounds(10, 49, 135, 36);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\u6D88\u606F\u680F");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		label.setBackground(SystemColor.activeCaptionBorder);
		label.setBounds(647, 49, 135, 36);
		contentPane.add(label);
	}
}
class messageDialog implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		try {
			String info = information.list(End.user);
			JOptionPane.showMessageDialog(null, info, "个人信息",
					JOptionPane.PLAIN_MESSAGE);
		} catch (Exception es) {
			es.printStackTrace();
		}
	}
}
