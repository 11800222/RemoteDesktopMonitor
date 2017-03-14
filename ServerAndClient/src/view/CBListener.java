package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.JOptionPane;

import services.information;
import Rmi.MyClientRemote;
import Rmi.MyRemote;

public class CBListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		ClientButton clientbutton=(ClientButton)e.getSource();
		try{
			String info=information.list(clientbutton.account);
		JOptionPane.showConfirmDialog(null, info,"Ñ§Éú"+clientbutton.getText(),
				JOptionPane.DEFAULT_OPTION, 0,clientbutton.serice.Cap());
		}catch(Exception es){
			es.printStackTrace();
		}
	}

}