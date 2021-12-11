package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Property;
import model.Database;
import model.Lanlord;
import model.Manager;


public class PropertyView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	private ArrayList<Property> propertyList = new ArrayList<Property>();
	
	//String[] columnNames = {"Picture", "ID", "Type","numOfBathroom",  "numOfBedroom", "isFurnished", "Address", "cityQuadrant", "ListingState"};
	String[] columnNames = { "ID", "Type","numOfBathroom",  "numOfBedroom", "isFurnished", "Address", "cityQuadrant", "ListingState"};
	String[][] data;
	
	String[][] dataa;
	JTable propertyTable;
	String selected;
	String ID;
	
	JButton emailButton = new JButton("Email Lanlord About Selected Property?");
	JButton changeStateButton = new JButton("Change state of Property Listing?");
	
	protected boolean typeFilter = false;
	protected boolean bathFilter = false;
	protected boolean bedFilter = false;
	protected boolean furnFilter = false;
	protected boolean quadFilter = false;
	
//	public PropertyView(String type, int nBath, int nBed, boolean furn, String quad) {
//		setTitle("Master Rental Property Management - Listed Properties");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setBackground(Color.white);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int height = screenSize.height;
//		int width = screenSize.width;
//		setSize(width/2, height/2);
//		setLocationRelativeTo(null);
//		setIconImage(img.getImage());
//		populateData(type, nBath, nBed, furn, quad);
//		propertyTable = new JTable(dataa, columnNames);
//		JScrollPane sp = new JScrollPane(propertyTable);
//		sp.setBounds(10, 50, 200, 200);
//		getContentPane().add(sp);
//		
//	}
	public PropertyView() {
		setTitle("Master Rental Property Management - Listed Properties");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		populateData();
		propertyTable = new JTable(dataa, columnNames);
		propertyTable.setDefaultEditor(Object.class, null);
		
		emailButton.setBounds(180, 360, 300, 30);
		emailButton.setActionCommand("email");
		
		changeStateButton.setBounds(180, 360, 300, 30);
		changeStateButton.setActionCommand("state");
		
		
		
		JScrollPane sp = new JScrollPane(propertyTable);
		sp.setBounds(00, 00, width/2 - 10, 200);
		getContentPane().add(sp);
		
		propertyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		propertyTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(Lanlord.getInstance().getEmailAddress() != null) {
					getContentPane().add(changeStateButton);
					System.out.println("Lanlord");
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					selected = propertyTable.getValueAt(propertyTable.getSelectedRow(),  7).toString();
				}
				else if(Manager.getInstance().getEmailAddress() != null) {
					getContentPane().add(changeStateButton);
					System.out.println("Manager");
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					selected = propertyTable.getValueAt(propertyTable.getSelectedRow(),  7).toString();
				}
				else {
					getContentPane().add(emailButton);
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					System.out.println("Renter");
				}
		    	getContentPane().repaint();
			}
		});
	}
	
	public PropertyView(boolean[] flags,  String type, int beds, int baths, int furn, String quad) {
		setTitle("Master Rental Property Management - Listed Properties");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		populateData(flags, type,beds, baths, furn, quad);
		propertyTable = new JTable(dataa, columnNames);
		propertyTable.setDefaultEditor(Object.class, null);
		
		emailButton.setBounds(180, 360, 300, 30);
		emailButton.setActionCommand("email");
		
		changeStateButton.setBounds(180, 360, 300, 30);
		changeStateButton.setActionCommand("state");
		
		
		
		JScrollPane sp = new JScrollPane(propertyTable);
		sp.setBounds(00, 00, width/2 - 10, 200);
		getContentPane().add(sp);
		
		propertyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		propertyTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(Lanlord.getInstance().getEmailAddress() != null) {
					getContentPane().add(changeStateButton);
					System.out.println("Lanlord");
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					selected = propertyTable.getValueAt(propertyTable.getSelectedRow(),  7).toString();
				}
				else if(Manager.getInstance().getEmailAddress() != null) {
					getContentPane().add(changeStateButton);
					System.out.println("Manager");
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					selected = propertyTable.getValueAt(propertyTable.getSelectedRow(),  7).toString();
				}
				else {
					getContentPane().add(emailButton);
					ID = propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString();
					System.out.println("Renter");
				}
		    	getContentPane().repaint();
			}
		});
	}
	
	public void addEmailListener(ActionListener a)  {
		emailButton.addActionListener(a);
	}
	
	public void addStateListener(ActionListener a)  {
		changeStateButton.addActionListener(a);
	}
	
	public String getSelected()
	{
		return selected;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void populateData(boolean[] flags,  String type, int beds, int baths, int furn, String quad)
	{
		Database access = Database.getInstance();
		access.getConnection();
		if((flags[0] == false) && (flags[1] == false) &&(flags[2] == false) &&(flags[3] == false) &&(flags[4] == false))
		{
			populateData();
		}
		else if (flags[0] == true)
		{
			System.out.println("In spin");
			if(flags[1] == true)
			{
				if(flags[2] == true)
				{
					if(flags[3] == true)
					{
						if(flags[4] == true)
						{
							System.out.println("In all true");
							dataa = access.getAllFilteredProperties(type, beds, baths, furn, quad);
						}
						else
						{
							dataa = access.getAllFilteredProperties1(type, beds, baths, furn);
						}
					} 
					else if(flags[4] == true)
					{
						dataa = access.getAllFilteredProperties2(type, beds, baths, quad);
					}
					else
					{
						dataa = access.getAllFilteredProperties3(type, beds, baths);
					}	
				}
				else if (flags[3] == true)
				{
					if(flags[4] == true)
					{
						dataa = access.getAllFilteredProperties4(type, beds, furn, quad);
					}
					else
					{
						dataa = access.getAllFilteredProperties5(type, beds, furn);
					}
				} 
				else if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties6(type, beds, quad);
				}
				else
				{
					dataa = access.getAllFilteredProperties7(type, beds);
				}	
			} 
			else if (flags[2] == true)
			{
				if(flags[3] == true)
				{
					dataa = access.getAllFilteredProperties8(type, baths, furn);
				}
				else if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties9(type, baths, quad);
				}
				else
				{
					dataa = access.getAllFilteredProperties10(type, baths);
				}
			}
			else if(flags[3] == true)
			{
				if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties11(type, furn, quad);
				}
				else
				{
					dataa = access.getAllFilteredProperties12(type, furn);
				}
			}
			else if(flags[4] == true)
			{
				dataa = access.getAllFilteredProperties13(type, quad);
			}
			else ;
		}
		else if(flags[1] == true)
		{
			if(flags[2] == true)
			{
				if(flags[3] == true) 
				{
					if(flags[4] == true)
					{
						dataa = access.getAllFilteredProperties14(beds, baths, furn, quad);
					}
					else
					{
						dataa = access.getAllFilteredProperties15(beds, baths, furn);
					}
				}
				else if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties16(beds, baths, quad);
				}
				else
				{
					System.out.println("Guuy");
					dataa = access.getAllFilteredProperties17(beds, baths);
				}
			}
			else if(flags[3] == true)
			{
				if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties18(beds, furn, quad);
				}
				else
				{
					dataa = access.getAllFilteredProperties19(beds, furn);
				}
			}
			else if (flags[4] == true)
			{
				dataa = access.getAllFilteredProperties20(beds, quad);
			}
			else
			{
				dataa = access.getAllFilteredProperties21(beds);
			}
		}
		else if(flags[2] == true)
		{
			if(flags[3] == true)
			{
				if(flags[4] == true)
				{
					dataa = access.getAllFilteredProperties22(baths, furn, quad);
				}
				else
				{
					dataa = access.getAllFilteredProperties23(baths, furn);
				}
			}
			else if(flags[4] == true)
			{
				dataa = access.getAllFilteredProperties24(baths, quad);
			}
			else 
			{
				dataa = access.getAllFilteredProperties25(baths);
			}
		}
		else if(flags[3] == true)
		{
			if(flags[4]== true)
			{
				dataa = access.getAllFilteredProperties26(furn, quad);
			}
			else
			{
				dataa = access.getAllFilteredProperties27(furn);
			}
		}
		else if(flags[4] == true)
		{
			dataa = access.getAllFilteredProperties28(quad);
		}
		
		//propertyTable.setDefaultEditor(Object.class, null);
	} 
	
	public void populateData()
	{
		Database access = Database.getInstance();
		access.getConnection();
		if(Lanlord.getInstance().getEmailAddress() != null)
		{
			dataa = access.getAllLanlordProperties(Lanlord.getInstance().getEmailAddress());
			//propertyTable.getColumn(0).setEditable(false);
		}
		else
		{
			dataa = access.getAllProperties();
		}
	}
}