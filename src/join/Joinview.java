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
	
	JButton icon1;
	JButton icon2;
	JButton icon3;
	JButton icon4;
	JButton icon5;
	JButton icon6;
	int ck;
	
	public Joinview() { 
		setTitle("Joining");
		setSize(515, 500);
		setLayout(null);
		
		JLayeredPane LP = new JLayeredPane();
		LP.setBounds(0, 0, 515, 500);
		LP.setLayout(null);
		
		try {
			img2 = ImageIO.read(new File("img/Joining_change.png"));
			} catch (IOException e) {
				System.out.println("Image failed.");
				System.exit(0);
			}
	     
		NewPanel panel2 = new NewPanel();
	    panel2.setBounds(0, 0, 515, 500);
		
		ID = new JTextField(15); 
		ID.setBounds(180, 260, 104, 20);
        ID.setOpaque(false);
        ID.setForeground(Color.black);
        ID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		LP.add(ID);
		
		PW = new JPasswordField(15);
		PW.setBounds(180, 330, 104, 20);
        PW.setOpaque(false);
        PW.setForeground(Color.black);
        PW.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        LP.add(PW);
		
        button = new JButton(new ImageIcon("img/Join.png"));
        button.setBounds(200, 400, 104, 48);
        
		
        icon1 = new JButton(new ImageIcon("img/icn1.png"));
        icon1.setBounds(20, 130, 60, 60);
        icon1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ck=0;
			}
		});
        icon2 = new JButton(new ImageIcon("img/icn2.png"));
        icon2.setBounds(100, 130, 60, 60);
        icon2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				ck=1;
 			}
 		});
        icon3 = new JButton(new ImageIcon("img/icn3.png"));
        icon3.setBounds(180, 130, 60, 60);
        icon3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				ck=2;
 			}
 		});
        icon4 = new JButton(new ImageIcon("img/icn4.png"));
        icon4.setBounds(260, 130, 60, 60);
        icon4.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				ck=3;
 			}
 		});
        icon5 = new JButton(new ImageIcon("img/icn5.png"));
        icon5.setBounds(340, 130, 60, 60);
        icon5.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				ck=4;
 			}
 		});
        icon6 = new JButton(new ImageIcon("img/icn6.png"));
        icon6.setBounds(420, 130, 60, 60);
        icon6.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				ck=5;
 			}
 		});
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
        
        LP.add(button);
        LP.add(icon1);
        LP.add(icon2);
        LP.add(icon3);
        LP.add(icon4);
        LP.add(icon5);
        LP.add(icon6);
        
        LP.add(panel2);
        add(LP);
		setLocation(450, 120);
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
            boolean JoinOk = LoginService.Joining(id, password,ck);
            if (JoinOk) {
                JOptionPane.showMessageDialog(null, "Joining successes, please Login");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "ID is existed already");
            }
 
        }
        password = null;
	}
}
