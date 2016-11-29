
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GameroomUI implements MouseMotionListener, MouseListener, ActionListener {
	public MyGeneralPathOpen line;
	private JPanel background_panel, title_panel, drawing_panel, chat_panel;
	private JLabel user1_label, user2_label, user3_label, user4_label;
	private JPanel user1_panel, user2_panel, user3_panel, user4_panel;
	private JPanel pentoolPanel;
	private JFrame frame;
	private JLabel title;
	private ImageIcon bg = null, d = null, r = null, w = null, e = null, a = null, u = null;
	static int num;
	public static int count = 0;
	int room;
	JButton lineBtn, ovalBtn, clearBtn;
	private JTextField textField = new JTextField();
	private JTextArea messageArea;
	JScrollPane jpane2;
	Socket sc;
	BufferedReader in;
	PrintWriter out;

	public boolean checkexit = false;
	JButton chkExit;

	private int x, y, width, height;
	private boolean mouseState;

	public GameroomUI(Socket sc, int room) {
		this.sc = sc;
		this.room = room;
		// 전체 프레임 등록
		frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		lineBtn = new JButton("연필");
		ovalBtn = new JButton("굵은팬");
		clearBtn = new JButton("초기화");
		chkExit = new JButton("나가기 ");

		// 버튼 이벤트 핸들러 장착
		lineBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		ovalBtn.addActionListener(this);
		chkExit.addActionListener(this);

		// 배경 패널 생성
		bg = new ImageIcon("img/whale.jpg");
		background_panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, 1000, 800, null);
			}
		};
		line = new MyGeneralPathOpen();
		title = new JLabel("Drawer Druwa");
		d = new ImageIcon("img/d.png");
		r = new ImageIcon("img/r.png");
		a = new ImageIcon("img/a.png");
		w = new ImageIcon("img/w.png");
		e = new ImageIcon("img/e.png");
		u = new ImageIcon("img/u.png");

		title_panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				// title_panel.add(title);
				g.drawImage(d.getImage(), 120, 20, 20, 30, null);
				g.drawImage(r.getImage(), 145, 20, 20, 30, null);
				g.drawImage(a.getImage(), 160, 20, 20, 30, null);
				g.drawImage(w.getImage(), 185, 20, 20, 30, null);
				g.drawImage(e.getImage(), 200, 20, 20, 30, null);
				// drawer druwa 이쁜 글씨로 띄우기
			}
		};

		chat_panel = new JPanel();
		chat_panel();
		drawing_panel = new JPanel();

		user1_label = new SetLabelImg("image1.png");
		user2_label = new SetLabelImg("image1.png");
		user3_label = new SetLabelImg("image1.png");
		user4_label = new SetLabelImg("image1.png");

		user1_panel = new JPanel();
		user2_panel = new JPanel();
		user3_panel = new JPanel();
		user4_panel = new JPanel();

		pentoolPanel = new JPanel();
		pentoolPanel.setBounds(300, 430, 400, 50);
		pentoolPanel.setBackground(Color.white);
		pentoolPanel.add(lineBtn);
		pentoolPanel.add(clearBtn);
		pentoolPanel.add(ovalBtn);

		// 두께
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

		// 패널들의 위치 조정
		title_panel.setBounds(0, 0, 1000, 80);
		title_panel.setVisible(true);

		user1_panel.setBounds(50, 100, 200, 200);
		user1_panel.setVisible(true);
		user2_panel.setBounds(50, 320, 200, 200);
		user2_panel.setVisible(true);
		user3_panel.setBounds(750, 100, 200, 200);
		user3_panel.setVisible(true);
		user4_panel.setBounds(750, 320, 200, 200);
		user4_panel.setVisible(true);

		user1_label.setBounds(50, 100, 200, 200);
		user1_label.setVisible(false);
		user2_label.setBounds(50, 320, 200, 200);
		user2_label.setVisible(false);
		user3_label.setBounds(750, 100, 200, 200);
		user3_label.setVisible(false);
		user4_label.setBounds(750, 320, 200, 200);
		user4_label.setVisible(false);
		chkExit.setBounds(750, 550, 200, 50);

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

		frame.add(pentoolPanel);
		frame.add(chat_panel);
		frame.add(drawing_panel);
		frame.add(user1_panel);
		frame.add(user2_panel);
		frame.add(user3_panel);
		frame.add(user4_panel);
		frame.add(title_panel);
		frame.add(background_panel);
		frame.add(chkExit);
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
			String input = in.readLine(); // º≠πˆø°º≠ πﬁ¿∫ πÆ¿Âø°º≠ √ππ¯¬∞
			// ∫Œ∫–¿ª ∫æ¥œ¥Ÿ.
			if (checkexit == true) {
				frame.dispose();
				break;
			}
			if (input.startsWith("setRoom")) {
				String[] str = input.split(" ");
				int userc = Integer.parseInt(str[1]);
				changeUserProfile(userc);
			}

			else if (input.startsWith("point")) {
				String[] str = input.split(" ");
				line.pointXY(Integer.parseInt(str[1]), Integer.parseInt(str[2]));

			} else if (input.startsWith("released")) {
				line.DrawShape();
			} else if (input.startsWith("clearpanel")) {
				line.clearElement();
			} else if (input.startsWith("mode")) {
				String[] str = input.split(" ");
				line.changePanMode(Integer.parseInt(str[1]));

			}
			// 유저가 들어왔을때 ㅇㅇ
			else if (input.startsWith("changeUserInfo")) {
				String[] tmp = input.split(" "); // first: userCount else :
				// userName
				changeUserProfile(2);
			}

		}
	}

	private void changeUserProfile(int urCount) {
		if (count == 1) {
			user1_panel.setVisible(false);
			user1_label.setVisible(true);
			user2_panel.setVisible(true);
			user2_label.setVisible(false);
			user3_panel.setVisible(true);
			user3_label.setVisible(false);
			user4_panel.setVisible(true);
			user4_label.setVisible(false);
		} else if (count == 2) {
			user1_panel.setVisible(false);
			user1_label.setVisible(true);
			user2_panel.setVisible(false);
			user2_label.setVisible(true);
			user3_panel.setVisible(true);
			user3_label.setVisible(false);
			user4_panel.setVisible(true);
			user4_label.setVisible(false);
		} else if (count == 3) {
			user1_panel.setVisible(false);
			user1_label.setVisible(true);
			user2_panel.setVisible(false);
			user2_label.setVisible(true);
			user3_panel.setVisible(false);
			user3_label.setVisible(true);
			user4_panel.setVisible(true);
			user4_label.setVisible(false);
		} else if (count == 4) {
			user1_panel.setVisible(false);
			user1_label.setVisible(true);
			user2_panel.setVisible(false);
			user2_label.setVisible(true);
			user3_panel.setVisible(false);
			user3_label.setVisible(true);
			user4_panel.setVisible(false);
			user4_label.setVisible(true);
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
				messageArea.append("hyuna" + " : " + tmp + "\n");
				messageArea.setCaretPosition(messageArea.getText().length());// ∏ﬁºº¡ˆ
				// ¿‘∑¬Ω√
				// Ω∫≈©∑—πŸ
				// «◊ªÛ
				// πÿø°
				// ∂ﬂµµ∑œ
				// «œ¥¬∞Õ.
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
			// System.out.println(e.getButton());
			MyShape drawShape = (MyShape) e.getSource();
			if (e.getSource() == line) {
				x = e.getX();
				y = e.getY();
				drawShape.setDragPaintInfo(x, y, width, height);
			}
			width = e.getX() - x;
			height = e.getY() - y;
			drawShape.setDragPaintInfo(x, y, width, height);
			out.println("point " + x + " " + y);
		}
	}

	public void paintXY() {
		// System.out.println(e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			x = e.getX();
			y = e.getY();
			mouseState = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			MyShape drawShape = (MyShape) e.getSource();
			drawShape.DrawShape();
			out.println("released");
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
			drawing_panel.add(line);
			line.changePanMode(1);
			out.println("mode 1");
		}
		if (e.getSource() == ovalBtn) {
			drawing_panel.add(line);

			line.changePanMode(2);
			out.println("mode 2");
		} else if (e.getSource() == clearBtn) {
			line.clearElement();
			drawing_panel.add(line);

			out.println("clearpanel");
		} else if (e.getSource() == chkExit) {
			checkexit = true;
			out.println("redispose " + room);
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
	int[] Xpoints;
	int[] Ypoints;
	ArrayList<Integer> XpointsList = new ArrayList<Integer>();
	ArrayList<Integer> YpointsList = new ArrayList<Integer>();

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
			g.moveTo(XpointsList.get(0), YpointsList.get(0));
			for (int i = 0; i < XpointsList.size(); ++i) {
				g.lineTo(XpointsList.get(i), YpointsList.get(i));
			}
			shapeArray.add(g); // 여기에 모양을 저장해야만 기록이 된다.
		}
		XpointsList.removeAll(XpointsList); // 이부분을 하지않을시 선이 이어져서 그려지게된다.
		YpointsList.removeAll(YpointsList);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		if ((Xpoints == null) == false) {

			if (mode == 1) {
				g2.setStroke(new BasicStroke(2));
				g2.setColor(Color.cyan);
				g2.drawPolyline(Xpoints, Ypoints, Xpoints.length);
			} else {
				s = new Ellipse2D.Float(Xpoints[0], Ypoints[0], 50, 50);
				shapeArray2.add(s);
				XpointsList.removeAll(XpointsList); // 이부분을 하지않을시 선이 이어져서
				// 그려지게된다.
				YpointsList.removeAll(YpointsList);
				// g2.setColor(Color.RED);
				// for(int i = 0 ; i < Xpoints.length ; i++){
				// g2.fillOval(Xpoints[i], Ypoints[i], 50, 50);
				// }
			}
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.cyan);
		for (Shape s : shapeArray) {
			g2.draw(s);
			// g2.fill(s);
		}
		g2.setColor(Color.red);
		for (Shape s : shapeArray2) {
			g2.fill(s);
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
		XpointsList.clear();
		YpointsList.clear();
		Xpoints = null;
		Ypoints = null;
		repaint();
	}

	public void changePanMode(int inputmode) {
		mode = inputmode;
		System.out.println("mode: " + mode);
		XpointsList.clear();
		YpointsList.clear();
		Xpoints = null;
		Ypoints = null;
	}
}