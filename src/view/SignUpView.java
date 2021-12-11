package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	private JTextField emailInput;
	String[] optionsToChoose = {"Manager", "Lanlord", "Registered Renter"};
    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
    JLabel emailLbl = new JLabel("Email address");
    JLabel passwordLbl = new JLabel("Password");
    JLabel userTypeLbl = new JLabel("User Type");
	private JPasswordField password;
	private JButton enterButton = new JButton("Sign Up");
	
	public SignUpView() {
		setTitle("Master Rental Property Management - Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		JLabel text = new JLabel("Please Enter the Following Information: ");
		text.setFont(new Font("Arial", Font.BOLD, 20));
    	text.setBounds(10, 5, 500, 22);
    	getContentPane().add(text);
    	
    	emailLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    	emailLbl.setBounds(10, 35, 84, 20);
    	getContentPane().add(emailLbl);
    	
    	passwordLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    	passwordLbl.setBounds(10, 70, 69, 20);
    	getContentPane().add(passwordLbl);
    	
    	userTypeLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    	userTypeLbl.setBounds(10, 105, 69, 20);
    	getContentPane().add(userTypeLbl);
        
    	emailInput = new JTextField();
    	emailInput.setBounds(140, 35, 200, 30);
    	getContentPane().add(emailInput);
    	emailInput.setColumns(10);
    	
    	password = new JPasswordField();
    	password.setBounds(140, 70, 200, 30);
    	getContentPane().add(password);
    	
        jComboBox.setBounds(140, 105, 200, 30);
        getContentPane().add(jComboBox);
    	
    	enterButton.setBounds(375, 60, 90, 40);
    	getContentPane().add(enterButton);
    	enterButton.setActionCommand("sign up");
	}
	
    public String getEmail()  {
		return emailInput.getText();
	}
	
	public String getPassword()  {
		return String.copyValueOf(password.getPassword());
	}
	
	public String getUser()
	{
		return jComboBox.getSelectedItem().toString();
	}
	
	public void addSignUpListener(ActionListener al)  {
    	enterButton.addActionListener(al);
	}
}
