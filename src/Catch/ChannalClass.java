package Catch;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Member;

public class ChannalClass extends JFrame implements ActionListener {
	public JButton btni, btno;
	private JPanel LeftPanel = new JPanel();
	private JPanel MainPanel = new JPanel();
	private JPanel BottomPanel = new JPanel();
	private JPanel CenterPanel = new JPanel();
	private static JPanel bg_panel, left_img_panel;
	private JTextField textField = new JTextField();
	private JTextArea messageArea;
	private static ImageIcon bgimg = null, LPimg = null;
	private JLabel nameLabel;
	private JPanel room1, room2, room3, room4, room5, room6;
	private JButton Jbtn1, Jbtn2, Jbtn3, Jbtn4, Jbtn5, Jbtn6;
	JButton jbt1, jbt2, jbt3, jbt4, jbt5, jbt6;
	Vector months;
	JList list;
	JScrollPane jpane;
	JScrollPane jpane2;

	Boolean showframe = false;

	Socket socket;
	BufferedReader in;
	PrintWriter out;
	
	String myName;
	int character;
	int grade;
	int point;
	
	private static ArrayList<String> nameList;
	JFrame frame = new JFrame("Chatter");

	int[] eachRoomUser = new int[6];
	String[] eachRoomName = new String[6];
	int namecount = 0;

