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
 
    // ����
    public static void main(String[] args) {
        new LoginFrame();
    }
 
    // ������
    public LoginFrame() {
        setTitle("Drawer Druwa");
        setSize(1000, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        // ���̾ƿ� ����
        setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 720);
        layeredPane.setLayout(null);
 
        // �г�1
        
        // �̹��� �޾ƿ���
        try {
            img = ImageIO.read(new File("Drawer Druwa(2).png"));
        } catch (IOException e) {
            System.out.println("�̹��� �ҷ����� ����");
            System.exit(0);
        }
         
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1000, 720);
         
 
        // �α��� �ʵ�
        loginTextField = new JTextField(15);
        loginTextField.setBounds(440, 460, 100, 40);
        layeredPane.add(loginTextField);
        loginTextField.setOpaque(false);
        loginTextField.setForeground(Color.black);
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // �н�����
        passwordField = new JPasswordField(15);
        passwordField.setBounds(440, 510, 100, 40);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.black);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        layeredPane.add(passwordField);
 
        // �α��ι�ư �߰�
        bt = new JButton(new ImageIcon("btLogin_hud.png"));
        bt.setBounds(580, 480, 104, 48);
 
        // ��ư ����ó��
        bt.setBorderPainted(false);
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);
        bt.addActionListener(this);
 
        layeredPane.add(bt);
 
        // ������ �߰���
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
            // �޽����� ������.
            JOptionPane.showMessageDialog(null, "Enter a ID or Password");
        } else {
 
            // �α��� �� ���� ���θ� �Ǵ�
            boolean existLogin = LoginService.loginTest(id, password);
 
            if (existLogin) {
                // �α��� ������ ���
                JOptionPane.showMessageDialog(null, "�α��� ����");
            } else {
                // �α��� ������ ���
                JOptionPane.showMessageDialog(null, "�α��� ����");
            }
 
        }
        password = null;
	}
}