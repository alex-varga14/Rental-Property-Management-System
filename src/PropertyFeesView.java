import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PropertyFeesView  extends JFrame
{
	 ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	 JButton enterButton = new JButton("List Property");
	 JButton signUpButton = new JButton("Cancel Registration");
	 
	 public PropertyFeesView() {
		setTitle("Master Rental Property Management - Property Fee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		 
		JLabel info = new JLabel("It costs $100 to List a Property for 60 days.");
		info.setFont(new Font("Arial", Font.BOLD, 22));
		info.setBounds(20, 10, 500, 40);
		getContentPane().add(info);
		
		JLabel info2 = new JLabel("Do you want to List your Property?");
		info2.setFont(new Font("Arial", Font.BOLD, 16));
		info2.setBounds(100, 45, 400, 40);
		getContentPane().add(info2);
   	
	   	enterButton.setBounds(70, 90, 120, 40);
	   	getContentPane().add(enterButton);
	   	enterButton.setActionCommand("list property");
	   	
	   	signUpButton.setBounds(300, 90, 120, 40);
	   	getContentPane().add(signUpButton);
	   	signUpButton.setActionCommand("cancel");
	 }
	 
	 public void addListPropertyListener(ActionListener a)  {
	    	enterButton.addActionListener(a);
	 }
	 public void addCancelListener(ActionListener a)  {
		    signUpButton.addActionListener(a);
	 }

}