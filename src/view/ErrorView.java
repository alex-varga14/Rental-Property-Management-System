package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//view to error handlt login errors, ie same username or invalid
public class ErrorView extends JFrame
{
	 ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	 JButton enterButton = new JButton("Try again");
	 JButton signUpButton = new JButton("Sign Up");
	 
	 //set up JFrame
	 public ErrorView() {
		setTitle("Master Rental Property Management - Login Error");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		 
		JLabel error = new JLabel("ERROR: Email Address or Password Invalid!");
		error.setFont(new Font("Arial", Font.BOLD, 22));
		error.setBounds(10, 34, 500, 40);
    	getContentPane().add(error);
    	
    	enterButton.setBounds(60, 100, 90, 30);
    	getContentPane().add(enterButton);
    	enterButton.setActionCommand("try again");
    	
    	signUpButton.setBounds(300, 100, 90, 30);
    	getContentPane().add(signUpButton);
    	signUpButton.setActionCommand("sign up");
	 }
	 //action listeners
	 public void addTryAgainListener(ActionListener a)  {
	    	enterButton.addActionListener(a);
	 }
	 public void addSignUpListener(ActionListener a)  {
		    signUpButton.addActionListener(a);
	 }

}
