import java.awt.Color;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChannalClass extends JFrame implements ActionListener {
	public JButton btni, btno;
	private JPanel LeftPanel = new JPanel();
	private JPanel MainPanel = new JPanel();
	private JPanel BottomPanel = new JPanel();
	private JPanel CenterPanel = new JPanel();
	private JTextField textField = new JTextField();
	private JTextArea messageArea;
	private JPanel room1, room2, room3, room4, room5, room6;
	private JButton Jbtn1, Jbtn2, Jbtn3, Jbtn4, Jbtn5, Jbtn6;
	JButton jbt1, jbt2, jbt3, jbt4, jbt5, jbt6;
	Vector months;
	JList list;
	JScrollPane jpane;
	JScrollPane jpane2;

	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String myName;
	private static ArrayList<String> nameList;
	JFrame frame = new JFrame("Chatter");

	int[] eachRoomUser = new int[6];
	String[] eachRoomName = new String[6];

	public ChannalClass() {
		for (int i = 0; i < 6; i++) {
			eachRoomUser[i] = 0;
		}
		this.setLayout(null);
		this.setSize(800, 625);
		LeftPanel.setLayout(null);
		LeftPanel.add(new ProfileImagePanel("image1.png"));
		months = new Vector();
		list = new JList(months);
		nameList = new ArrayList<String>();

		// months.addElement("January222");
		// months.addElement("December");
		// months.addElement("January");
		jpane = new JScrollPane(list);
		jpane.setBounds(0, 300, 200, 600);// textarea\
		LeftPanel.add(jpane);

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

		BottomPanel();
		this.add(BottomPanel);

		CenterPanel();
		this.add(CenterPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void BottomPanel() {
		BottomPanel.setLayout(null);

		messageArea = new JTextArea(
				"****************************************************************************************\n"
						+ "****************************** Welcome to chat program! **************************** \n"
						+ "*********************************** Have a nice day~!! ********************************\n"
						+ "****************************************************************************************\n");

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
				// ¿‘∑¬Ω√
				// Ω∫≈©∑—πŸ
				// «◊ªÛ
				// πÿø°
				// ∂ﬂµµ∑œ
				// «œ¥¬∞Õ.
				messageArea.setLineWrap(true);
				textField.setText("");
			}
		});
		// textField.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// String tmp = textField.getText();
		// //messageArea.append(myID+" : "+tmp+"\n");
		// messageArea.append("hyuna" + " : " + tmp + "\n");
		// messageArea.setCaretPosition(messageArea.getText().length());//∏ﬁºº¡ˆ
		// ¿‘∑¬Ω√ Ω∫≈©∑—πŸ «◊ªÛ πÿø° ∂ﬂµµ∑œ «œ¥¬∞Õ.
		// messageArea.setLineWrap(true);
		//
		// //out.println(tmp);
		// textField.setText("");
		//
		// }
		// });

		BottomPanel.setBounds(201, 401, 599, 199);
		BottomPanel.setBackground(Color.lightGray);

	}

	private void CenterPanel() {
		CenterPanel.setLayout(null);
		CenterPanel.setBounds(201, 0, 599, 399);

		room1 = new RoomClass(1, "dsdsd");
		room2 = new RoomClass(2, "방만듬 ");
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

		Jbtn1.setBounds(240, 100, 45, 20);
		Jbtn2.setBounds(540, 100, 45, 20);
		Jbtn3.setBounds(240, 225, 45, 20);
		Jbtn4.setBounds(540, 225, 45, 20);
		Jbtn5.setBounds(240, 350, 45, 20);
		Jbtn6.setBounds(540, 350, 45, 20);

		room1.setVisible(false);
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

		CenterPanel.add(Jbtn1);
		CenterPanel.add(Jbtn2);
		CenterPanel.add(Jbtn3);
		CenterPanel.add(Jbtn4);
		CenterPanel.add(Jbtn5);
		CenterPanel.add(Jbtn6);
		CenterPanel.add(room1);
		CenterPanel.add(room2);
		CenterPanel.add(room3);
		CenterPanel.add(room4);
		CenterPanel.add(room5);
		CenterPanel.add(room6);
		CenterPanel.setBackground(Color.LIGHT_GRAY);

		JPanel dd = new JPanel();
		dd.setLayout(new GridLayout(3, 2));
		jbt1 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));
		jbt2 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));
		jbt3 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));
		jbt4 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));
		jbt5 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));

		jbt6 = new JButton(new ImageIcon(
				((new ImageIcon("button.png").getImage().getScaledInstance(300, 127, java.awt.Image.SCALE_SMOOTH)))));

		jbt1.addActionListener(this);
		jbt2.addActionListener(this);
		jbt3.addActionListener(this);
		jbt4.addActionListener(this);
		jbt5.addActionListener(this);
		jbt6.addActionListener(this);

		jbt1.setBounds(5, 5, 290, 130);
		jbt2.setBounds(305, 5, 290, 130);
		jbt3.setBounds(5, 135, 290, 130);
		jbt4.setBounds(305, 135, 290, 130);
		jbt5.setBounds(5, 265, 290, 130);
		jbt6.setBounds(305, 265, 290, 130);
		CenterPanel.add(jbt1);
		CenterPanel.add(jbt2);
		CenterPanel.add(jbt3);
		CenterPanel.add(jbt4);
		CenterPanel.add(jbt5);
		CenterPanel.add(jbt6);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if (source == Jbtn1) {
			if (eachRoomUser[0] != 4) {
				out.println("change 0 +");
				eachRoomUser[0]++;
				runGame(0);
			}
		} else if (source == Jbtn2) {
			if (eachRoomUser[1] != 4) {
				out.println("change 1 +");
				eachRoomUser[1]++;
				runGame(1);
			}
		} else if (source == Jbtn3) {
			if (eachRoomUser[2] != 4) {
				out.println("change 2 +");
				eachRoomUser[2]++;
				runGame(2);
			}
		} else if (source == Jbtn4) {
			if (eachRoomUser[3] != 4) {
				out.println("change 3 +");
				eachRoomUser[3]++;
				runGame(3);
			}
		} else if (source == Jbtn5) {
			if (eachRoomUser[4] != 4) {
				out.println("change 4 +");
				eachRoomUser[4]++;
				runGame(4);
			}
		} else if (source == Jbtn6) {
			if (eachRoomUser[5] != 4) {
				out.println("change 5 +");
				eachRoomUser[5]++;
				runGame(5);
			}
		}

		else if (source == jbt1) {
			if (eachRoomUser[0] != 4) {
				out.println("change 0 +");
				eachRoomUser[0]++;
				runGame(0);
			}
		} else if (source == jbt2) {
			if (eachRoomUser[1] != 4) {
				out.println("change 1 +");
				eachRoomUser[1]++;
				runGame(1);
			}
		} else if (source == jbt3) {
			if (eachRoomUser[2] != 4) {
				out.println("change 2 +");
				eachRoomUser[2]++;
				runGame(2);
			}
		} else if (source == jbt4) {
			if (eachRoomUser[3] != 4) {
				out.println("change 3 +");
				eachRoomUser[3]++;
				runGame(3);
			}
		} else if (source == jbt5) {
			if (eachRoomUser[4] != 4) {
				out.println("change 4 +");
				eachRoomUser[4]++;
				runGame(4);
			}
		} else if (source == jbt6) {
			if (eachRoomUser[5] != 4) {
				out.println("change 5 +");
				eachRoomUser[5]++;
				runGame(5);
			}
		}

	}

	private void runGame(int room) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				GameroomUI gameroom = new GameroomUI(socket, room);
			}

		});
		thread.start();
		this.setVisible(false);
	}

	private String gettUsername() {
		return JOptionPane.showInputDialog(frame, "Choose a screen name:", "Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void getError() {
		JOptionPane.showMessageDialog(null, "input Error", "wrong input", JOptionPane.ERROR_MESSAGE);
	}

	private void run() throws IOException {
		socket = new Socket("LocalHost", 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		while (true) {
			// jpane.repaint();
			String input = in.readLine();

			if (input.startsWith("SUBMITNAME")) {
				myName = gettUsername();
				out.println(myName);

				input = in.readLine();
				if (input.equals("Error")) {
					getError();
				}
			} else if (input.startsWith("NAMEACCEPTED")) {
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

				System.out.println(input);
				String[] str = input.split(" ");
				roomCheck(Integer.parseInt(str[1]), str[2]);
			}

			else if (input.startsWith("redispose")) {
				if (this.isVisible() == false) {
					String[] tmp = input.split(" ");
					eachRoomUser[0] = Integer.parseInt(tmp[2]);
					eachRoomUser[1] = Integer.parseInt(tmp[3]);
					eachRoomUser[2] = Integer.parseInt(tmp[4]);
					eachRoomUser[3] = Integer.parseInt(tmp[5]);
					eachRoomUser[4] = Integer.parseInt(tmp[6]);
					eachRoomUser[5] = Integer.parseInt(tmp[7]);
					System.out.println(eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);
					roomCheck(Integer.parseInt(tmp[1]), "-");
					out.println("change "+Integer.parseInt(tmp[1])+" -");
					this.setVisible(true);
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
			}
		}
	}

	private void roomCheck(int room, String addorminus) {
		if (addorminus.equals("+")) {
			System.out.println(myName +" 플러스 1 : "+ eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

			if (room == 0) {
				if (eachRoomUser[0] == 0) {
					eachRoomName[0] = "0000";
					room1 = new RoomClass(1, "roomname1");
					jbt1.setVisible(false);
				} else {
					room1 = new RoomClass(eachRoomUser[0] + 1, "roomname1");
				}
				eachRoomUser[0]++;
				room1.setBounds(5, 8, 290, 125);
				CenterPanel.add(room1);
				room1.repaint();
				room1.setVisible(true);
				Jbtn1.setVisible(true);
			}
			if (room == 1) {
				if (eachRoomUser[1] == 0) {
					eachRoomName[1] = "1111";
					room2 = new RoomClass(1, "roomname2");
					jbt2.setVisible(false);
				} else {
					room2 = new RoomClass(eachRoomUser[1] + 1, "roomname2");
				}
				eachRoomUser[1]++;
				room2.setBounds(305, 8, 290, 125);
				CenterPanel.add(room2);
				room2.repaint();
				room2.setVisible(true);
				Jbtn2.setVisible(true);

			}
			if (room == 2) {
				if (eachRoomUser[2] == 0) {
					eachRoomName[2] = "2222";
					room3 = new RoomClass(1, "roomname3");
					jbt3.setVisible(false);
				} else {
					room3 = new RoomClass(eachRoomUser[2] + 1, "roomname3");
				}
				eachRoomUser[2]++;
				room3.setBounds(5, 138, 290, 125);
				CenterPanel.add(room3);
				room3.repaint();
				room3.setVisible(true);
				Jbtn3.setVisible(true);

			}
			if (room == 3) {
				if (eachRoomUser[3] == 0) {
					eachRoomName[3] = "3333";
					room4 = new RoomClass(1, "roomname4");

					jbt4.setVisible(false);

				} else {
					room4 = new RoomClass(eachRoomUser[3] + 1, "roomname4");
				}
				eachRoomUser[3]++;
				room4.setBounds(305, 138, 290, 125);
				CenterPanel.add(room4);
				room4.repaint();
				room4.setVisible(true);
				Jbtn4.setVisible(true);

			}
			if (room == 4) {
				if (eachRoomUser[4] == 0) {
					eachRoomName[4] = "4444";
					room5 = new RoomClass(1, "roomname5");
					jbt5.setVisible(false);

				} else {
					room5 = new RoomClass(eachRoomUser[4] + 1, "roomname6");
				}
				eachRoomUser[4]++;
				room5.setBounds(5, 268, 290, 125);
				CenterPanel.add(room5);
				room5.repaint();
				room5.setVisible(true);
				Jbtn5.setVisible(true);

			}
			if (room == 5) {
				if (eachRoomUser[5] == 0) {
					eachRoomName[5] = "5555";
					room6 = new RoomClass(1, "roomname6");
					jbt6.setVisible(false);

				} else {
					room6 = new RoomClass(eachRoomUser[5] + 1, "roomname6");
				}
				eachRoomUser[5]++;
				room6.setBounds(305, 268, 290, 125);
				CenterPanel.add(room6);
				room6.repaint();
				room6.setVisible(true);
				Jbtn6.setVisible(true);

			}
			System.out.println(myName +" 플러스 2 : "+ eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

		} else {
			System.out.println(myName + " 마이너스 1 : " + eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

			if (room == 0) {
				eachRoomUser[0]--;
				if (eachRoomUser[0] == 0) {
					jbt1.setVisible(true);
					room1.setVisible(false);
					Jbtn1.setVisible(false);

				}else{
					room1 = new RoomClass(eachRoomUser[0], "roomname1");
					room1.setBounds(5, 8, 290, 125);
					CenterPanel.add(room1);
					room1.repaint();
					room1.setVisible(true);
					Jbtn1.setVisible(true);
				}

			}
			if (room == 1) {
				eachRoomUser[1]--;
				if (eachRoomUser[1] == 0) {
					jbt2.setVisible(true);
					room2.setVisible(false);
					Jbtn2.setVisible(false);
					frame.repaint();
				}else{
					room2 = new RoomClass(eachRoomUser[1], "roomname2");
					room2.setBounds(305, 8, 290, 125);
					CenterPanel.add(room2);
					room2.repaint();
					room2.setVisible(true);
					Jbtn2.setVisible(true);
				}

			}
			if (room == 2) {
				eachRoomUser[2]--;
				if (eachRoomUser[2] == 0) {
					jbt3.setVisible(true);
					room3.setVisible(false);
					Jbtn3.setVisible(false);
				}else{
					room3 = new RoomClass(eachRoomUser[2], "roomname3");
					room3.setBounds(5, 138, 290, 125);
					CenterPanel.add(room3);
					room3.repaint();
					room3.setVisible(true);
					Jbtn3.setVisible(true);
				}

			}
			if (room == 3) {
				eachRoomUser[3]--;
				if (eachRoomUser[3] == 0) {
					jbt4.setVisible(true);
					room4.setVisible(false);
					Jbtn4.setVisible(false);
				}else{
					room4 = new RoomClass(eachRoomUser[3], "roomname4");
					room4.setBounds(305, 138, 290, 125);
					CenterPanel.add(room4);
					room4.repaint();
					room4.setVisible(true);
					Jbtn4.setVisible(true);
				}

			}
			if (room == 4) {
				eachRoomUser[4]--;
				if (eachRoomUser[4] == 0) {
					jbt5.setVisible(true);
					room5.setVisible(false);
					Jbtn5.setVisible(false);
					frame.repaint();
				}else{
					System.out.println("eachRoomUser[4] :" + eachRoomUser[4]);
					room5 = new RoomClass(eachRoomUser[4], "roomname5");
					room5.setBounds(5, 268, 290, 125);
					CenterPanel.add(room5);
					room5.repaint();
					room5.setVisible(true);
					Jbtn5.setVisible(true);
				}
			}
			if (room == 5) {
				eachRoomUser[5]--;
				if (eachRoomUser[5] == 0) {
					jbt6.setVisible(true);
					room6.setVisible(false);
					Jbtn6.setVisible(false);
				}else{
					room6 = new RoomClass(eachRoomUser[5], "roomname6");
					room6.setBounds(305, 268, 290, 125);
					CenterPanel.add(room6);
					room6.repaint();
					room6.setVisible(true);
					Jbtn6.setVisible(true);
				}
			}
			System.out.println(myName +" 마이너스2 : "+eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);

		}
		//		CenterPanel.repaint();
		//		frame.setVisible(false);
		//		frame.setVisible(true);
	}

	public static void main(String[] arg) {
		ChannalClass a = new ChannalClass();
		try {
			a.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
