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
		
//		propertyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//			@Override
//			public void valueChanged(ListSelectionEvent e)
//			{
//				if(propertyTable.getSelectedRow() > -1)
//				{
//					System.out.println(propertyTable.getValueAt(propertyTable.getSelectedRow(),  0).toString());
//					//EmailController n = new EmailController("send");
//				}
//			}
//		});
		
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
	
	/*
	public void populateData(String type, int nBath, int nBed, boolean furn, String quad)
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
			if(!type.equals(""))
			{
				typeFilter = true;
			}
			if(!String.valueOf(nBath).equals(""))
			{
				bathFilter = true;
			}
			if(!String.valueOf(nBed).equals(""))
			{
				bedFilter = true;
			}
			if(!String.valueOf(furn).equals(""))
			{
				furnFilter = true;
			}
			if(!quad.equals(""))
			{
				quadFilter = true;
			}
			//determine which search algo to use
			//all filters set
			if( (typeFilter == true) && (bathFilter == true) && (bedFilter == true) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//everything except type true
			else if( (typeFilter == false) && (bathFilter == true) && (bedFilter == true) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//bed furn and quad true
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == true) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//furn and quad true
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == false) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//quad true
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == false) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//all false
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == false) && (furnFilter == false) && (quadFilter == false))
			{
				dataa = access.getAllProperties();
			}
			//type true, 
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == false) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//type true, bath true
			else if( (typeFilter == true) && (bathFilter == true) && (bedFilter == false) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//type true, bed true
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == true) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//type true, furn true
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == false) && (furnFilter == true) && (quadFilter == false))
			{
				
			}
			//type true, quad true
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == false) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//bath true, 
			else if( (typeFilter == false) && (bathFilter == true) && (bedFilter == false) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//bath and bed true, 
			else if( (typeFilter == false) && (bathFilter == true) && (bedFilter == true) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//bath and furn true, 
			else if( (typeFilter == false) && (bathFilter == true) && (bedFilter == false) && (furnFilter == true) && (quadFilter == false))
			{
				
			}
			//bath and quad true, 
			else if( (typeFilter == false) && (bathFilter == true) && (bedFilter == false) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//bed true, 
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == true) && (furnFilter == false) && (quadFilter == false))
			{
				
			}
			//bed and funished true, 
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == true) && (furnFilter == true) && (quadFilter == false))
			{
				
			}
			//bed and quadrant true, 
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == true) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//furn true, 
			else if( (typeFilter == false) && (bathFilter == false) && (bedFilter == false) && (furnFilter == true) && (quadFilter == false))
			{
				
			}
			//type, furn quad true, 
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == false) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//type, bath quad true, 
			else if( (typeFilter == true) && (bathFilter == true) && (bedFilter == false) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//everything except bath
			else if( (typeFilter == true) && (bathFilter == false) && (bedFilter == true) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//everything except bed
			else if( (typeFilter == true) && (bathFilter == true) && (bedFilter == false) && (furnFilter == true) && (quadFilter == true))
			{
				
			}
			//everything except furn
			else if( (typeFilter == true) && (bathFilter == true) && (bedFilter == true) && (furnFilter == false) && (quadFilter == true))
			{
				
			}
			//everything except quad
			else if( (typeFilter == true) && (bathFilter == true) && (bedFilter == true) && (furnFilter == true) && (quadFilter == false))
			{
				
			}
			
			propertyTable.setDefaultEditor(Object.class, null);
		}
	} */
	
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