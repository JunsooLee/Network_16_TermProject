import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class ProfileImagePanel extends JPanel{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image img;

	public  ProfileImagePanel(String ImgName){//person¿« ¡§∫∏
		this.setBounds(0, 0,200, 210 );
		img = tk.createImage(ImgName);


	}
	@Override
	protected void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 210);
		g.drawImage(img, 5, 5,190, 205, this);
	}

}
