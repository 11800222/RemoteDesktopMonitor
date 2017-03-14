package services;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import overall.End;
import view.ClientButton;

public class ButtonControler implements Observer {
	public JPanel panel;

	public ButtonControler(JPanel panel) {
		this.panel = panel;
	}

	public void addbutton(ClientButton clientbutton) {
		this.panel.add(clientbutton);
		this.panel.revalidate();

		End.ClientButtons.put(clientbutton.account.getIp(), clientbutton);
	}

	public void removebutton(ClientButton clientbutton) {
		this.panel.remove(clientbutton);
		this.panel.setVisible(false);
		this.panel.setVisible(true);
	}

	public void update(Observable o, Object arg) {
		try {
			ClientButton cb = (ClientButton) arg;
			
			cb.FileSocket.close();
			cb.MsgSocket.close();
			removebutton(cb);
			End.ClientButtons.remove(cb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}