package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Database;

public class SummaryView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	Database access = Database.getInstance();
	int totalHouses;
	int rentedHouses;
	int activeListings;
	JButton backHome = new JButton("Return Home");
	
	String[][] dataa;
	JTable propertyTable;
	
	DefaultTableModel model;
	
	String[] columnNames = { "LanlordEmail", "ID","Address"};
	
	
	public SummaryView()
	{
		setTitle("Master Rental Property Management - Summary Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		access.getConnection();
		
		totalHouses = access.numberOfProperties();
	
		JLabel info = new JLabel("Total Number of Houses Listed in the Period - " + totalHouses + " Listed Houses.");
		info.setFont(new Font("Arial", Font.BOLD, 18));
		info.setBounds(10, 10, 550, 40);
		getContentPane().add(info);
		
		rentedHouses = access.numberOfRentedProperties();
		
		JLabel info2 = new JLabel("Total Number of Houses Rented in the Period - " + rentedHouses + " Rented Houses.");
		info2.setFont(new Font("Arial", Font.BOLD, 18));
		info2.setBounds(10, 50, 550, 40);
		getContentPane().add(info2);
		
		activeListings = access.numberOfActiveProperties();
		
		JLabel info3 = new JLabel("Total Number of Active Listings - " + activeListings + " Active Listings.");
		info3.setFont(new Font("Arial", Font.BOLD, 18));
		info3.setBounds(10, 90, 500, 40);
		getContentPane().add(info3);
		
		JLabel listOfHouses = new JLabel("List Of Houses Rented In Period:");
		listOfHouses.setFont(new Font("Arial", Font.BOLD, 18));
		listOfHouses.setBounds(10, 130, 400, 40);
		getContentPane().add(listOfHouses);
		
		populatePropertyData();
		propertyTable = new JTable(dataa, columnNames);
		
		propertyTable.setDefaultEditor(Object.class, null);
		JScrollPane sp = new JScrollPane(propertyTable);
		sp.setBounds(0, 160, width/2, 100);
		getContentPane().add(sp);
		
		backHome.setBounds(250, 300, 120, 30);
	   	getContentPane().add(backHome);
	   	backHome.setActionCommand("home");
	   	
	}
	
	public void addHomePageListener(ActionListener a)  {
		backHome.addActionListener(a);
	 }
	
	public void populatePropertyData()
	{
		dataa = access.getAllPropertiesManagerSummary();
	}
}