	public ChannalClass(Member info) {
		
		myName = info.getID();
		character = info.getCharacter();
		grade = info.getGrade();
		point = info.getPoint();
		
		for (int i = 0; i < 6; i++) {
			eachRoomUser[i] = 0;
		}
		this.setLayout(null);
		this.setSize(820, 652);

		LeftPanel.setLayout(null);
		if(character == 0){
			LeftPanel.add(new ProfileImagePanel("img/img1.png"));
		}else if(character == 1){
			LeftPanel.add(new ProfileImagePanel("img/img2.png"));
		}else if(character == 2){
			LeftPanel.add(new ProfileImagePanel("img/img3.png"));
		}else if(character == 3){
			LeftPanel.add(new ProfileImagePanel("img/img4.png"));
		}else if(character == 4){
			LeftPanel.add(new ProfileImagePanel("img/img5.png"));
		}else if(character == 5){
			LeftPanel.add(new ProfileImagePanel("img/img6.png"));
		}

		LPimg = new ImageIcon();
		left_img_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(LPimg.getImage(),0, 0, 200, 600, null);
			}
		};

		left_img_panel.setBounds(0, 0, 200, 600);
		left_img_panel.setVisible(true);

		// 190 205
		nameLabel = new JLabel();
		nameLabel.setBounds(0, 210 ,200 ,100);
		months = new Vector();
		list = new JList(months);
		nameList = new ArrayList<String>();

		// months.addElement("January222");
		// months.addElement("December");
		// months.addElement("January");
		jpane = new JScrollPane(list);
		jpane.setBounds(0, 300, 200, 600);// textarea\
		LeftPanel.add(nameLabel);
		LeftPanel.add(jpane);
		LeftPanel.setOpaque(false);

		// frame.add(new JScrollPane(list));
		// frame.setSize(300,200);
		// frame.setVisible(true);
		// btno = new JButton("인풋");
		// btni = new JButton("아웃");
		// frame.add(btni,"North");
		// frame.add(btno,"North");
		//
		LeftPanel.setBackground(Color.lightGray);
		LeftPanel.setBounds(0, 0, 200, 600);
		this.add(LeftPanel);
		this.add(left_img_panel);

		BottomPanel();
		this.add(BottomPanel);

		CenterPanel();
		this.add(CenterPanel);
		this.add(bg_panel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void BottomPanel() {
		BottomPanel.setLayout(null);

		messageArea = new JTextArea();
		textField.setEditable(true);
		messageArea.setEditable(false);
		// messageArea.setBounds(5, 170, 460, 130);
		jpane2 = new JScrollPane(messageArea);
		jpane2.setBounds(5, 5, 590, 170);// textarea¿« Ω∫≈©∑—πŸ∏¶ √ﬂ∞°«— ∞Õ¿«
		// ªÁ¿Ã¡Ó∏¶ ¡ˆ¡§«—¥Ÿ.
		BottomPanel.add(jpane2);
		textField.setBounds(5, 178, 590, 19);
		BottomPanel.add(textField);

		// Add Listeners
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("message " + textField.getText());
				// messageArea.append("hyuna" + " : " + textField.getText() +
				// "\n");
				messageArea.setCaretPosition(messageArea.getText().length());// ∏ﬁºº¡ˆ

				messageArea.setLineWrap(true);
				textField.setText("");
			}
		});


		BottomPanel.setBounds(201, 401, 599, 199);
		BottomPanel.setBackground(Color.lightGray);

	}

	private void CenterPanel() {
		CenterPanel.setLayout(null);
		CenterPanel.setBounds(201, 0, 599, 399);

		bgimg = new ImageIcon("img/color_background3.png");
		bg_panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(bgimg.getImage(),0, 0, 599, 399, null);
			}
		};

		bg_panel.setBounds(201, 0, 599, 399);
		bg_panel.setVisible(true);

		room1 = new RoomClass();
		room2 = new RoomClass();
		room3 = new RoomClass();
		room4 = new RoomClass();
		room5 = new RoomClass();
		room6 = new RoomClass();

		Jbtn1 = new JButton();
		Jbtn2 = new JButton();
		Jbtn3 = new JButton();
		Jbtn4 = new JButton();
		Jbtn5 = new JButton();
		Jbtn6 = new JButton();
		Jbtn1 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));
		Jbtn2 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));
		Jbtn3 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));
		Jbtn4 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));
		Jbtn5 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));
		Jbtn6 = new JButton(new ImageIcon(((new ImageIcon("img/join2.png").getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)))));



		Jbtn1.addActionListener(this);
		Jbtn2.addActionListener(this);
		Jbtn3.addActionListener(this);
		Jbtn4.addActionListener(this);
		Jbtn5.addActionListener(this);
		Jbtn6.addActionListener(this);

		room1.setBounds(5, 8, 290, 125);
		room2.setBounds(305, 8, 290, 125);
		room3.setBounds(5, 138, 290, 125);
		room4.setBounds(305, 138, 290, 125);
		room5.setBounds(5, 268, 290, 125);
		room6.setBounds(305, 268, 290, 125);

		Jbtn1.setBounds(230, 95, 50, 25);
		Jbtn2.setBounds(530, 95, 50, 25);
		Jbtn3.setBounds(230, 220, 50, 25);
		Jbtn4.setBounds(530, 220, 50, 25);
		Jbtn5.setBounds(230, 345, 50, 25);
		Jbtn6.setBounds(530, 345, 50, 25);

		/*room1.setVisible(false);
		room2.setVisible(false);
		room3.setVisible(false);
		room4.setVisible(false);
		room5.setVisible(false);
		room6.setVisible(false);
		Jbtn1.setVisible(false);
		Jbtn2.setVisible(false);
		Jbtn3.setVisible(false);
		Jbtn4.setVisible(false);
		Jbtn5.setVisible(false);
		Jbtn6.setVisible(false);
		 */



		JPanel dd = new JPanel();
		dd.setLayout(new GridLayout(3, 2));
		jbt1 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));
		jbt2 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));
		jbt3 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));
		jbt4 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));
		jbt5 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));

		jbt6 = new JButton(new ImageIcon(
				((new ImageIcon("img/create.png").getImage().getScaledInstance(100, 40, java.awt.Image.SCALE_SMOOTH)))));

		//		jbt1.setContentAreaFilled(false);
		//		jbt2.setContentAreaFilled(false);
		//		jbt3.setContentAreaFilled(false);
		//		jbt4.setContentAreaFilled(false);
		//		jbt5.setContentAreaFilled(false);
		//		jbt6.setContentAreaFilled(false);



		jbt1.addActionListener(this);
		jbt2.addActionListener(this);
		jbt3.addActionListener(this);
		jbt4.addActionListener(this);
		jbt5.addActionListener(this);
		jbt6.addActionListener(this);

		jbt1.setBounds(200, 96, 90, 32);
		jbt2.setBounds(500, 96, 90, 32);
		jbt3.setBounds(200, 226, 90, 32);
		jbt4.setBounds(500, 226, 90, 32);
		jbt5.setBounds(200, 356, 90, 32);
		jbt6.setBounds(500, 356, 90, 32);



		CenterPanel.add(Jbtn1);
		CenterPanel.add(Jbtn2);
		CenterPanel.add(Jbtn3);
		CenterPanel.add(Jbtn4);
		CenterPanel.add(Jbtn5);
		CenterPanel.add(Jbtn6);
		CenterPanel.add(jbt1);
		CenterPanel.add(jbt2);
		CenterPanel.add(jbt3);
		CenterPanel.add(jbt4);
		CenterPanel.add(jbt5);
		CenterPanel.add(jbt6);
		CenterPanel.add(room1);
		CenterPanel.add(room2);
		CenterPanel.add(room3);
		CenterPanel.add(room4);
		CenterPanel.add(room5);
		CenterPanel.add(room6);

		//CenterPanel.setBackground(Color.LIGHT_GRAY);
		CenterPanel.setOpaque(false);

		room1.setVisible(true);
		room2.setVisible(true);
		room3.setVisible(true);
		room4.setVisible(true);
		room5.setVisible(true);
		room6.setVisible(true);

		Jbtn1.setVisible(false);
		Jbtn2.setVisible(false);
		Jbtn3.setVisible(false);
		Jbtn4.setVisible(false);
		Jbtn5.setVisible(false);
		Jbtn6.setVisible(false);

		jbt1.setVisible(true);
		jbt2.setVisible(true);
		jbt3.setVisible(true);
		jbt4.setVisible(true);
		jbt5.setVisible(true);
		jbt6.setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if (source == Jbtn1) {
			if (eachRoomUser[0] != 4) {
				out.println("change 0 + " + myName);
				//eachRoomUser[0]++;
				runGame(0);
			}
		} else if (source == Jbtn2) {
			if (eachRoomUser[1] != 4) {
				out.println("change 1 + " + myName);
				//eachRoomUser[1]++;
				runGame(1);
			}
		} else if (source == Jbtn3) {
			if (eachRoomUser[2] != 4) {
				out.println("change 2 + " + myName);
				//eachRoomUser[2]++;
				runGame(2);
			}
		} else if (source == Jbtn4) {
			if (eachRoomUser[3] != 4) {
				out.println("change 3 + " + myName);
				//eachRoomUser[3]++;
				runGame(3);
			}
		} else if (source == Jbtn5) {
			if (eachRoomUser[4] != 4) {
				out.println("change 4 + " + myName);
				//eachRoomUser[4]++;
				runGame(4);
			}
		} else if (source == Jbtn6) {
			if (eachRoomUser[5] != 4) {
				out.println("change 5 + " + myName);
				//eachRoomUser[5]++;
				runGame(5);
			}
		}

		else if (source == jbt1) {
			if (eachRoomUser[0] != 4) {
				out.println("change 0 + " + myName);
				//eachRoomUser[0]++;
				runGame(0);
			}
		} else if (source == jbt2) {
			if (eachRoomUser[1] != 4) {
				out.println("change 1 + " + myName);
				//eachRoomUser[1]++;
				runGame(1);
			}
		} else if (source == jbt3) {
			if (eachRoomUser[2] != 4) {
				out.println("change 2 + " + myName);
				//eachRoomUser[2]++;
				runGame(2);
			}
		} else if (source == jbt4) {
			if (eachRoomUser[3] != 4) {
				out.println("change 3 + " + myName);
				//eachRoomUser[3]++;
				runGame(3);
			}
		} else if (source == jbt5) {
			if (eachRoomUser[4] != 4) {
				out.println("change 4 + " + myName);
				//eachRoomUser[4]++;
				runGame(4);
			}
		} else if (source == jbt6) {
			if (eachRoomUser[5] != 4) {
				out.println("change 5 + " + myName);
				//eachRoomUser[5]++;
				runGame(5);
			}
		}

	}

	private void runGame(int room) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				GameroomUI gameroom = new GameroomUI(socket, room , myName);
			}

		});
		thread.start();
		this.setVisible(false);
	}

	private void getError() {
		JOptionPane.showMessageDialog(null, "input Error", "wrong input", JOptionPane.ERROR_MESSAGE);
	}

	public void run() throws IOException {
		socket = new Socket("LocalHost", 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		while (true) {
			// jpane.repaint();

			String input = in.readLine();

			if (input.startsWith("SUBMITNAME")) {
				out.println(myName);
				nameLabel.setText(" Name: "+myName);
				input = in.readLine();
				if (input.equals("Error")) {
					getError();
				}
			} else if (input.startsWith("NAMEACCEPTED")) {
				String[] tmp = input.split(" ");
				eachRoomUser[0] = Integer.parseInt(tmp[1]);
				eachRoomUser[1] = Integer.parseInt(tmp[2]);
				eachRoomUser[2] = Integer.parseInt(tmp[3]);
				eachRoomUser[3] = Integer.parseInt(tmp[4]);
				eachRoomUser[4] = Integer.parseInt(tmp[5]);
				eachRoomUser[5] = Integer.parseInt(tmp[6]);
				roomCheck(0);
				roomCheck(1);
				roomCheck(2);
				roomCheck(3);
				roomCheck(4);
				roomCheck(5);
				//System.out.println(myName+" start: "+eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

				textField.setEditable(true);

			} else if (input.startsWith("message")) {
				messageArea.append(input.substring(8) + "\n");
			}
			// name assign
			else if (input.startsWith("assign")) {
				months.addElement(input.substring(7));
				list.setListData(months);
			} else if (input.startsWith("removename")) {
				months.removeElement(input.substring(11));
				list.setListData(months);
			} else if (input.startsWith("change")) {
				//System.out.println(myName+" change: "+eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

				//System.out.println(input);
				String[] str = input.split(" ");
				roomCheck(Integer.parseInt(str[1]));
			}
			else if(input.startsWith("synch")){
				String[] tmp = input.split(" ");
				eachRoomUser[0] = Integer.parseInt(tmp[1]);
				eachRoomUser[1] = Integer.parseInt(tmp[2]);
				eachRoomUser[2] = Integer.parseInt(tmp[3]);
				eachRoomUser[3] = Integer.parseInt(tmp[4]);
				eachRoomUser[4] = Integer.parseInt(tmp[5]);
				eachRoomUser[5] = Integer.parseInt(tmp[6]);
				//System.out.println(myName+ " synch: " +eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

			}
			else if (input.startsWith("redispose")) {
				if (showframe == false) {
					String[] tmp = input.split(" ");
					//System.out.println(eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);
					out.println("change "+Integer.parseInt(tmp[1])+" -");
					showframe = true;

				}
			} else if (input.startsWith("alluser")) {
				input = input.trim();

				if (input.length() == 7) {

				} else {
					String[] tmp = input.split(" ");
					for (int i = 1; i < tmp.length; i++) {
						months.addElement(tmp[i]);
					}
				}
			}else{
				System.out.println("일로오면 안돼! :" +input);
				out.println("retrans " +input);
			}
		}


	}

	private void roomCheck(int room) {
		if(showframe == true){
			showframe =false;
			this.setVisible(true);
		}
		System.out.println(myName + " "+ room +" : "+ eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

		if(room == 0){
			namecount++;
			room1.setVisible(false);
			room1.removeAll();
			room1 = new RoomClass(eachRoomUser[0], "roomname"+namecount);
			room1.setBounds(5, 8, 290, 125);
			CenterPanel.add(room1);
			room1.repaint();
			if (eachRoomUser[0] == 0) {
				room1.setVisible(true);
				jbt1.setVisible(true);
				Jbtn1.setVisible(false);
			}
			else{
				jbt1.setVisible(false);
				room1.setVisible(true);
				Jbtn1.setVisible(true);
			}
		}
		else if(room == 1){
			room2.setVisible(false);
			room2.removeAll();
			room2 = new RoomClass(eachRoomUser[1], "roomname2");
			room2.setBounds(305, 8, 290, 125);
			CenterPanel.add(room2);
			room2.repaint();
			if (eachRoomUser[1] == 0) {
				room2.setVisible(true);
				jbt2.setVisible(true);
				Jbtn2.setVisible(false);
			}
			else{
				jbt2.setVisible(false);
				room2.setVisible(true);
				Jbtn2.setVisible(true);
			}
		}
		else if(room == 2){
			room3.setVisible(false);
			room3.removeAll();
			room3 = new RoomClass(eachRoomUser[2], "roomname2");
			room3.setBounds(5, 138, 290, 125);
			CenterPanel.add(room3);
			room3.repaint();
			if (eachRoomUser[2] == 0) {

				room3.setVisible(true);
				jbt3.setVisible(true);
				Jbtn3.setVisible(false);
			}
			else{

				jbt3.setVisible(false);
				room3.setVisible(true);
				Jbtn3.setVisible(true);
			}
		}
		else if(room == 3){
			room4.setVisible(false);
			room4.removeAll();
			room4 = new RoomClass(eachRoomUser[3], "roomname2");
			room4.setBounds(305, 138, 290, 125);
			CenterPanel.add(room4);
			room4.repaint();
			if (eachRoomUser[3] == 0) {
				room4.setVisible(true);
				jbt4.setVisible(true);
				Jbtn4.setVisible(false);
			}
			else{
				jbt4.setVisible(false);
				room4.setVisible(true);
				Jbtn4.setVisible(true);
			}
		}
		else if(room == 4){
			room5.setVisible(false);
			room5.removeAll();
			room5 = new RoomClass(eachRoomUser[4], "roomname2");
			room5.setBounds(5, 268, 290, 125);
			CenterPanel.add(room5);
			room5.repaint();
			if (eachRoomUser[4] == 0) {

				room5.setVisible(true);
				jbt5.setVisible(true);
				Jbtn5.setVisible(false);
			}
			else{
				jbt5.setVisible(false);
				room5.setVisible(true);
				Jbtn5.setVisible(true);
			}
		}
		else if(room == 5){
			room6.setVisible(false);
			room6.removeAll();
			room6 = new RoomClass(eachRoomUser[5], "roomname2");
			room6.setBounds(305, 268, 290, 125);
			CenterPanel.add(room6);
			room6.repaint();
			if (eachRoomUser[5] == 0) {
				room6.setVisible(true);
				jbt6.setVisible(true);
				Jbtn6.setVisible(false);
			}
			else{
				jbt6.setVisible(false);
				room6.setVisible(true);
				Jbtn6.setVisible(true);
			}
		}
		CenterPanel.repaint();
	}
}