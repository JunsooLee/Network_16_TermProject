package Catch;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

public class GameroomUI implements MouseMotionListener, MouseListener, ActionListener {
	public MyGeneralPathOpen line;
	private JPanel background_panel, title_panel, drawing_panel, chat_panel;
	private JLabel user1_label, user2_label, user3_label, user4_label;
	private JLabel user1_name, user2_name, user3_name, user4_name;
	private JPanel user1_panel, user2_panel, user3_panel, user4_panel;
	private JTextField solutionLb;
	private JTextField timerlb = new JTextField();
	private JPanel pentoolPanel;
	private JFrame frame;
	private JLabel title;
	private ImageIcon bg = null ,cc;
	int[] eachUserPoint = new int[4];
	boolean gameState = false , checkNextQize = false , checkGetAnswer = false;
	boolean stakeHolder = false;
	static String timerBuffer; // 04:11:15 등의 경과 시간 문자열이 저장될 버퍼 정의
	static int oldTime; // 타이머가 ON 되었을 때의 시각을 기억하고 있는 변수
	int QUIZCOUNT = 0;
	static int num;
	public static int count = 0;
	int room;
	private int x, y, width, height;
	int userCount;

	JButton c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
	JButton lineBtn, ovalBtn, clearBtn,startBtn, hintBtn;
	JButton chkExit;

	private JTextField textField = new JTextField();
	private JTextArea messageArea;
	JScrollPane jpane2;
	Socket sc;
	BufferedReader in;
	PrintWriter out;
	String []userName = new String[4];
	String myName;

	boolean hintCheck = false;
	public boolean checkexit = false;
	private boolean mouseState;



