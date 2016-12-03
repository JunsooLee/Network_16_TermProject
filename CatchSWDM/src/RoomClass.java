import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RoomClass extends JPanel {
	Toolkit tk = Toolkit.getDefaultToolkit();
	JScrollPane scrollPane;
	ImageIcon icon, icon2, userIn, userOut;
	JPanel centerPanel;

	public RoomClass() {
		icon = new ImageIcon("background.png");
		this.setSize(290, 125);
		this.setLayout(null);

		JButton button = new JButton("버튼");
		this.setLayout(null);
		this.add(button);

	}
	public RoomClass(Socket d){

	}
	public RoomClass(int userNumber, String roomName) {
		icon = new ImageIcon("background.png");
		icon2 = new ImageIcon("panelimg.png");
		this.setLayout(null);
		this.setSize(290, 125);

		JLabel title = new SetLabelImg("panelimg.png");
		JLabel userLine = new SetLabelImg("panelimg.png");


		title.setText("  " + roomName);
		title.setBounds(5, 5, 100, 30);
		this.add(title);

		//userLine.setBounds(5, 45, 200, 40);
		//this.add(userLine);
		setPanel(userNumber);
		this.repaint();
	}
	public void setPanel(int userNumber){
		JLabel user1,user2,user3,user4;


		centerPanel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon2.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		centerPanel.setLayout(null);
		centerPanel.setBounds(5, 45, 200, 40);
		if(userNumber == 0){
			user1 = new SetLabelImg("user2.png");
			user2 =	new SetLabelImg("user2.png");
			user3 = new SetLabelImg("user2.png");
			user4 = new SetLabelImg("user2.png");

			user1.setBounds(10,5, 30,30);
			centerPanel.add(user1);
			user2.setBounds(50,5, 30,30);
			centerPanel.add(user2);
			user3.setBounds(90,5, 30,30);
			centerPanel.add(user3);
			user4.setBounds(130,5, 30,30);
			centerPanel.add(user4);
		}
		if (userNumber == 1) {
			user1 = new SetLabelImg("user1.png");
			user2 =	new SetLabelImg("user2.png");
			user3 = new SetLabelImg("user2.png");
			user4 = new SetLabelImg("user2.png");

			user1.setBounds(10,5, 30,30);
			centerPanel.add(user1);
			user2.setBounds(50,5, 30,30);
			centerPanel.add(user2);
			user3.setBounds(90,5, 30,30);
			centerPanel.add(user3);
			user4.setBounds(130,5, 30,30);
			centerPanel.add(user4);
		}
		if (userNumber == 2) {
			user1 = new SetLabelImg("user1.png");
			user2 =	new SetLabelImg("user1.png");
			user3 = new SetLabelImg("user2.png");
			user4 = new SetLabelImg("user2.png");

			user1.setBounds(10,5, 30,30);
			centerPanel.add(user1);
			user2.setBounds(50,5, 30,30);
			centerPanel.add(user2);
			user3.setBounds(90,5, 30,30);
			centerPanel.add(user3);
			user4.setBounds(130,5, 30,30);
			centerPanel.add(user4);
		}
		if (userNumber == 3) {
			user1 = new SetLabelImg("user1.png");
			user2 =	new SetLabelImg("user1.png");
			user3 = new SetLabelImg("user1.png");
			user4 = new SetLabelImg("user2.png");
			user1.setBounds(10,5, 30,30);
			centerPanel.add(user1);
			user2.setBounds(50,5, 30,30);
			centerPanel.add(user2);
			user3.setBounds(90,5, 30,30);
			centerPanel.add(user3);
			user4.setBounds(130,5, 30,30);
			centerPanel.add(user4);
		}
		if (userNumber == 4) {
			user1 = new SetLabelImg("user1.png");
			user2 =	new SetLabelImg("user1.png");
			user3 = new SetLabelImg("user1.png");
			user4 = new SetLabelImg("user1.png");
			user1.setBounds(10,5, 30,30);
			centerPanel.add(user1);
			user2.setBounds(50,5, 30,30);
			centerPanel.add(user2);
			user3.setBounds(90,5, 30,30);
			centerPanel.add(user3);
			user4.setBounds(130,5, 30,30);
			centerPanel.add(user4);
		}

		//centerPanel.setBounds(5, 45, 200, 40);
		this.add(centerPanel);

	}
	@Override
	protected void paintComponent(Graphics g) {
		// g.fillRect(0, 0, 290, 125);
		// g.drawImage(img, 0, 0,290, 125, this);
		// setOpaque(false);

		// Approach 1: Dispaly image at at full size
		// g.drawImage(icon.getImage(), 0, 0, null);
		// Approach 2: Scale image to size of component
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		// Approach 3: Fix the image position in the scroll pane
		// Point p = scrollPane.getViewport().getViewPosition();
		// g.drawImage(icon.getImage(), p.x, p.y, null);
		setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
		super.paintComponent(g);

	}
}
