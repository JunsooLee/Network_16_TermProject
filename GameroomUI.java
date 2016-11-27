package drawe_druwa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



public class GameroomUI {
	
	private JPanel background_panel, title_panel, drawing_panel, chat_panel;
	private JPanel user1_panel, user2_panel, user3_panel, user4_panel;
	private JFrame frame;
	private JLabel title;
	private ImageIcon bg = null, d=null, r=null, w=null,e=null,a=null,u=null;
	static int num;
	public static int count = 0;

	public GameroomUI() {

		// 전체 프레임 등록
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// 배경 패널 생성
		bg = new ImageIcon("img/whale.jpg");
		background_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, 1000, 800, null);
			}
		};
		
		title = new JLabel("Drawer Druwa");
		d = new ImageIcon("img/d.png");
		r = new ImageIcon("img/r.png");
		a = new ImageIcon("img/a.png");
		w = new ImageIcon("img/w.png");
		e = new ImageIcon("img/e.png");
		u = new ImageIcon("img/u.png");
		
		title_panel = new JPanel(){
			public void paintComponent(Graphics g){
				//title_panel.add(title);
				g.drawImage(d.getImage(),120, 20, 20, 30, null);
				g.drawImage(r.getImage(),145, 20, 20, 30, null);
				g.drawImage(a.getImage(), 160, 20, 20, 30, null);
				g.drawImage(w.getImage(), 185, 20, 20, 30, null);
				g.drawImage(e.getImage(), 200, 20, 20, 30, null);
				//drawer druwa 이쁜 글씨로 띄우기
			}
		};

		chat_panel = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};
		
		drawing_panel = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};
		
		
		user1_panel = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};
		
		user2_panel = new JPanel(){
			public void paintComponent (Graphics g){
				
			}
		};
		
		user3_panel = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};

		user4_panel = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};
		//두께
		title_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		title_panel.setLayout(new BorderLayout());
		user1_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		user1_panel.setLayout(new BorderLayout());
		user2_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		user2_panel.setLayout(new BorderLayout());
		user3_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		user3_panel.setLayout(new BorderLayout());
		user4_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		user4_panel.setLayout(new BorderLayout());
		
		drawing_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		drawing_panel.setLayout(new BorderLayout());
		
		chat_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		chat_panel.setLayout(new BorderLayout());
		

		// 패널들의 위치 조정
		title_panel.setBounds(0,0,1000, 80);
		title_panel.setVisible(true);		
		user1_panel.setBounds(50, 100, 200, 200);
		user1_panel.setVisible(true);
		user2_panel.setBounds(50, 320, 200,200);
		user2_panel.setVisible(true);
		user3_panel.setBounds(750, 100, 200, 200);
		user3_panel.setVisible(true);
		user4_panel.setBounds(750, 320, 200, 200);
		
		drawing_panel.setBounds(300, 80, 400, 500);
		drawing_panel.setVisible(true);
		chat_panel.setBounds(300, 500, 400, 220);
		chat_panel.setVisible(true);		
		
		background_panel.setBounds(0, 0, 1000, 800);
		background_panel.setVisible(true);

 
		frame.add(chat_panel);
		frame.add(drawing_panel);
		frame.add(user1_panel);
		frame.add(user2_panel);
		frame.add(user3_panel);
		frame.add(user4_panel);
		frame.add(title_panel);
		frame.add(background_panel);
		frame.setSize(1000, 800);
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		GameroomUI gameroom = new GameroomUI();

	}

}