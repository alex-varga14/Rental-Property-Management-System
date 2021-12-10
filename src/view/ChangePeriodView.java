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

public class ChangePeriodView extends JFrame
{
	
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	 JButton changeFeeButton = new JButton("Change Period");
	 JButton cancelButton = new JButton("Cancel Change");
	 JTextField newFee = new JTextField();
	 
	 public ChangePeriodView() {
		setTitle("Master Rental Property Management - Modify Property Listing Period");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		PropertyFees currentFees = PropertyFees.getInstance();
		
		JLabel currentFee = new JLabel("The Current Property Period is: " + currentFees.getPeriod() + " days");
		currentFee.setFont(new Font("Arial", Font.BOLD, 20));
		currentFee.setBounds(10, 10, 500, 40);
		getContentPane().add(currentFee);
		
		JLabel newPeriodBox = new JLabel("New Period:");
		newPeriodBox.setFont(new Font("Arial", Font.BOLD, 20));
		newPeriodBox.setBounds(10, 60, 130, 20);
	   	getContentPane().add(newPeriodBox);
	   
		newFee.setBounds(150, 60, 80, 30);
	   	getContentPane().add(newFee);
	   	newFee.setColumns(10);
		 
 	
	   	changeFeeButton.setBounds(70, 100, 120, 30);
	   	getContentPane().add(changeFeeButton);
	   	changeFeeButton.setActionCommand("change period");
	   	
	   	cancelButton.setBounds(300, 100, 130, 30);
	   	getContentPane().add(cancelButton);
	   	cancelButton.setActionCommand("cancel");
	 }
	 
	 public void addChangePeriodListener(ActionListener a)  {
		 changeFeeButton.addActionListener(a);
	 }
	 public void addCancelListener(ActionListener a)  {
		 cancelButton.addActionListener(a);
	 }
	 
	 public String getNewPeriodField()
	 {
			return newFee.getText();
	 }
}