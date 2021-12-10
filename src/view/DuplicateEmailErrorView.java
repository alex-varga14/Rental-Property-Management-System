package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DuplicateEmailErrorView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	JButton enterButton = new JButton("Try again");
	JButton signInButton = new JButton("Sign In");
	
	public DuplicateEmailErrorView() {
		setTitle("Master Rental Property Management - Sign Up Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		 
		JLabel error = new JLabel("ERROR: Email Address In Use");
		error.setFont(new Font("Arial", Font.BOLD, 22));
		error.setBounds(10, 34, 500, 40);
		getContentPane().add(error);
   	
	   	enterButton.setBounds(60, 100, 90, 30);
	   	getContentPane().add(enterButton);
	   	enterButton.setActionCommand("try again");
	   	
	   	signInButton.setBounds(300, 100, 90, 30);
	   	getContentPane().add(signInButton);
	   	signInButton.setActionCommand("sign in");
	 }
	 
	 public void addTryAgainListener(ActionListener a)  {
	    	enterButton.addActionListener(a);
	 }
	 public void addSignInListener(ActionListener a)  {
		    signInButton.addActionListener(a);
	 }
	
}
