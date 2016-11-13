package drawe_druwa;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;


public class GameroomUI {
	
	public static void main(String[] args){
	
		JFrame frame = new JFrame();
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());
	
		Font f1 = new Font("绊雕", Font.BOLD, 30);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel(); 
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		JLabel label = new JLabel("Drawer Druwa");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(f1);
		
		panel1.setLayout(new GridLayout(2,1));
		//panel2.setLayout(new GridLayout(3,1));
		
		
		//啊款单 哭率(蜡历甸)
		//panel2.setLayout(new GridLayout(3,1));
		//Box b1 = new Box(BoxLayout.Y_AXIS);
		//Box b2 = new Box(BoxLayout.Y_AXIS);
		//JPanel panel2_1 = new JPanel();
		//JPanel panel2_2 = new JPanel();
		//JButton temp_b1 = new JButton("temp1");
		//JButton temp_b2 = new JButton("temp2");
		
		//JTextArea temp_text = new JTextArea("", 20,20);
		
		
		//panel2_1.setBounds(0, 0, 20, 20);
		//panel2_2.setBounds(0, 0, 20, 20);
		//panel2_1.add(temp_b1);
		//panel2_2.add(temp_b2);
		
		//b1.add(panel2_1);
		//b1.add(panel2_2);
		//b2.add(panel2_1);
		//b2.add(panel2_2);
		
		//panel2.add(b1);
		//panel2.add(temp_text);
		//panel2.add(b2);
		
		panel1.add(label);
		
		panel1.add(panel2);
		
		cp.add(panel1);
		//cp.add(panel2);
		
		frame.pack();
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}

	public GameroomUI(){
		
	}

	
}
