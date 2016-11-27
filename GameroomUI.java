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
	
	private JPanel background_panel, title_panel, elevator2_panel, elevator3_panel, floor_panel, ann_panel;
	private JFrame frame;
	private JLabel title;
	private JButton[][] floor_button;
	private ImageIcon bg = null, floor = null, fbutton = null, show_floor = null, planet = null;
	static int elevator_x = 177;
	static int elevator_y;
	Timer elevator_time = new Timer();

	public static int count = 0;

	public GameroomUI() {

		// 전체 프레임 등록
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// 배경 패널 생성
		bg = new ImageIcon("img/glass2.png");
		background_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, 1400, 1000, null);

				for (int i = 1; i <= 10; i++) {
					show_floor = new ImageIcon("img/floor" + (11 - i) + ".png");
					g.drawImage(show_floor.getImage(), 40, -50 + (i * 90), 60, 60, null);
				}
				for (int i = 1; i <= 10; i++) {
					planet = new ImageIcon("img/stair" + (11 - i) + ".png");
					g.drawImage(planet.getImage(), 940, -60 + (i * 90), 50, 50, null);
				}
			}
		};
		
		title = new JLabel("Drawer Druwa");
		title_panel = new JPanel(){
			public void paintComponent(Graphics g){
				title_panel.add(title);
			}
		};

		//두께
		title_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		title_panel.setLayout(new BorderLayout());

		

		// 패널들의 위치 조정
		title_panel.setBounds(0,0,1000, 80);
		title_panel.setVisible(true);
		
		background_panel.setBounds(0, 0, 1400, 1000);
		background_panel.setVisible(true);

 
		
		frame.add(title_panel);
		frame.add(background_panel);
		frame.setSize(1000, 800);
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		GameroomUI gameroom = new GameroomUI();

	}

	
}