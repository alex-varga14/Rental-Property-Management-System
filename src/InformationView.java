import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InformationView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	
	String[][] data;
	
	String[][] dataa;
	JTable propertyTable;
	
	DefaultTableModel model;
	
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