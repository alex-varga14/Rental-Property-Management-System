
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame 
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	JButton enterButton = new JButton("Sign In");
	JTextField emailInput;
	JPasswordField password;
	
    public LoginView() {
    	setTitle("Master Rental Property Management - Login");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		JLabel emailLbl = new JLabel("Email address: ");
	    JLabel passwordLbl = new JLabel("Password: ");

	    emailInput = new JTextField();
	    emailInput.setBounds(168, 34, 250, 30);
    	getContentPane().add(emailInput);
    	emailInput.setColumns(10);
    	
    	password = new JPasswordField();
    	password.setBounds(168, 68, 250, 30);
    	getContentPane().add(password);
    	
    	emailLbl.setFont(new Font("Arial", Font.BOLD, 18));
    	emailLbl.setBounds(15, 37, 150, 20);
    	getContentPane().add(emailLbl);
    	
    	passwordLbl.setFont(new Font("Arial", Font.BOLD, 18));
    	passwordLbl.setBounds(15, 72, 100, 20);
    	getContentPane().add(passwordLbl);
    	
    	enterButton.setBounds(250, 100, 90, 30);
    	getContentPane().add(enterButton);
    	enterButton.setActionCommand("login");
    }
    
    public void addLoginListener(ActionListener al)  {
    	enterButton.addActionListener(al);
	}
    
    public String getEmail()  {
		return emailInput.getText();
	}
	
	public String getPassword()  {
		return String.copyValueOf(password.getPassword());
	}
}