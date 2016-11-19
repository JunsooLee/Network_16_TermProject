package Draw_Druwa;

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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChatClient implements MouseMotionListener, MouseListener, ActionListener{
	BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
//    JTextField textField = new JTextField(40);
//    JTextArea messageArea = new JTextArea(8, 40);
//    JButton whisper = new JButton("whisper");
    
    private JFrame frm;
    private JLabel stateLabel;
   
    public MyGeneralPathOpen line;
    private JPanel panel;
    private JPanel btnPanel;
    private JButton lineBtn;
    private JButton ovalBtn;
    private JButton clearBtn;
    private int x, y, width, height;
    private boolean mouseState;

    public ChatClient() {
    	 //��� ����
        frm = new JFrame();
        stateLabel = new JLabel("���� ǥ�� ��");
        
       
        line = new MyGeneralPathOpen();
        
        panel = new JPanel();
        btnPanel = new JPanel();
        lineBtn = new JButton("����");
        ovalBtn = new JButton("������");
        clearBtn = new JButton("�ʱ�ȭ");
        
        mouseState = false;
        
        //��ư��ο� ��ư����
        btnPanel.add(lineBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(ovalBtn);
        
        //��ư �̺�Ʈ �ڵ鷯 ����
        lineBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        ovalBtn.addActionListener(this);
        
        //�� �׸��� ���� �̺�Ʈ �ڵ鷯 ����
        line.addMouseListener(this);
        line.addMouseMotionListener(this);
      
        
        //�׷���� �г� �⺻ ����
        panel.setLayout(new BorderLayout());
        panel.add(line); //�⺻������ ����� �⸮�� ���� ����
        panel.setBackground(Color.white);
       
        //�����ӿ� �г� ����
        frm.add(btnPanel, "North");
        frm.add(panel, "Center");
        frm.add(stateLabel, "South");
        
        //������ �⺻����
        frm.setTitle("�׸��� ����");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(120, 120, 500, 500);
        frm.setVisible(true);
    }
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        if(mouseState == true)
        {
            //System.out.println(e.getButton());
            MyShape drawShape = (MyShape)e.getSource();
            if(e.getSource() == line)
            {
                x = e.getX();
                y = e.getY();
                drawShape.setDragPaintInfo(x, y, width, height);
            }
            width = e.getX() - x;
            height = e.getY() - y;
            drawShape.setDragPaintInfo(x, y, width, height);
            out.println("point "+x +" "+ y);
        }
    }
    public void paintXY(){
    	 //System.out.println(e.getButton());
    }
    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(e.getButton() == 1)
        {
            x = e.getX();
            y = e.getY();
            mouseState = true;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        if(e.getButton() == 1)
        {
            MyShape drawShape = (MyShape)e.getSource();
            drawShape.DrawShape();
            out.println("released");
        }
        mouseState = false;
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Dimension d = panel.getSize();
        panel.removeAll();
        
        if(e.getSource() == lineBtn)
        {
    
            panel.add(line);
            stateLabel.setText("����� �׸��� ���");
            line.changePanMode(1);
            out.println("mode 1");
        }
        if(e.getSource() == ovalBtn)
        {
        	panel.add(line);
        	stateLabel.setText("����� �� �׸��� ���");
        	line.changePanMode(2);
        	out.println("mode 2");
        }
        else if(e.getSource() == clearBtn)
        {
        	line.clearElement();
        	panel.add(line);
            stateLabel.setText("�ʱ�ȭ ���");
            out.println("clearpanel");
        }
        panel.repaint(); // �� �κ��� �ȵǸ� ���� �ʱ�ȭ�� �����ʴ´�.
    }
    
    private String getName() {
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

        while (true) {
            String input = in.readLine();
            if (input.startsWith("SUBMITNAME")) {
                out.println(getName());
                input = in.readLine();
                if(input.equals("Error")){
                	 getError();
                }
            }
            else if(input.startsWith("point")){
            	String []str = input.split(" ");
            	line.pointXY(Integer.parseInt(str[1]), Integer.parseInt(str[2]));	
            	
            	
            }
            else if(input.startsWith("released")){
            	line.DrawShape();
            }
            else if(input.startsWith("clearpanel")){
            	line.clearElement();
            }
            else if(input.startsWith("mode")){
            	String[] str = input.split(" ");
            	line.changePanMode(Integer.parseInt(str[1]));
            	
            }
            
        }
    }
    
    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //client.frame.setVisible(true);
        client.run();
    }
}