	Thread t;
	public GameroomUI(Socket sc, int room,String myName) {
		this.sc = sc;
		this.room = room;
		this.myName = myName;
		// 전체 프레임 등록
		bg = new ImageIcon("img/bg2.png");
		frame = new JFrame(){
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, 1000, 800, null);
			}
		};
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


		lineBtn = new JButton(); //연필
		ovalBtn = new JButton(); //굵은 팬
		clearBtn = new JButton(); //초기화
		chkExit = new JButton(); //나가기
		hintBtn = new JButton(new ImageIcon(
				((new ImageIcon("img/hint.png").getImage().getScaledInstance(180, 80, java.awt.Image.SCALE_SMOOTH)))));
		startBtn = new JButton(new ImageIcon(
				((new ImageIcon("img/start.png").getImage().getScaledInstance(180, 80, java.awt.Image.SCALE_SMOOTH)))));
		lineBtn = new JButton(new ImageIcon(
				((new ImageIcon("img/pencil2.png").getImage().getScaledInstance(70, 40, java.awt.Image.SCALE_SMOOTH)))));
		ovalBtn = new JButton(new ImageIcon(
				((new ImageIcon("img/bold_pencil2.png").getImage().getScaledInstance(70, 40, java.awt.Image.SCALE_SMOOTH)))));
		clearBtn = new JButton(new ImageIcon(
				((new ImageIcon("img/reset2.png").getImage().getScaledInstance(70, 40, java.awt.Image.SCALE_SMOOTH)))));
		chkExit= new JButton(new ImageIcon(
				((new ImageIcon("img/exit2.png").getImage().getScaledInstance(160, 60, java.awt.Image.SCALE_SMOOTH)))));


		lineBtn.setBorderPainted(false);
		ovalBtn.setBorderPainted(false);
		clearBtn.setBorderPainted(false);
		chkExit.setBorderPainted(false);
		startBtn.setBorderPainted(false);
		hintBtn.setBorderPainted(false);

		lineBtn.setContentAreaFilled(false);
		ovalBtn.setContentAreaFilled(false);
		clearBtn.setContentAreaFilled(false);
		chkExit.setContentAreaFilled(false);
		startBtn.setContentAreaFilled(false);
		hintBtn.setContentAreaFilled(false);

		// 버튼 이벤트 핸들러 장착
		lineBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		ovalBtn.addActionListener(this);
		chkExit.addActionListener(this);
		startBtn.addActionListener(this);
		hintBtn.addActionListener(this);
		// 배경 패널 생성
		bg = new ImageIcon("img/bg2.png");
		background_panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, 1000, 800, null);
			}
		};
		line = new MyGeneralPathOpen();


		chat_panel = new JPanel();
		chat_panel();
		drawing_panel = new JPanel();

		user1_name = new JLabel();
		user2_name = new JLabel();
		user3_name = new JLabel();
		user4_name = new JLabel();

		solutionLb = new JTextField();
		solutionLb.setVisible(false);
		solutionLb.setHorizontalAlignment(JLabel.CENTER);
		solutionLb.setEditable(false);
		textField.setEditable(true);
		solutionLb.setBackground(Color.white);

		timerlb.setVisible(false);
		timerlb.setEditable(false);
		timerlb.setBackground(Color.white);


		user1_name.setVisible(false);
		user2_name.setVisible(false);
		user3_name.setVisible(false);
		user4_name.setVisible(false);
		user1_name.setBounds(88, 295 ,100, 20);
		user2_name.setBounds(820 ,295 ,100, 20);
		user3_name.setBounds(88, 550 ,100, 20);
		user4_name.setBounds(820 ,550 ,100, 20);
		solutionLb.setBounds(80 ,30 , 100, 20);
		timerlb.setBounds(810 ,30 , 100, 20);

		hintBtn.setBounds(50, 600, 140, 50);
		//hintBtn.setBackground(Color.ORANGE);
		hintBtn.setVisible(false);
		user1_name.setBackground(Color.orange);
		user2_name.setBackground(Color.orange);
		user3_name.setBackground(Color.orange);
		user4_name.setBackground(Color.orange);
		user1_name.setOpaque(true);
		user2_name.setOpaque(true);
		user3_name.setOpaque(true);
		user4_name.setOpaque(true);

		user1_panel = new JPanel();
		user2_panel = new JPanel();
		user3_panel = new JPanel();
		user4_panel = new JPanel();

		pentoolPanel = new JPanel();
		pentoolPanel.setBounds(300, 430, 400, 50);
		pentoolPanel.setBackground(new Color(150, 207, 239));
		pentoolPanel.setLayout(null);
		lineBtn.setBounds(180,5,64,50);  //여기
		clearBtn.setBounds(320,5,64,50);
		ovalBtn.setBounds(250,5,64,50);
		pentoolPanel.add(lineBtn);
		pentoolPanel.add(clearBtn);
		pentoolPanel.add(ovalBtn);

		c1 = new JButton();
		c2 = new JButton();
		c3 = new JButton();
		c4 = new JButton();
		c5 = new JButton();
		c6 = new JButton();
		c7 = new JButton();
		c8 = new JButton();
		c9 = new JButton();
		c10 = new JButton();
		c1.setBackground(new Color(224, 255, 202));
		c2.setBackground(new Color(239, 90, 90));
		c3.setBackground(new Color(255, 200, 0));
		c4.setBackground(new Color(255, 255, 0));
		c5.setBackground(new Color(121, 142, 121));
		c6.setBackground(new Color(103, 103, 232));
		c7.setBackground(new Color(0, 255, 255));
		c8.setBackground(new Color(177, 110, 110));
		c9.setBackground(new Color(128, 128, 128));
		c10.setBackground(new Color(0, 0, 0));


		c1.setOpaque(true);
		c2.setOpaque(true);
		c3.setOpaque(true);
		c4.setOpaque(true);
		c5.setOpaque(true);
		c6.setOpaque(true);
		c7.setOpaque(true);
		c8.setOpaque(true);
		c9.setOpaque(true);
		c10.setOpaque(true);

		c1.setBorderPainted(false);
		c2.setBorderPainted(false);
		c3.setBorderPainted(false);
		c4.setBorderPainted(false);
		c5.setBorderPainted(false);
		c6.setBorderPainted(false);
		c7.setBorderPainted(false);
		c8.setBorderPainted(false);
		c9.setBorderPainted(false);
		c10.setBorderPainted(false);
		c1.setBounds(20, 16, 15, 15);
		c2.setBounds(35, 16, 15, 15);
		c3.setBounds(50, 16, 15, 15);
		c4.setBounds(65, 16, 15, 15);
		c5.setBounds(80, 16, 15, 15);
		c6.setBounds(95, 16, 15, 15);
		c7.setBounds(110, 16, 15, 15);
		c8.setBounds(125, 16, 15, 15);
		c9.setBounds(140, 16, 15, 15);
		c10.setBounds(155, 16, 15, 15);

		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);
		c5.addActionListener(this);
		c6.addActionListener(this);
		c7.addActionListener(this);
		c8.addActionListener(this);
		c9.addActionListener(this);
		c10.addActionListener(this);

		pentoolPanel.add(c1);
		pentoolPanel.add(c2);
		pentoolPanel.add(c3);
		pentoolPanel.add(c4);
		pentoolPanel.add(c5);
		pentoolPanel.add(c6);
		pentoolPanel.add(c7);
		pentoolPanel.add(c8);
		pentoolPanel.add(c9);
		pentoolPanel.add(c10);
		drawing_panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		drawing_panel.setLayout(new BorderLayout());

		// 패널들의 위치 조정


		user1_panel.setBounds(63, 100, 151, 173);
		user1_panel.setVisible(true);
		user1_panel.setLayout(null);
		user2_panel.setBounds(793, 100, 151, 173);
		user2_panel.setVisible(true);
		user2_panel.setLayout(null);
		user3_panel.setBounds(60, 352, 151, 175);
		user3_panel.setVisible(true);
		user3_panel.setLayout(null);
		user4_panel.setBounds(790, 352, 151, 175);
		user4_panel.setVisible(true);
		user4_panel.setLayout(null);


		chkExit.setBounds(665, 670, 400, 60);
		startBtn.setBounds(650, 580, 430, 80);

		// 각 그리기 도구 이벤트 핸들러 장착
		line.addMouseListener(this);
		line.addMouseMotionListener(this);
		drawing_panel.setBounds(300, 80, 400, 350);
		drawing_panel.add(line);
		drawing_panel.setBackground(Color.white);
		drawing_panel.setVisible(true);

		chat_panel.setVisible(true);

		background_panel.setBounds(0, 0, 1000, 800);
		background_panel.setVisible(true);
		background_panel.setLayout(null);
		//		user1_panel.add(user1_label);
		//		user2_panel.add(user2_label);
		//		user3_panel.add(user3_label);
		//		user4_panel.add(user4_label);
		background_panel.add(hintBtn);
		background_panel.add(timerlb);
		background_panel.add(solutionLb);
		background_panel.add(startBtn);
		background_panel.add(user1_name);
		background_panel.add(user2_name);
		background_panel.add(user3_name);
		background_panel.add(user4_name);
		background_panel.add(pentoolPanel);
		background_panel.add(chat_panel);
		background_panel.add(drawing_panel);
		background_panel.add(chkExit);
		background_panel.add(user1_panel);
		background_panel.add(user2_panel);
		background_panel.add(user3_panel);
		background_panel.add(user4_panel);

		frame.add(background_panel);
		frame.setSize(1000, 800);
		frame.setVisible(true);


		try {
			startdraw();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void startdraw() throws IOException {
		frame.setVisible(true);
		in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		out = new PrintWriter(sc.getOutputStream(), true);

		out.println("initialRoom " + room);
		while (true) {
			String input = in.readLine();
			//System.out.println("두번째 패널: " + input);
			if (checkexit == true) {
				frame.dispose();
				break;
			} else {
				if (input == null){

				}
				else if (input.startsWith("setRoom")) {
					String[] str = input.split(" ");
					int userc = Integer.parseInt(str[2]);
					userCount = userc;
					for(int i = 0 ; i < userCount; i++){
						userName[i] = str[i+3];
					}
					changeUserProfile();
				} else if (input.startsWith("roommsg")) {
					// System.out.println("들어왔다?? "+ input.substring(8) );
					messageArea.append(input.substring(8) + "\n");
				} else if (input.startsWith("point")) {
					String[] str = input.split(" ");
					line.pointXY(Integer.parseInt(str[2]), Integer.parseInt(str[3]));
					drawing_panel.add(line);
				} else if (input.startsWith("released")) {
					line.DrawShape();
					drawing_panel.add(line);
				} else if (input.startsWith("clearpanel")) {
					line.clearElement();
					drawing_panel.add(line);
					drawing_panel.repaint();
				} else if (input.startsWith("mode")) {
					String[] str = input.split(" ");
					line.changePanMode(Integer.parseInt(str[2]));
					drawing_panel.add(line);
				}else if(input.startsWith("getAnswer")){
					//checkGetAnswer
					String[] str = input.split(" ");
					QUIZCOUNT = Integer.parseInt(str[3]);
					stakeHolder = true;

					//System.out.println(myName +" 문제 그리고 번호  :" + str[2] + " "+(QUIZCOUNT+1) );
					solutionLb.setText((QUIZCOUNT+1) +"ST : " + str[2]);
					solutionLb.repaint();

				}
				else if (input.startsWith("convertUserInfo")) {


					String[] str = input.split(" ");
					int userc = Integer.parseInt(str[2]);
					userCount = userc;
					System.out.println("userCount  : "+ userCount);
					for(int i = 0 ; i < userCount; i++){
						userName[i] = str[i+3];
					}
					changeUserProfile();
				}else if (input.startsWith("RequestGameStart")) {
					int n= JOptionPane.showConfirmDialog(
							frame,
							"Do you want play a game?",
							"Question",
							JOptionPane.YES_NO_OPTION);
					if( n == 0){
						out.println("RequestGameStart "+ room + " 1");
					}
					else{
						out.println("RequestGameStart "+ room + " 2");
					}

				}
				else if(input.startsWith("showHint")){ // 11
					String sub = input.substring(11);
					String starCount="";
					for(int i = 0 ; i < sub.length()-1  ; i++)
						starCount+="*";
					solutionLb.setText(sub.substring(0, 1) + starCount);
					solutionLb.repaint();
				}
				else if (input.startsWith("GameStart")) {


					startBtn.setVisible(false);
					solutionLb.setVisible(true);
					timerlb.setVisible(true);
					chkExit.setVisible(false);
					hintBtn.setVisible(true);
					gameState = true;
					user1_name.setBackground(Color.RED);
					QUIZCOUNT = 0;
					hintCheck = true;
					for(int i = 0 ; i < 4 ; i ++){
						eachUserPoint[i] =0;
					}

					t = new Thread(new Runnable() {
						int timeCount;
						@Override
						public void run()
						{
							oldTime = (int) System.currentTimeMillis() / 1000;
							while(true){
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								timeCount = stopwatch();
								timerlb.setText(getTimerString(oldTime+60 -  ((int) System.currentTimeMillis() / 1000)));
								if(timeCount > 60){
									if(stakeHolder){
										timerlb.setText("");
										stakeHolder =false;
										out.println("timeOut " + room + " "+ QUIZCOUNT);
									}

									break;
								}
							}
						}
					});
					t.start();
				}else if(input.startsWith("colorChange")){
					String[] str = input.split(" ");
					line.setgColor(str[2]);
					drawing_panel.add(line);
				}
				//e.println("collect " + tmproom + " " +name);
				else if(input.startsWith("collect")){
					String[] str = input.split(" ");
					t.stop();
					//t.destroy();
					for(int i = 0 ; i < userCount ; i++ ){
						if(userName[i].equals(str[2])){
							eachUserPoint[i] += 50;
							break;
						}
					}



					int pp = QUIZCOUNT % userCount;
					eachUserPoint[pp] += 30;

					System.out.println("---------------");
					System.out.println("맞춘유저 : "+ str[2] + "지금유저:" +myName );
					for(int i = 0 ; i < 4 ; i++){
						System.out.println("유저 "+ (i+1) + " : " + eachUserPoint[i] );
					}
					out.println("Restart "+room + " " + QUIZCOUNT );
					System.out.println("실행됌  :" +myName);
					stakeHolder =false;
					solutionLb.setText("");
					solutionLb.repaint();
				}

				else if(input.startsWith("nextQuiz")){
					//stakeHolder

					//checkNextQize
					String[] str = input.split(" ");
					QUIZCOUNT = Integer.parseInt(str[2]);
					if(QUIZCOUNT % userCount == 0){
						user1_name.setBackground(Color.RED);
						user2_name.setBackground(Color.ORANGE);
						user3_name.setBackground(Color.ORANGE);
						user4_name.setBackground(Color.ORANGE);
					}else if(QUIZCOUNT % userCount == 1){
						user1_name.setBackground(Color.ORANGE);
						user2_name.setBackground(Color.RED);
						user3_name.setBackground(Color.ORANGE);
						user4_name.setBackground(Color.ORANGE);
					}else if(QUIZCOUNT % userCount == 2){
						user1_name.setBackground(Color.ORANGE);
						user2_name.setBackground(Color.ORANGE);
						user3_name.setBackground(Color.RED);
						user4_name.setBackground(Color.ORANGE);
					}else if(QUIZCOUNT % userCount == 3){
						user1_name.setBackground(Color.ORANGE);
						user2_name.setBackground(Color.ORANGE);
						user3_name.setBackground(Color.ORANGE);
						user4_name.setBackground(Color.RED);
					}
					t = new Thread(new Runnable() {
						int timeCount;
						@Override
						public void run()
						{
							oldTime = (int) System.currentTimeMillis() / 1000;
							while(true){
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								timeCount = stopwatch();
								timerlb.setText(getTimerString(oldTime+60 -  ((int) System.currentTimeMillis() / 1000)));
								if(timeCount > 60){
									if(stakeHolder){
										timerlb.setText("");
										stakeHolder=false;
										out.println("timeOut " + room + " "+ QUIZCOUNT);
									}
									break;
								}
							}
						}
					});
					t.start();
					if(!stakeHolder){
						solutionLb.setText("");
						solutionLb.repaint();
					}

				}
				else if(input.startsWith("gameResult")){
					user1_name.setBackground(Color.ORANGE);
					user2_name.setBackground(Color.ORANGE);
					user3_name.setBackground(Color.ORANGE);
					user4_name.setBackground(Color.ORANGE);
					solutionLb.setText("");
					solutionLb.repaint();
					gameState = false;
					stakeHolder = false;
					solutionLb.setVisible(false);
					startBtn.setVisible(true);
					chkExit.setVisible(true);
					hintBtn.setVisible(false);
					QUIZCOUNT= 0;
					hintCheck = false;
					timerlb.setVisible(false);
					if(userCount == 2){
						ResultClass a = new ResultClass (eachUserPoint[0],eachUserPoint[1] , userName[0], userName[1]);
						a.setVisible(true);
					}else if(userCount == 3){
						ResultClass a = new ResultClass(eachUserPoint[0],eachUserPoint[1],eachUserPoint[2] , userName[0], userName[1],userName[2]);
						a.setVisible(true);
					}else if(userCount == 4){
						ResultClass a = new ResultClass(eachUserPoint[0],eachUserPoint[1],eachUserPoint[2],eachUserPoint[3] , userName[0], userName[1],userName[2],userName[3]);
						a.setVisible(true);
					}
					for(int i = 0 ; i < userCount ; i++){
						System.out.println("유저 "+ (i+1) + " : " + eachUserPoint[i] );
						eachUserPoint[i] = 0;
					}
					//TODO 데이터베이스 저장
				}
				else{
					out.println("retrans " +input);
				}
			}
		}
	}


	public static int stopwatch() {
		secToHHMMSS(  ((int) System.currentTimeMillis() / 1000) - oldTime  );
		return (int) System.currentTimeMillis() / 1000 - oldTime;
	}

	// 정수로 된 시간을 초단위(sec)로 입력 받아, "04:11:15" 등의 형식의 문자열로 시분초를 저장
	public static String secToHHMMSS(int secs) {
		int hour, min, sec;

		sec  = secs % 60;
		min  = secs / 60 % 60;
		hour = secs / 3600;

		timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
		return timerBuffer;
	}
	public static String getTimerString(int secs){
		int min, sec;

		sec  = secs % 60;
		min  = secs / 60 % 60;


		return timerBuffer = String.format("%02d:%02d", min, sec);
	}
	private void changeUserProfile() {
		//		user1_panel.removeAll();
		//		user2_panel.removeAll();
		//		user3_panel.removeAll();
		//		user4_panel.removeAll();

		user1_label = new SetLabelImg("img/image1.png");
		user2_label = new SetLabelImg("img/image1.png");
		user3_label = new SetLabelImg("img/image1.png");
		user4_label = new SetLabelImg("img/image1.png");
		user1_label.setBounds(0, 0, 50, 50);
		user2_label.setBounds(0, 0, 151, 173);
		user3_label.setBounds(0, 0, 151, 175);
		user4_label.setBounds(0, 0, 151, 175);
		if (userCount == 1) {
			user1_label = new SetLabelImg("img/image1.png");
			user1_label.setBounds(0, 0, 151, 173);
			user1_label.setVisible(true);
			user1_panel.add(user1_label);
			user1_panel.revalidate();
			user1_panel.repaint();
			user1_panel.setVisible(true);

			user1_name.setText(userName[0]);
			user1_name.setVisible(true);


			user2_panel.setVisible(false);
			user3_panel.setVisible(false);
			user4_panel.setVisible(false);
			user2_name.setVisible(false);
			user3_name.setVisible(false);
			user4_name.setVisible(false);

		} else if (userCount == 2) {
			user1_label = new SetLabelImg("img/img2.png");
			user1_label.setBounds(0, 0, 151, 173);
			user1_label.setVisible(true);
			user1_panel.add(user1_label);
			user1_panel.setVisible(true);

			user1_name.setText(userName[0]);
			user1_name.setVisible(true);

			user2_label = new SetLabelImg("img/image1.png");
			user2_label.setBounds(0, 0, 151, 173);
			user2_label.setVisible(true);
			user2_panel.add(user2_label);
			user2_panel.setVisible(true);
			user1_panel.repaint();
			user2_panel.repaint();

			user2_name.setText(userName[1]);
			user2_name.setVisible(true);

			user3_panel.setVisible(false);
			user4_panel.setVisible(false);
			user3_name.setVisible(false);
			user4_name.setVisible(false);

		} else if (userCount == 3) {
			user1_label = new SetLabelImg("img/img2.png");
			user1_label.setBounds(0, 0, 151, 173);
			user1_label.setVisible(true);
			user1_panel.add(user1_label);
			user1_panel.setVisible(true);

			user1_name.setText(userName[0]);
			user1_name.setVisible(true);

			user2_label = new SetLabelImg("img/image1.png");
			user2_label.setBounds(0, 0, 151, 173);
			user2_label.setVisible(true);
			user2_panel.add(user2_label);
			user2_panel.setVisible(true);

			user2_name.setText(userName[1]);
			user2_name.setVisible(true);


			user3_label = new SetLabelImg("img/img4.png");
			user3_label.setBounds(0, 0, 151, 173);
			user3_label.setVisible(true);
			user3_panel.add(user3_label);
			user3_panel.setVisible(true);

			user3_name.setText(userName[2]);
			user3_name.setVisible(true);

			user4_panel.setVisible(false);
			user4_name.setVisible(false);

		} else if (userCount == 4) {
			user1_label = new SetLabelImg("img/image1.png");
			user1_label.setBounds(0, 0, 151, 173);
			user1_label.setVisible(true);
			user1_panel.add(user1_label);
			user1_panel.setVisible(true);

			user1_name.setText(userName[0]);
			user1_name.setVisible(true);

			user2_label = new SetLabelImg("img/img2.png");
			user2_label.setBounds(0, 0, 151, 173);
			user2_label.setVisible(true);
			user2_panel.add(user2_label);
			user2_panel.setVisible(true);

			user2_name.setText(userName[1]);
			user2_name.setVisible(true);


			user3_label = new SetLabelImg("img/img4.png");
			user3_label.setBounds(0, 0, 151, 173);
			user3_label.setVisible(true);
			user3_panel.add(user3_label);
			user3_panel.setVisible(true);
			user3_name.setText(userName[2]);
			user3_name.setVisible(true);

			user4_label = new SetLabelImg("img/img5.png");
			user4_label.setBounds(0, 0, 151, 173);
			user4_label.setVisible(true);
			user4_panel.add(user4_label);
			user4_panel.setVisible(true);
			user4_name.setText(userName[3]);
			user4_name.setVisible(true);
		}


	}

	private void chat_panel() {
		chat_panel.setLayout(null);

		messageArea = new JTextArea();

		textField.setEditable(true);
		messageArea.setEditable(false);

		jpane2 = new JScrollPane(messageArea);
		jpane2.setBounds(5, 0, 390, 170);// textarea¿« Ω∫≈©∑—πŸ∏¶ √ﬂ∞°«— ∞Õ¿«
		// ªÁ¿Ã¡Ó∏¶ ¡ˆ¡§«—¥Ÿ.
		chat_panel.add(jpane2);
		textField.setBounds(3, 170, 393, 22);
		chat_panel.add(textField);

		// Add Listeners
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp = textField.getText();
				// messageArea.append(myID+" : "+tmp+"\n");
				if(gameState == false)
					out.println("roommsg " + room + " 1 " + textField.getText());
				else{
					if(!stakeHolder)
						out.println("roommsg " + room + " 2 " + textField.getText());
				}
				messageArea.setCaretPosition(messageArea.getText().length());// ∏ﬁºº¡ˆ

				messageArea.setLineWrap(true);

				// out.println(tmp);
				textField.setText("");
			}
		});

		chat_panel.setBounds(300, 480, 400, 195);
		chat_panel.setBackground(Color.lightGray);
		chat_panel.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mouseState == true) {

			MyShape drawShape = (MyShape) e.getSource();
			if (e.getSource() == line) {
				x = e.getX();
				y = e.getY();
				drawShape.setDragPaintInfo(x, y, width, height);
			}else{
				width = e.getX() - x;
				height = e.getY() - y;
				drawShape.setDragPaintInfo(x, y, width, height);
			}
			out.println("point " + room + " " + x + " " + y);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			if(gameState == true){

				if(stakeHolder == true){
					x = e.getX();
					y = e.getY();
					mouseState = true;
				}

			}
			else{
				x = e.getX();
				y = e.getY();
				mouseState = true;

			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			MyShape drawShape = (MyShape) e.getSource();
			drawShape.DrawShape();
			out.println("released " + room + " ");
		}
		mouseState = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Dimension d = drawing_panel.getSize();
		drawing_panel.removeAll();

		if (e.getSource() == lineBtn) {
			if(gameState){
				if(stakeHolder){
					drawing_panel.add(line);
					line.changePanMode(1);
					out.println("mode " + room + " 1");
				}
			}
			else{
				drawing_panel.add(line);
				line.changePanMode(1);
				out.println("mode " + room + " 1");
			}
		}
		if (e.getSource() == ovalBtn) {
			if(gameState){
				if(stakeHolder){
					drawing_panel.add(line);
					line.changePanMode(2);
					out.println("mode " + room + " 2");
				}
			}
			else{
				drawing_panel.add(line);
				line.changePanMode(2);
				out.println("mode " + room + " 2");
			}

		} else if (e.getSource() == clearBtn) {
			if(gameState){
				if(stakeHolder){
					line.clearElement();
					drawing_panel.add(line);

					out.println("clearpanel " + room);
				}
			}
			else{
				line.clearElement();
				drawing_panel.add(line);

				out.println("clearpanel " + room);
			}

		} else if (e.getSource() == chkExit) {
			if(!gameState){
				checkexit = true;
				out.println("redispose " + room + " ");
			}
		}
		else if(e.getSource() == startBtn){
			if(userCount != 1)
				out.println("RequestGameStart " + room + " 1");
		}else if(e.getSource() == hintBtn){
			if(stakeHolder  == false && hintCheck == true){
				hintCheck = false;
				out.println("showHint " + room);
			}

		}
		else{
			drawing_panel.add(line);

			boolean a =false;
			if(!gameState){
				a = true;
			}
			else{
				if(stakeHolder)
					a = true;
			}
			if(a){
				int i = 0;
				if(e.getSource() == c1)
					i = 1;
				else if(e.getSource() == c2)
					i = 2;
				else if(e.getSource() == c3)
					i = 3;
				else if(e.getSource() == c4)
					i = 4;
				else if(e.getSource() == c5)
					i = 5;
				else if(e.getSource() == c6)
					i = 6;
				else if(e.getSource() == c7)
					i = 7;
				else if(e.getSource() == c8)
					i = 8;
				else if(e.getSource() == c9)
					i = 9;
				else if(e.getSource() == c10)
					i = 10;
				out.println("colorChange "+ room + " "+i);
			}
		}
		drawing_panel.repaint(); // 이 부분이 안되면 절대 초기화가 되지않는다.
	}

}

