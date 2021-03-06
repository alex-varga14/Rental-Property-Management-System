package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.PropertyFees;

//Change Fee View
public class ChangeFeeView extends JFrame
{
	 //Company Logo
	 ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	 //Buttons for commands
	 JButton enterButton = new JButton("Change Fee");
	 JButton signUpButton = new JButton("Cancel Change");
	 //textfield to store new fee
	 JTextField newFee = new JTextField();
	 
	 
	 //CTOR to set up JFRAME
	 public ChangeFeeView() {
		setTitle("Master Rental Property Management - Modify Property Fee's");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		PropertyFees currentFees = PropertyFees.getInstance();
		
		JLabel currentFee = new JLabel("The Current Property Fee is: $" + currentFees.getFee());
		currentFee.setFont(new Font("Arial", Font.BOLD, 20));
		currentFee.setBounds(10, 10, 500, 40);
		getContentPane().add(currentFee);
		
		JLabel newFeeBox = new JLabel("New fee:");
		newFeeBox.setFont(new Font("Arial", Font.BOLD, 20));
		newFeeBox.setBounds(10, 60, 90, 20);
    	getContentPane().add(newFeeBox);
    
		newFee.setBounds(100, 60, 80, 30);
    	getContentPane().add(newFee);
    	newFee.setColumns(10);
		 
	   	enterButton.setBounds(70, 100, 120, 30);
	   	getContentPane().add(enterButton);
	   	enterButton.setActionCommand("change fee");
	   	
	   	signUpButton.setBounds(300, 100, 130, 30);
	   	getContentPane().add(signUpButton);
	   	signUpButton.setActionCommand("cancel");
	 }
	 
	 //ActionListeners for both buttons, linked to ChangeFee Controller
	 public void addChangeFeeListener(ActionListener a)  {
	    	enterButton.addActionListener(a);
	 }
	 public void addCancelListener(ActionListener a)  {
		    signUpButton.addActionListener(a);
	 }
	 //Getter to get contents of TextField
	 public String getNewFeeField()
	 {
			return newFee.getText();
	 }
}
