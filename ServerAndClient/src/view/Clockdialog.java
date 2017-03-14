package view;

import view.Clock;

import javax.swing.JFrame;

public class Clockdialog {
	public  void showclock() 
	{
		Clock p=new Clock();
		p.setTitle("Exercise15_7");
		p.setSize(300,300);
		p.setLocationRelativeTo(null);
	//	p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p.setVisible(true);
	}
}
