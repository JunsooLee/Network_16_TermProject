package Catch;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SetLabelImg extends JLabel{
	ImageIcon icon;
	SetLabelImg(String imgName){
		icon = new ImageIcon(imgName);
	}

	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		//				g.drawImage(icon2.getImage(), 0, 0, null);
		super.paintComponent(g);
	}
}