class MyShape extends JComponent
{
    protected int x, y, width, height;
    protected  Shape s;
    protected static ArrayList<Shape> shapeArray = new ArrayList<Shape>();
    protected static ArrayList<Shape> shapeArray2 = new ArrayList<Shape>(); 
    public MyShape(){}
    public void DrawShape()    {}
    public void paint(Graphics g){}
    public void setDragPaintInfo(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
 
class MyGeneralPathOpen extends MyShape
{
	int mode =1;
    int[] Xpoints;
    int[] Ypoints;
    ArrayList<Integer> XpointsList = new ArrayList<Integer>();
    ArrayList<Integer> YpointsList = new ArrayList<Integer>();
    
    public MyGeneralPathOpen()
    {
        s = new GeneralPath();
    }
    
    public void setDragPaintInfo(int x, int y, int width, int height)
    {
        super.setDragPaintInfo(x, y, width, height);
        
        XpointsList.add(x);
        YpointsList.add(y);
        
        Xpoints = new int[XpointsList.size()];
        for(int i = 0 ; i < XpointsList.size() ; ++i)
        {
            Xpoints[i] = XpointsList.get(i);
        }
        Ypoints = new int[YpointsList.size()];
        for(int i = 0 ; i < YpointsList.size() ; ++i)
        {
            Ypoints[i] = YpointsList.get(i);
        }
        repaint();
    }
    
    public void DrawShape()
    {   
    	if(mode == 1){
	        GeneralPath g = new GeneralPath();
	        g.moveTo(XpointsList.get(0), YpointsList.get(0));
	        for (int i = 0 ; i < XpointsList.size() ; ++i)
	        {
	            g.lineTo(XpointsList.get(i), YpointsList.get(i));
	        }
	        shapeArray.add(g); // ���⿡ ����� �����ؾ߸� ����� �ȴ�.
    	}
        XpointsList.removeAll(XpointsList); // �̺κ��� ���������� ���� �̾����� �׷����Եȴ�.
        YpointsList.removeAll(YpointsList);
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if( (Xpoints == null) == false )
        {
        	
        	if(mode == 1){
        		g2.setStroke(new BasicStroke(2));
        		g2.setColor(Color.cyan);
        		g2.drawPolyline(Xpoints, Ypoints, Xpoints.length);
        	}
        	else{
        		s = new Ellipse2D.Float(Xpoints[0], Ypoints[0], 50, 50);
        		shapeArray2.add(s);
        		XpointsList.removeAll(XpointsList); // �̺κ��� ���������� ���� �̾����� �׷����Եȴ�.
        	    YpointsList.removeAll(YpointsList);
//        		g2.setColor(Color.RED);
//        		for(int i = 0 ; i < Xpoints.length ; i++){
//        			g2.fillOval(Xpoints[i], Ypoints[i], 50, 50);
//        		}
        	}
        }
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.cyan);
        for(Shape s : shapeArray)
        {
        	g2.draw(s);
//          g2.fill(s);
        }
        g2.setColor(Color.red);
        for(Shape s : shapeArray2)
        {
            g2.fill(s);
        }
    }
    // �̺κ��� �׸��� ��� �̿ܿ� �׸��� ���� ����� �̺κ��� ���ؼ� �׸��Եȴ�.
    public void pointXY(int x, int y){
    	 XpointsList.add(x);
         YpointsList.add(y);
         
         Xpoints = new int[XpointsList.size()];
         for(int i = 0 ; i < XpointsList.size() ; ++i)
         {
             Xpoints[i] = XpointsList.get(i);
         }
         Ypoints = new int[YpointsList.size()];
         for(int i = 0 ; i < YpointsList.size() ; ++i)
         {
             Ypoints[i] = YpointsList.get(i);
         }
         repaint();
    }
    
    public void clearElement(){
    	shapeArray.clear();
    	shapeArray2.clear();
    	XpointsList.clear();
    	YpointsList.clear();
    	Xpoints = null;
    	Ypoints = null;
    	repaint();
    }
    public void changePanMode(int inputmode){
    	mode = inputmode;
    	System.out.println("mode: "+ mode);
    	XpointsList.clear();
    	YpointsList.clear();
    	Xpoints = null;
    	Ypoints = null;
    }
}
