package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.PropertyFees;
import model.ListingState;
import model.Property.*;

public class ChangeStateView extends JFrame
{
	
	 ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	 JButton changeStateButton = new JButton("Change Listing State");
	 JButton cancelButton = new JButton("Cancel Change");
	 
	 
	 String[] states = {"", ListingState.ACTIVE.toString(), ListingState.RENTED.toString(), ListingState.SUSPENDED.toString(), ListingState.CANCELLED.toString()};
	 JComboBox<String> stateComboBox = new JComboBox<>(states);

	 public ChangeStateView(String state) {
		setTitle("Master Rental Property Management - Change Property Listing State");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(500, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		PropertyFees currentFees = PropertyFees.getInstance();
		
		JLabel currentFee = new JLabel("The Current Property State is: " + state);
		currentFee.setFont(new Font("Arial", Font.BOLD, 20));
		currentFee.setBounds(10, 10, 500, 40);
		getContentPane().add(currentFee);
		
		JLabel newStateBox = new JLabel("New State:");
		newStateBox.setFont(new Font("Arial", Font.BOLD, 20));
		newStateBox.setBounds(10, 60, 130, 20);
	   	getContentPane().add(newStateBox);
	   	
	   	stateComboBox.setBounds(125, 55, 130, 30);
        getContentPane().add(stateComboBox);
		 
	
        changeStateButton.setBounds(60, 100, 160, 30);
	   	getContentPane().add(changeStateButton);
	   	changeStateButton.setActionCommand("change state");
	   	
	   	cancelButton.setBounds(300, 100, 130, 30);
	   	getContentPane().add(cancelButton);
	   	cancelButton.setActionCommand("cancel");
	 }
	 
	 public void addChangeStateListener(ActionListener a)  {
		 changeStateButton.addActionListener(a);
	 }
	 public void addCancelListener(ActionListener a)  {
		 cancelButton.addActionListener(a);
	 }
	 
	 public String getStateBox()
	{
		return stateComboBox.getSelectedItem().toString();
	}
		
	 
}