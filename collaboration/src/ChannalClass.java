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
	public JButton btni,btno;
	private JPanel LeftPanel = new JPanel();
	private JPanel MainPanel = new JPanel();
	private JPanel BottomPanel = new JPanel();
	private JPanel CenterPanel = new JPanel();
	private JTextField textField = new JTextField();
	private JTextArea messageArea;
	private JPanel room1,room2,room3,room4,room5,room6;
	private JButton Jbtn1,Jbtn2,Jbtn3,Jbtn4,Jbtn5,Jbtn6;
	JButton jbt1,jbt2,jbt3,jbt4,jbt5,jbt6;
	Vector months;
	JList list;
	JScrollPane jpane;
	JScrollPane jpane2;
	BufferedReader in;
	PrintWriter out;
	String myName;
	private static ArrayList<String> nameList;
	JFrame frame = new JFrame("Chatter");
	public ChannalClass(){

		this.setLayout(null);
		this.setSize(800,625);
		LeftPanel.setLayout(null);
		LeftPanel.add( new ProfileImagePanel("image1.png"));
		months = new Vector();
		list = new JList(months);
		nameList = new ArrayList<String>();

		//		months.addElement("January222");
		//		months.addElement("December");
		//		months.addElement("January");
		jpane = new JScrollPane(list);
		jpane.setBounds(0, 300, 200, 600);//textarea\
		LeftPanel.add(jpane);

		//		frame.add(new JScrollPane(list));
		//		frame.setSize(300,200);
		//		frame.setVisible(true);
		//		btno = new JButton("인풋");
		//		btni = new JButton("아웃");
		//		frame.add(btni,"North");
		//		frame.add(btno,"North");
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


	private void BottomPanel(){
		BottomPanel.setLayout(null);

		messageArea = new JTextArea("****************************************************************************************\n" +
				"****************************** Welcome to chat program! **************************** \n" +
				"*********************************** Have a nice day~!! ********************************\n" +
				"****************************************************************************************\n");

		textField.setEditable(true);
		messageArea.setEditable(false);
		//messageArea.setBounds(5, 170, 460, 130);
		jpane2 = new JScrollPane(messageArea);
		jpane2.setBounds(5, 5, 590, 170);//textarea¿« Ω∫≈©∑—πŸ∏¶ √ﬂ∞°«— ∞Õ¿« ªÁ¿Ã¡Ó∏¶ ¡ˆ¡§«—¥Ÿ.
		BottomPanel.add(jpane2);
		textField.setBounds(5, 178, 590, 19);
		BottomPanel.add(textField);

		// Add Listeners
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("message " + textField.getText());
				//messageArea.append("hyuna" + " : " + textField.getText() + "\n");
				messageArea.setCaretPosition(messageArea.getText().length());//∏ﬁºº¡ˆ ¿‘∑¬Ω√ Ω∫≈©∑—πŸ «◊ªÛ πÿø° ∂ﬂµµ∑œ «œ¥¬∞Õ.
				messageArea.setLineWrap(true);
				textField.setText("");
			}
		});
		//		textField.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				String tmp = textField.getText();
		//				//messageArea.append(myID+" : "+tmp+"\n");
		//				messageArea.append("hyuna" + " : " + tmp + "\n");
		//				messageArea.setCaretPosition(messageArea.getText().length());//∏ﬁºº¡ˆ ¿‘∑¬Ω√ Ω∫≈©∑—πŸ «◊ªÛ πÿø° ∂ﬂµµ∑œ «œ¥¬∞Õ.
		//				messageArea.setLineWrap(true);
		//
		//				//out.println(tmp);
		//				textField.setText("");
		//
		//			}
		//		});


		BottomPanel.setBounds(201, 401, 599, 199);
		BottomPanel.setBackground(Color.lightGray);

	}

	private void CenterPanel(){
		CenterPanel.setLayout(null);
		CenterPanel.setBounds(201, 0, 599, 399);

		room1 = new RoomClass(1,"dsdsd");
		room2 = new RoomClass(2,"방만듬 ");
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
		dd.setLayout(new GridLayout(3,2));
		jbt1 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));
		jbt2 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));
		jbt3 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));
		jbt4 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));
		jbt5 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));

		jbt6 = new JButton(new ImageIcon(((new ImageIcon(
				"button.png").getImage()
				.getScaledInstance(300, 127,
						java.awt.Image.SCALE_SMOOTH)))));
		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				months.removeElement("January");
				jpane.repaint();
			}
		});
		jbt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbt2.isVisible() == false){
					jbt2.setVisible(true);
				}
				else
					jbt2.setVisible(false);
			}
		});
		jbt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbt2.isVisible() == false){
					jbt2.setVisible(true);
					jbt4.setVisible(true);
					room2.setVisible(false);
					room4.setVisible(false);
					Jbtn2.setVisible(false);
					Jbtn4.setVisible(false);
				}
				else{
					jbt2.setVisible(false);
					jbt4.setVisible(false);
					room2.setVisible(true);
					room4.setVisible(true);
					Jbtn2.setVisible(true);
					Jbtn4.setVisible(true);
					//room2.repaint();
					out.println("change");
				}
			}
		});
		jbt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				months.removeElement("January");
				months.addElement("January");
				jpane.repaint();
			}
		});
		jbt5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				months.removeElement("January");
				jpane.repaint();
			}
		});
		jbt6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				months.removeElement("January");
				jpane.repaint();
			}
		});
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
		//		dd.add(jbt1);
		//		dd.add(jbt2);
		//		dd.add(jbt3);
		//		dd.add(jbt4);
		//		dd.add(jbt5);
		//		dd.add(jbt6);
		//
		//		dd.setSize(599,399);
		//		dd.setVisible(true);
		//		CenterPanel.add(dd);



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();

		if( source == Jbtn1){

		}
		else if(source == Jbtn2){
			room2.setVisible(false);
			room4.setVisible(false);
			Jbtn2.setVisible(false);
			Jbtn4.setVisible(false);
		}
		else if(source == Jbtn3){

		}
		else if(source == Jbtn4){

		}
		else if(source == Jbtn5){

		}
		else if(source == Jbtn6){

		}


	}


	private String gettUsername() {
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name:",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void getError() {
		JOptionPane.showMessageDialog(null, "input Error", "wrong input", JOptionPane.ERROR_MESSAGE);
	}

	private void run() throws IOException {
		Socket socket = new Socket("LocalHost", 9001);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		RoomClass a = new RoomClass(socket);
		while (true) {
			//jpane.repaint();
			String input = in.readLine();
			System.out.println(input);
			if (input.startsWith("SUBMITNAME")){
				myName = gettUsername();
				out.println(myName);

				input = in.readLine();
				if(input.equals("Error")){
					getError();
				}
			}
			else if (input.startsWith("NAMEACCEPTED")) {
				textField.setEditable(true);
			}
			else if (input.startsWith("message")) {
				messageArea.append(input.substring(8) + "\n");
			}
			// name assign
			else if (input.startsWith("assign")) {
				months.addElement(input.substring(7));
				list.setListData(months);
			}
			else if (input.startsWith("removename")) {
				months.removeElement(input.substring(11));
				list.setListData(months);
			}
			else if(input.startsWith("change")){
				System.out.println("들어");
				
				room4 = new RoomClass(3,"sdf");
				room4.setBounds(305, 138, 290, 125);
				CenterPanel.add(room4);
				jbt2.setVisible(false);
				jbt4.setVisible(false);
				room2.setVisible(true);
				room4.setVisible(true);
				Jbtn2.setVisible(true);
				Jbtn4.setVisible(true);
				//room2.repaint();

			}
			else if (input.startsWith("alluser")) {
				input = input.trim();

				if(input.length() == 7){

				}
				else{
					String[] tmp = input.split(" ");
					for(int i = 1 ; i <tmp.length ; i++ ){
						months.addElement(tmp[i]);
					}
				}
			}
		}
	}
	public static void main(String[] arg){
		ChannalClass a = new ChannalClass();
		try {
			a.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
