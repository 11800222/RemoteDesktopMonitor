package services;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

//import com.sun.prism.Image;

/* JOptionPane.showConfirmDialog(null, 
						  information.list(new Account()),"ll", JOptionPane.YES_NO_OPTION, 0, ScreenCapture.Cap());
				*/
public class ScreenCapture {
	public static Icon Cap() {
		Icon cap=null;
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage simage = robot.createScreenCapture(screenRectangle);
			BufferedImage image = new BufferedImage(1300, 700, BufferedImage.TYPE_INT_BGR);  
		    Graphics graphics = image.createGraphics();  
		    graphics.drawImage(simage, 0, 0, 1300, 700, null); 
			 // image.setImage(image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
			cap = new ImageIcon(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cap;
	}
}