class MyShape extends JComponent {
	protected int x, y, width, height;
	protected Shape s;
	protected static ArrayList<Shape> shapeArray = new ArrayList<Shape>();
	protected static ArrayList<Shape> shapeArray2 = new ArrayList<Shape>();

	public MyShape() {
	}

	public void DrawShape() {
	}

	@Override
	public void paint(Graphics g) {
	}

	public void setDragPaintInfo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

class MyGeneralPathOpen extends MyShape {
	int mode = 1;
	static int gColor =10;
	int[] Xpoints;
	int[] Ypoints;
	ArrayList<Integer> XpointsList = new ArrayList<Integer>();
	ArrayList<Integer> YpointsList = new ArrayList<Integer>();
	ArrayList<Integer> lineColor = new ArrayList<Integer>();
	ArrayList<Integer> CycleColor = new ArrayList<Integer>();
	int[] tempColor;
	public MyGeneralPathOpen() {
		s = new GeneralPath();
	}

	@Override
	public void setDragPaintInfo(int x, int y, int width, int height) {
		super.setDragPaintInfo(x, y, width, height);

		XpointsList.add(x);
		YpointsList.add(y);

		Xpoints = new int[XpointsList.size()];
		for (int i = 0; i < XpointsList.size(); ++i) {
			Xpoints[i] = XpointsList.get(i);
		}
		Ypoints = new int[YpointsList.size()];
		for (int i = 0; i < YpointsList.size(); ++i) {
			Ypoints[i] = YpointsList.get(i);
		}
		repaint();
	}

