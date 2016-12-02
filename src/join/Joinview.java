package join;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
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

public class Joinview extends JFrame implements ActionListener{
	BufferedImage img2 = null;
	JTextField ID;
	JPasswordField PW;
	JButton button;
	
	public Joinview() { 
		setTitle("Joining");
		setSize(300, 337);
		setLayout(null);
		
		JLayeredPane LP = new JLayeredPane();
		LP.setBounds(0, 0, 300, 337);
		LP.setLayout(null);
		
		try {
			img2 = ImageIO.read(new File("JOINING.png"));
			} catch (IOException e) {
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}
	     
		NewPanel panel2 = new NewPanel();
	    panel2.setBounds(0, 0, 300, 337);
		
		ID = new JTextField(15); 
		ID.setBounds(100, 123, 104, 20);
        ID.setOpaque(false);
        ID.setForeground(Color.black);
        ID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LP.add(ID);
		
		PW = new JPasswordField(15);
		PW.setBounds(100, 185, 104, 20);
        PW.setOpaque(false);
        PW.setForeground(Color.black);
        PW.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        LP.add(PW);
		
        button = new JButton(new ImageIcon("Join.png"));
        button.setBounds(100, 230, 104, 48);
        
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
		
        LP.add(button);
        LP.add(panel2);
        add(LP);
		setLocation(550, 200);
    	setVisible(true);
	}
	class NewPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img2, 0, 0, null);
            }
        }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id = ID.getText();
        char[] pass = PW.getPassword();
        String password = new String(pass);
 
        if (id.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter a ID or Password");
        } else {
            boolean JoinOk = LoginService.Joining(id, password);
            if (JoinOk) {
                JOptionPane.showMessageDialog(null, "Success");
            } else {
                JOptionPane.showMessageDialog(null, "Fuck");
            }
 
        }
        password = null;
	}
}
