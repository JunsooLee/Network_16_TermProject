package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginService;
 
public class LoginFrame extends JFrame implements ActionListener{
 
    BufferedImage img = null;
    JTextField loginTextField;
    JPasswordField passwordField;
    JButton bt;
 
    // 메인
    public static void main(String[] args) {
        new LoginFrame();
    }
 
    // 생성자
    public LoginFrame() {
        setTitle("Drawer Druwa");
        setSize(1000, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        // 레이아웃 설정
        setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 720);
        layeredPane.setLayout(null);
 
        // 패널1
        
        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("Drawer Druwa(2).png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패");
            System.exit(0);
        }
         
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1000, 720);
         
 
        // 로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setBounds(440, 460, 100, 40);
        layeredPane.add(loginTextField);
        loginTextField.setOpaque(false);
        loginTextField.setForeground(Color.black);
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // 패스워드
        passwordField = new JPasswordField(15);
        passwordField.setBounds(440, 510, 100, 40);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.black);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        layeredPane.add(passwordField);
 
        // 로그인버튼 추가
        bt = new JButton(new ImageIcon("btLogin_hud.png"));
        bt.setBounds(580, 480, 104, 48);
 
        // 버튼 투명처리
        bt.setBorderPainted(false);
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);
        bt.addActionListener(this);
 
        layeredPane.add(bt);
 
        // 마지막 추가들
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
    }
 
    class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, -70, null);
        }
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id = loginTextField.getText();
        char[] pass = passwordField.getPassword();
        String password = new String(pass);
 
        if (id.equals("") || password.equals("")) {
            // 메시지를 날린다.
            JOptionPane.showMessageDialog(null, "Enter a ID or Password");
        } else {
 
            // 로그인 참 거짓 여부를 판단
            boolean existLogin = LoginService.loginTest(id, password);
 
            if (existLogin) {
                // 로그인 성공일 경우
                JOptionPane.showMessageDialog(null, "로그인 성공");
            } else {
                // 로그인 실패일 경우
                JOptionPane.showMessageDialog(null, "로그인 실패");
            }
 
        }
        password = null;
	}
}