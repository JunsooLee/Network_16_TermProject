import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultClass  extends JFrame implements ActionListener{
	JButton btn = new JButton("OK!");
	JLabel Titlelb = new JLabel("Result!!! ");

	public  ResultClass(){

	}
	public ResultClass(int user1, int user2 ,String u11,String u22) {
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		Titlelb.setBounds(100,30,300,30);
		Titlelb.setVisible(true);

		JLabel u1,u2;
		u1 = new JLabel(u11 + " score: "+ user1);
		u2 = new JLabel(u22 + " score: "+ user2);

		u1.setVisible(true);
		u2.setVisible(true);

		u1.setBackground(Color.orange);
		u2.setBackground(Color.orange);

		u1.setOpaque(true);
		u2.setOpaque(true);

		u1.setBounds(100, 100 , 300 ,30);
		u2.setBounds(100, 140 , 300 ,30);

		btn.setBounds(100, 300, 300, 40);
		btn.addActionListener(this);
		this.add(btn);
		this.add(Titlelb);
		this.add(u1);
		this.add(u2);

	}

	public ResultClass(int user1 , int user2 , int user3 ,String u11,String u22, String u33){
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		Titlelb.setBounds(100,30,300,30);
		Titlelb.setBackground(Color.BLUE);
		Titlelb.setOpaque(true);

		JLabel u1,u2,u3;

		u1 = new JLabel(u11 + " score: "+ user1);
		u2 = new JLabel(u22 + " score: "+ user2);
		u3 = new JLabel(u33 + " score: "+ user3);

		u1.setVisible(true);
		u2.setVisible(true);
		u3.setVisible(true);

		u1.setBackground(Color.orange);
		u2.setBackground(Color.orange);
		u3.setBackground(Color.orange);

		u1.setOpaque(true);
		u2.setOpaque(true);
		u3.setOpaque(true);

		u1.setBounds(100, 100 , 300 ,30);
		u2.setBounds(100, 140 , 300 ,30);
		u3.setBounds(100, 180 , 300 ,30);
		btn.setBounds(100, 300, 300, 40);
		this.add(btn);
		this.add(Titlelb);
		btn.addActionListener(this);
		this.add(u1);
		this.add(u2);
		this.add(u3);

	}
	public ResultClass(int user1 , int user2 , int user3 , int user4 ,String u11,String u22,String u33,String u44){
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		Titlelb.setBounds(100,30,300,30);
		Titlelb.setBackground(Color.BLUE);
		Titlelb.setOpaque(true);


		JLabel u1,u2,u3,u4;
		u1 = new JLabel(u11+ " score: "+ user1);
		u2 = new JLabel(u22+ " score: "+ user2);
		u3 = new JLabel(u33+ " score: "+ user3);
		u4 = new JLabel(u44+ " score: "+ user4);

		u1.setVisible(true);
		u2.setVisible(true);
		u3.setVisible(true);
		u4.setVisible(true);

		u1.setBackground(Color.orange);
		u2.setBackground(Color.orange);
		u3.setBackground(Color.orange);
		u4.setBackground(Color.orange);

		u1.setOpaque(true);
		u2.setOpaque(true);
		u3.setOpaque(true);
		u4.setOpaque(true);

		u1.setBounds(100, 100 , 300 ,30);
		u2.setBounds(100, 140 , 300 ,30);
		u3.setBounds(100, 180 , 300 ,30);
		u4.setBounds(100, 220 , 300 ,30);

		btn.setBounds(100, 300, 300, 40);
		btn.addActionListener(this);
		this.add(btn);
		this.add(Titlelb);
		this.add(u1);
		this.add(u2);
		this.add(u3);
		this.add(u4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn){
			this.dispose();
		}


	}
}