	@Override
	public void DrawShape() {
		if (mode == 1) {
			GeneralPath g = new GeneralPath();
			if(XpointsList.size() == 0){
				XpointsList.add(0);
				YpointsList.add(0);
			}
			//System.out.println("애러가 난다면 여기다." + XpointsList.get(0) +" "+ YpointsList.get(0));


			g.moveTo(XpointsList.get(0), YpointsList.get(0));
			for (int i = 0; i < XpointsList.size(); ++i) {
				g.lineTo(XpointsList.get(i), YpointsList.get(i));
			}
			shapeArray.add(g); // 여기에 모양을 저장해야만 기록이 된다.
			lineColor.add(gColor);
		}
		XpointsList.clear();
		YpointsList.clear();
		Xpoints = new int[XpointsList.size()];
		Ypoints = new int[YpointsList.size()];
		//XpointsList.removeAll(XpointsList); // 이부분을 하지않을시 선이 이어져서 그려지게된다.
		//YpointsList.removeAll(YpointsList);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		if ((Xpoints == null) == false) {

			if (mode == 1) {
				g2.setStroke(new BasicStroke(2));
				if(gColor == 1){
					g2.setColor(new Color(224, 255, 202));
				}else if(gColor == 2){
					g2.setColor(new Color(239, 90, 90));
				}else if(gColor == 3){
					g2.setColor(new Color(255, 200, 0));
				}else if(gColor == 4){
					g2.setColor(new Color(255, 255, 0));
				}else if(gColor == 5){
					g2.setColor(new Color(121, 142, 121));
				}else if(gColor == 6){
					g2.setColor(new Color(103, 103, 232));
				}else if(gColor == 7){
					g2.setColor(new Color(0, 255, 255));
				}else if(gColor == 8){
					g2.setColor(new Color(177, 110, 110));
				}else if(gColor == 9){
					g2.setColor(new Color(128, 128, 128));
				}else if(gColor == 10){
					g2.setColor(new Color(0, 0, 0));
				}
				g2.drawPolyline(Xpoints, Ypoints, Xpoints.length);
			} else {

				if(Xpoints.length != 0){
					s = new Ellipse2D.Float(Xpoints[0], Ypoints[0], 50, 50);
					shapeArray2.add(s);
					CycleColor.add(gColor);
					XpointsList.removeAll(XpointsList); // 이부분을 하지않을시 선이 이어져서
					// 그려지게된다.
					YpointsList.removeAll(YpointsList);
				}
			}
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.cyan);
		int i=0,tpcolor;
		for (Shape s : shapeArray) {
			tpcolor = lineColor.get(i);
			if(tpcolor == 1){
				g2.setColor(new Color(224, 255, 202));
			}else if(tpcolor == 2){
				g2.setColor(new Color(239, 90, 90));
			}else if(tpcolor == 3){
				g2.setColor(new Color(255, 200, 0));
			}else if(tpcolor == 4){
				g2.setColor(new Color(255, 255, 0));
			}else if(tpcolor == 5){
				g2.setColor(new Color(121, 142, 121));
			}else if(tpcolor == 6){
				g2.setColor(new Color(103, 103, 232));
			}else if(tpcolor == 7){
				g2.setColor(new Color(0, 255, 255));
			}else if(tpcolor == 8){
				g2.setColor(new Color(177, 110, 110));
			}else if(tpcolor == 9){
				g2.setColor(new Color(128, 128, 128));
			}else if(tpcolor == 10){
				g2.setColor(new Color(0, 0, 0));
			}
			g2.draw(s);
			i++;
			// g2.fill(s);
		}
		i = 0;
		for (Shape s : shapeArray2) {
			tpcolor = CycleColor.get(i);
			if(tpcolor == 1){
				g2.setColor(new Color(224, 255, 202));
			}else if(tpcolor == 2){
				g2.setColor(new Color(239, 90, 90));
			}else if(tpcolor == 3){
				g2.setColor(new Color(255, 200, 0));
			}else if(tpcolor == 4){
				g2.setColor(new Color(255, 255, 0));
			}else if(tpcolor == 5){
				g2.setColor(new Color(121, 142, 121));
			}else if(tpcolor == 6){
				g2.setColor(new Color(103, 103, 232));
			}else if(tpcolor == 7){
				g2.setColor(new Color(0, 255, 255));
			}else if(tpcolor == 8){
				g2.setColor(new Color(177, 110, 110));
			}else if(tpcolor == 9){
				g2.setColor(new Color(128, 128, 128));
			}else if(tpcolor == 10){
				g2.setColor(new Color(0, 0, 0));
			}
			g2.fill(s);
			i++;
		}
	}

