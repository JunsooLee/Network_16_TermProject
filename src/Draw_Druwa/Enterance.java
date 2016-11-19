package Draw_Druwa;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Enterance {
	JFrame jframe = new JFrame("Drawer Druwa");
	Container cp = jframe.getContentPane();
	ImageIcon image = new ImageIcon("Drawer Druwa.png");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    
	public Enterance(){
		Image tmp = image.getImage();
		Image new_image = tmp.getScaledInstance(1024, 650, java.awt.Image.SCALE_SMOOTH);
		ImageIcon new_icon = new ImageIcon(new_image);
		JLabel label = new JLabel(new_icon,JLabel.CENTER);
		cp.setLayout(new FlowLayout());
		cp.add(label);
		jframe.setSize(1024,650);
		jframe.pack();
	}
	public static void main(String[] args){
		Enterance client = new Enterance();
		client.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.jframe.setVisible(true);		
	}
}
