import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChannalClass extends JFrame  {
	public JButton btni,btno;
	private JPanel LeftPanel = new JPanel();
	private JPanel MainPanel = new JPanel();
	private JPanel BottomPanel = new JPanel();
	private JPanel CenterPanel = new JPanel();
	private JTextField textField = new JTextField();
	private JTextArea messageArea;

	Vector months;
	JList list;
	JScrollPane jpane;
	JScrollPane jpane2;

	public ChannalClass(){
		this.setLayout(null);
		this.setSize(800,625);
		LeftPanel.setLayout(null);
		LeftPanel.add( new ProfileImagePanel("image1.png"));
		//		JFrame frame = new JFrame();
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		months = new Vector();
		list = new JList(months);
		months.addElement("January222");
		months.addElement("December");
		months.addElement("January");
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
				String tmp = textField.getText();
				//messageArea.append(myID+" : "+tmp+"\n");
				messageArea.append("hyuna" + " : " + tmp + "\n");
				messageArea.setCaretPosition(messageArea.getText().length());//∏ﬁºº¡ˆ ¿‘∑¬Ω√ Ω∫≈©∑—πŸ «◊ªÛ πÿø° ∂ﬂµµ∑œ «œ¥¬∞Õ.
				messageArea.setLineWrap(true);

				//out.println(tmp);
				textField.setText("");

			}
		});


		BottomPanel.setBounds(201, 401, 599, 199);
		BottomPanel.setBackground(Color.lightGray);

	}

	private void CenterPanel(){
		CenterPanel.setLayout(null);
		CenterPanel.setBounds(201, 0, 599, 399);

		JPanel dd = new JPanel();
		dd.setLayout(new GridLayout(3,2));
		JButton jbt1 = new JButton("방만들기1 ");
		JButton jbt2 = new JButton("방만들기2 ");
		JButton jbt3 = new JButton("방만들기3 ");
		JButton jbt4 = new JButton("방만들기4 ");
		JButton jbt5 = new JButton("방만들기5 ");
		JButton jbt6 = new JButton("방만들기6 ");
		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jbt1.setText("sss");
				months.removeElement("January");
			}
		});
		dd.add(jbt1);
		dd.add(jbt2);
		dd.add(jbt3);
		dd.add(jbt4);
		dd.add(jbt5);
		dd.add(jbt6);

		dd.setSize(599,399);
		dd.setVisible(true);
		CenterPanel.add(dd);
		CenterPanel.setBackground(Color.cyan);
	}
	public static void main(String[] arg){
		ChannalClass a = new ChannalClass();



	}
}
