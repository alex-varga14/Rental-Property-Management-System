package model;
import java.sql.*;
import java.util.*;

import controller.DuplicateEmailErrorController;
import controller.LoginController;

public class Database 
{
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	private Connection dbConnect;
	private ResultSet results;
	
	private static Database instance;
	
	private Database() {
	this.DBURL = "jdbc:mysql://localhost/rentalproperties";
    this.USERNAME = "alexcode";
    this.PASSWORD = "glorycode";
    initializeConnection();
    };
	
	public static Database getInstance()
	{
		if(instance == null) 
		{
			instance = new Database();
		}
		return instance;
	}

	
	public void initializeConnection(){
		try{
		   dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public Connection getConnection() 
	{
		return dbConnect;
	}
	
	 public String findUser(String type, String p){
	        StringBuilder tmp = new StringBuilder("");
	        try {
	            Statement myStmt = dbConnect.createStatement();
	            results = myStmt.executeQuery("SELECT logintype FROM USERS WHERE username = '" + type + "' AND password = '" + p +"'");
	            while(results.next()){
	                tmp.append(results.getString("logintype"));
	                //return "USER FOUND";
				}
				myStmt.close();
			} catch (SQLException i) {
				i.printStackTrace();
			}
            //return "USER NOT FOUND";
	        return tmp.toString();
	    }
	 
	 public void addUser(String username, String password, String logintype)
	 {
		 try {
	        	String query = "INSERT INTO users ( username, password, logintype) VALUES (?, ?, ?)";
	        	PreparedStatement myStmt = dbConnect.prepareStatement(query);
	        	myStmt.setString(1, username);
	            myStmt.setString(2, password);
	            myStmt.setString(3, logintype);
	            int rowCount = myStmt.executeUpdate();
	            System.out.println("Rows affected: " + rowCount);
	            myStmt.close();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            if(ex.getErrorCode() == 1062) {
	            	DuplicateEmailErrorController newError = new DuplicateEmailErrorController();
	            	return;
	            }
	        }
		 LoginController login = new LoginController();
	 }
	 
	 public void updatePropertyState(String propID, String s) {
		 try 
		 {
            Statement myStmt = dbConnect.createStatement();
            myStmt.executeUpdate("UPDATE listedproperties SET stateoflisting = '"+ s + "' WHERE ID='"+ propID +"'");
            myStmt.close();
		} catch (SQLException e) {
			System.err.println("Error changing state in DB");
			e.printStackTrace();
		}
	 }
	 
		
	public void listNewProperty(Property n)
	{
        try {
        	String query = "INSERT INTO listedproperties ( type, numberOfBaths, numberOfBedrooms, furnished, address, quadrant, stateOfListing, listingStart, listingEnd, lanlordEmail) "
        			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        	PreparedStatement myStmt = dbConnect.prepareStatement(query);
        	myStmt.setString(1, n.getTypeOfProperty());
            myStmt.setInt(2, n.getNumberOfBathrooms());
            myStmt.setInt(3, n.getNumberOfBedrooms());
            myStmt.setBoolean(4, n.isFurnished());
            myStmt.setString(5, n.getAddress());
            myStmt.setString(6, n.getQuadrant());
            myStmt.setString(7, n.getState().toString());
            myStmt.setString(8, n.getInformation().getFeePeriodStart());
            myStmt.setString(9, n.getInformation().getFeePeriodEnd());
            myStmt.setString(10, n.getLanlordEmail());
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	public String[][] getAllProperties()
	{
		String[][] propertyData = new String[numberOfProperties()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				propertyData[i] = new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") };
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return propertyData;
	}
	
	public String[][] getAllPropertiesManager()
	{
		String[][] propertyData = new String[numberOfProperties()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				propertyData[i] = new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING"), rs.getString("LISTINGSTART"), 
								rs.getString("LISTINGEND"), rs.getString("lanlordEmail") };
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return propertyData;
	}
	public String[][] getAllPropertiesManagerSummary()
	{
		String[][] propertyData = new String[numberOfProperties()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				propertyData[i] = new String[]{ rs.getString("lanlordEmail"), rs.getString("ID"), rs.getString("ADDRESS")};
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return propertyData;
	}
	
	public String[][] getAllLanlordProperties(String e)
	{
		String[][] propertyData = new String[lanlordnNumberOfProperties()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties where lanlordemail='" + Lanlord.getInstance().getEmailAddress() +"'";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				propertyData[i] = new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") };
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return propertyData;
	}
	/*
	 * 
	 * //int randomPropertyPicture = (int)(1 + (Math.random() * 4));
				//ImageIcon randomImage = new ImageIcon("C:\\Users\\Alex School\\Desktop\\git\\Rental Property Management System\\Rental Property Management System\\src\\assets/"+ randomPropertyPicture + "jpg");
//				propertyData[i] = new Object[]{randomImage,  rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
//						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
//								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") };
	 */
	
	public String[][] getAllRenters()
	{
		String[][] renterData = new String[numberOfRenters()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM users where loginType='Registered Renter'";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				renterData[i] = new String[]{ rs.getString("USERNAME"), rs.getString("PASSWORD")};
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return renterData;
	}
	public String[][] getAllLanlords()
	{
		String[][] renterData = new String[numberOfLanlords()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM users where loginType='Lanlord'";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				renterData[i] = new String[]{ rs.getString("USERNAME"), rs.getString("PASSWORD")};
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return renterData;
	}
	
	public int numberOfProperties()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM listedproperties";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	
	public int numberOfRentedProperties()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM listedproperties where stateOflisting ='RENTED'";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	
	public int numberOfActiveProperties()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM listedproperties where stateOflisting ='ACTIVE'";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	public int numberOfRenters()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM users where logintype='Registered Renter'";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	
	public int numberOfLanlords()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM users where logintype='Lanlord'";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	
	public int lanlordnNumberOfProperties()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM listedproperties where lanlordemail='"+ Lanlord.getInstance().getEmailAddress() +"'";
			rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return count;
	}
	
	public void searchListings(String propertyType, int numberOfBaths, int numberOfBedrooms, boolean furnished, String quadrant)
	{
		StringBuffer tmp = new StringBuffer();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '" + propertyType + "' or numberOfBaths= '" + numberOfBaths +
					"' or numberOfBedrooms= '" + numberOfBedrooms + "' or furnished= '" + furnished + "' or quadrant= '" + quadrant + "'";
			rs = stmt.executeQuery(query);
			System.out.println("Properties that match your search are: ");
			while(rs.next()) {
				tmp.append("Type: " + rs.getString("TYPE") + " Address: " + rs.getString("ADDRESS") + " Number of baths: " + rs.getInt("numberOfBaths") + " Number of bedrooms: " + rs.getInt("numberOfBedrooms") +
						" Furnished: " + rs.getString("FURNISHED") + " Quadrant: " + rs.getString("QUADRANT") + "\n");
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		if(tmp.length() == 0) {
			tmp.append("No matches");
		}
		System.out.println(tmp.toString());
	}

}
/*

    Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("INSERT INTO listedproperties ( type, nBath, nBed, furnished, address, quadrant, state, startPeriod, endPeriod)"
            		+ " VALUES ( '" + n.getTypeOfProperty() + "', '" + n.getNumberOfBathrooms() + "', '" + n.getNumberOfBedrooms() + "', '" + n.getAddress()
            		+ "', '" + n.isFurnished() + "', '" + n.getQuadrant() + "', '" + n.getState().toString() + "', '" + n.getInformation().getFeePeriodStart() +
            		"', '" + n.getInformation().getFeePeriodEnd() + "' );");
 */


