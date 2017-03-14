package view;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Adapter extends Clockdialog implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		showclock();
	}

}
