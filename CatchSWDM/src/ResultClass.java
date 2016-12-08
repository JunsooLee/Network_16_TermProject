
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultClass  extends JFrame implements ActionListener{
	private static ImageIcon bg = null;
	private static JPanel bg_panel;

	JButton btn = new JButton(new ImageIcon(
			((new ImageIcon("img/exit2.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));

	//JLabel Titlelb = new JLabel("Result!!! ");

	public  ResultClass(){

	}
	public ResultClass(int user1, int user2 ,String u11,String u22) {
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		//Titlelb.setBounds(100,30,300,30);
		//Titlelb.setVisible(true);
		bg = new ImageIcon("img/result.png");
		bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bg.getImage(),0, 0, 500, 500, null);
			}
		};

		JLabel u1,u2;
		u1 = new JLabel(u11 + " score: "+ user1);
		u2 = new JLabel(u22 + " score: "+ user2);
		u1.setFont(new Font("고딕" ,100,30));
		u1.setSize(100,40);
		u2.setFont(new Font("고딕",100,30));
		u2.setSize(100,40);

		u1.setVisible(true);
		u2.setVisible(true);

		//u1.setBackground(Color.orange);
		//u2.setBackground(Color.orange);

		//u1.setOpaque(true);
		//u2.setOpaque(true);

		u1.setBounds(70, 110 , 300 ,50);
		u2.setBounds(70, 175, 300 ,50);

		bg_panel.setBounds(0,0,500,500);
		bg_panel.setVisible(true);
		btn.setBounds(200, 400, 100, 40);
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		this.add(u1);
		this.add(u2);
		this.add(btn);
		this.add(bg_panel);
		//this.add(Titlelb);


	}

	public ResultClass(int user1 , int user2 , int user3 ,String u11,String u22, String u33){
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		//Titlelb.setBounds(100,30,300,30);
		//Titlelb.setBackground(Color.BLUE);
		//Titlelb.setOpaque(true);

		bg = new ImageIcon("img/result.png");
		bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bg.getImage(),0, 0, 500, 500, null);
			}
		};

		JLabel u1,u2,u3;

		u1 = new JLabel(u11 + " score: "+ user1);
		u2 = new JLabel(u22 + " score: "+ user2);
		u3 = new JLabel(u33 + " score: "+ user3);
		u1.setFont(new Font("고딕" ,100,30));
		u1.setSize(100,40);
		u2.setFont(new Font("고딕",100,30));
		u2.setSize(100,40);
		u3.setFont(new Font("고딕",100,30));
		u3.setSize(100,40);

		u1.setVisible(true);
		u2.setVisible(true);
		u3.setVisible(true);

		//u1.setBackground(Color.orange);
		//u2.setBackground(Color.orange);
		//u3.setBackground(Color.orange);

		//u1.setOpaque(true);
		//u2.setOpaque(true);
		//u3.setOpaque(true);

		u1.setBounds(70, 110 , 300 ,50);
		u2.setBounds(70, 175 , 300 ,50);
		u3.setBounds(70, 235 , 300 ,50);

		bg_panel.setBounds(0,0,500,500);
		bg_panel.setVisible(true);
		btn.setBounds(200, 400, 100, 40);
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);

		this.add(u1);
		this.add(u2);
		this.add(u3);
		this.add(btn);
		this.add(bg_panel);
		//this.add(Titlelb);


	}
	public ResultClass(int user1 , int user2 , int user3 , int user4 ,String u11,String u22,String u33,String u44){
		setSize(500, 500);
		setLocation(300, 300);
		setLayout(null);
		//Titlelb.setBounds(100,30,300,30);
		//Titlelb.setBackground(Color.BLUE);
		//Titlelb.setOpaque(true);
		bg = new ImageIcon("img/result.png");
		bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bg.getImage(),0, 0, 500, 500, null);
			}
		};


		JLabel u1,u2,u3,u4;
		u1 = new JLabel(u11+ " score: "+ user1);
		u2 = new JLabel(u22+ " score: "+ user2);
		u3 = new JLabel(u33+ " score: "+ user3);
		u4 = new JLabel(u44+ " score: "+ user4);
		u1.setFont(new Font("고딕" ,100,30));
		u1.setSize(100,40);
		u2.setFont(new Font("고딕",100,30));
		u2.setSize(100,40);
		u3.setFont(new Font("고딕",100,30));
		u3.setSize(100,40);
		u4.setFont(new Font("고딕",100,30));
		u4.setSize(100,40);


		u1.setVisible(true);
		u2.setVisible(true);
		u3.setVisible(true);
		u4.setVisible(true);

		//u1.setBackground(Color.orange);
		//u2.setBackground(Color.orange);
		//u3.setBackground(Color.orange);
		//u4.setBackground(Color.orange);

		//u1.setOpaque(true);
		//u2.setOpaque(true);
		//u3.setOpaque(true);
		//u4.setOpaque(true);

		u1.setBounds(70, 110 , 300 ,50);
		u2.setBounds(70, 175 , 300 ,50);
		u3.setBounds(70, 235 , 300 ,50);
		u4.setBounds(70,295,300,50);

		bg_panel.setBounds(0,0,500,500);
		bg_panel.setVisible(true);

		btn.setBounds(200, 400, 100, 40);
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		this.add(btn);
		this.add(u1);
		this.add(u2);
		this.add(u3);
		this.add(u4);
		this.add(bg_panel);
		//this.add(Titlelb);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn){
			this.dispose();
		}


	}
}