	// 이부분은 그리는 사람 이외에 그림을 보는 사람은 이부분을 통해서 그리게된다.
	public void pointXY(int x, int y) {
		XpointsList.add(x);
		YpointsList.add(y);

		Xpoints = new int[XpointsList.size()];
		for (int i = 0; i < XpointsList.size(); ++i) {
			Xpoints[i] = XpointsList.get(i);
		}
		Ypoints = new int[YpointsList.size()];
		for (int i = 0; i < YpointsList.size(); ++i) {
			Ypoints[i] = YpointsList.get(i);
		}
		repaint();
	}

	public void clearElement() {
		shapeArray.clear();
		shapeArray2.clear();
		CycleColor.clear();
		lineColor.clear();
		XpointsList.clear();
		YpointsList.clear();
		Xpoints = null;
		Ypoints = null;
		repaint();
	}
	public void setgColor(String color){
		if(color.equals("1"))
			gColor = 1;
		if(color.equals("2"))
			gColor =2;
		if(color.equals("3"))
			gColor = 3;
		if(color.equals("4"))
			gColor =4;
		if(color.equals("5"))
			gColor= 5;
		if(color.equals("6"))
			gColor =6;
		if(color.equals("7"))
			gColor =7;
		if(color.equals("8"))
			gColor =8;
		if(color.equals("9"))
			gColor =9;
		if(color.equals("10"))
			gColor =10;

	}
	public void changePanMode(int inputmode) {
		mode = inputmode;
		//System.out.println("mode: " + mode);
		XpointsList.clear();
		YpointsList.clear();
		Xpoints = null;
		Ypoints = null;
	}
}