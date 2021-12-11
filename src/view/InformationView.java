package view;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Database;

//Manager Information view 
public class InformationView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	String[][] dataa;
	JTable propertyTable;
	//CTOR to set up Jframe of desired information
	public InformationView(String infoType)
	{
		setTitle("Master Rental Property Management - " + infoType + " Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		if(infoType.equals("Registered Renter")) 
		{
			String[] columnNames = { "Email", "Password"};
			populateRenterData();
			propertyTable = new JTable(dataa, columnNames);
		}
		else if(infoType.equals("Lanlord")) 
		{
			String[] columnNames = { "Email", "Password"};
			populateLanlordData();
			propertyTable = new JTable(dataa, columnNames);
		}
		else if(infoType.equals("Property")) 
		{
			String[] columnNames = { "ID", "Type","numOfBathroom",  "numOfBedroom", "isFurnished", "Address", "cityQuadrant", "ListingState", 
					"ListingStart", "ListingEnd", "LanlordEmail"};
			populatePropertyData();
			propertyTable = new JTable(dataa, columnNames);
		}
		
		propertyTable.setDefaultEditor(Object.class, null);
		JScrollPane sp = new JScrollPane(propertyTable);
		sp.setBounds(10, 50, 200, 200);
		getContentPane().add(sp);

	}
	//functions to populate JTables for renters, lanlords or properties
	public void populateRenterData()
	{
		Database access = Database.getInstance();
		access.getConnection();
		dataa = access.getAllRenters();
	}
	
	public void populateLanlordData()
	{
		Database access = Database.getInstance();
		access.getConnection();
		dataa = access.getAllLanlords();
	}
	
	public void populatePropertyData()
	{
		Database access = Database.getInstance();
		access.getConnection();
		dataa = access.getAllPropertiesManager();
	}
}