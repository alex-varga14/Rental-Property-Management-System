package model;
import java.sql.*;
import java.util.*;

import controller.DuplicateEmailErrorController;
import controller.LoginController;

public class Database 
{
	//Databse fields for connectivity, SQL standard
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	private Connection dbConnect;
	private ResultSet results;
	//implements singleton
	private static Database instance;
	
	private Database() {
	this.DBURL = "jdbc:mysql://localhost/rentalproperties";
    this.USERNAME = "alexcode";
    this.PASSWORD = "glorycode";
    //initializeConnection();
    };
	
	public static Database getInstance()
	{
		if(instance == null) 
		{
			instance = new Database();
		}
		return instance;
	}

	//intializes database connection
//	public void initializeConnection(){
//		try{
//		   dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
//			System.out.println("Connected");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	
//	}
	public void initializeConnection(String u, String p){
		try{
		   dbConnect = DriverManager.getConnection(this.DBURL, u, p);
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	//gets connection to database
	public Connection getConnection() 
	{
		return dbConnect;
	}
	//function to find a user in the database, see if can sign in
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
	 //function to query database for lanlord email from property ID
	 public String findLanlordEmail(String ID){
        StringBuilder tmp = new StringBuilder("");
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT lanlordemail FROM LISTEDPROPERTIES WHERE ID = '" + ID + "'");
            while(results.next()){
                tmp.append(results.getString("lanlordemail"));
			}
			myStmt.close();
		} catch (SQLException i) {
			i.printStackTrace();
		}
        return tmp.toString();
    }
	 //function to add user to database, used in sign up
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
	 //function to query database and update property state, for lanlord and manageer functionality
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
	 //function to list new property into listed property list
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
	//function to query database for all properties
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
	//function to getr all properties for manager view, diffrent from above becuase lanlord email is viewable
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
	//function to query database for manager summary report
	public String[][] getAllPropertiesManagerSummary()
	{
		String[][] propertyData = new String[numberOfRentedProperties()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties where stateoflisting='RENTED'";
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
	//function to query database for all properties associated with desired lanlord
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
	//int randomPropertyPicture = (int)(1 + (Math.random() * 4));
				//ImageIcon randomImage = new ImageIcon("C:\\Users\\Alex School\\Desktop\\git\\Rental Property Management System\\Rental Property Management System\\src\\assets/"+ randomPropertyPicture + "jpg");
//				propertyData[i] = new Object[]{randomImage,  rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
//						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
//								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") };
	 */
	//function to query database for all renters
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
	//function to query database for all lanlords
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
	//function to query database for the number of total properties
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
	//function to query database for the number of total RENTED properties
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
	//function to query database for the number of total ACTIVE property listings
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
	//function to query database for the number of total renters
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
	//function to query database for the number of total lanlords
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
	//function to query database for the number of totallanlord properties 
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
	//function to search listings, was used for a JTextfield originally but disbanded, could be implemented again
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
	//function to send email into database 
	public void sendEmail(String sender, String receiver, String body)
	 {
		 try {
	        	String query = "INSERT INTO emails ( sender, receiver, message) VALUES (?, ?, ?)";
	        	PreparedStatement myStmt = dbConnect.prepareStatement(query);
	        	myStmt.setString(1, sender);
	            myStmt.setString(2, receiver);
	            myStmt.setString(3, body);
	            int rowCount = myStmt.executeUpdate();
	            System.out.println("Rows affected: " + rowCount);
	            myStmt.close();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	 }
	//function to get all emails from inbox
	public String[][] getAllMessages()
	{
		String[][] inbox = new String[numberOfMessages()][];
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM emails where receiver ='" + Lanlord.getInstance().getEmailAddress() + "'";
			rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				inbox[i] = new String[]{ rs.getString("SENDER"), rs.getString("MESSAGE")};
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		return inbox;
	}
	//function to query database for the number of messages
	public int numberOfMessages()
	{
		int count = 0;
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT COUNT(*) FROM emails where receiver='" + Lanlord.getInstance().getEmailAddress() + "'";
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
	
	//Below on is a really shitty filter search implementation
	//could have probably used an interface to determine which filters active
	//instead made like 27 of the same functions
	//fix later
	
	public String[][] getAllFilteredProperties(String type, int beds, int baths, int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties1(String type, int beds, int baths, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "' AND furnished='"+ furn + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties2(String type, int beds, int baths, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + " AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties3(String type, int beds, int baths)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties4(String type, int beds,  int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties5(String type, int beds, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "' AND furnished='"+ furn + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties6(String type, int beds, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties7(String type, int beds)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND numberofBedrooms='" + beds + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties8(String type, int baths, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type  + "'"
					+ "AND numberOfBaths='"+ baths + "' AND furnished='" + furn + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties9(String type, int baths,  String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type  + "'"
					+ "AND numberOfBaths='"+ baths + "' AND quadrant='"+  quad +"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties10(String type, int baths)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "'"
					+ "AND numberOfBaths='"+ baths + "'" ;
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties11(String type, int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type + "' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties12(String type, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type +  "' AND furnished='"+ furn + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties13(String type, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE type= '"+ type +  "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties14(int beds, int baths, int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties15(int beds, int baths, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "' AND furnished='"+ furn + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties16(int beds, int baths,  String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties17( int beds, int baths)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "'"
					+ "AND numberOfBaths='"+ baths + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties18(int beds, int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds +"' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties19(int beds, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "' AND furnished='"+ furn +"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties20(int beds, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties21(int beds)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberofBedrooms='" + beds + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties22(int baths, int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberOfBaths='"+ baths + "' AND furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties23(int baths, int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberOfBaths='"+ baths + "' AND furnished='"+ furn +"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties24(int baths, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberOfBaths='"+ baths + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties25(int baths)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE numberOfBaths='"+ baths + "'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties26(int furn, String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE furnished='"+ furn + "' AND quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties27(int furn)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE furnished='"+ furn +"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
	
	public String[][] getAllFilteredProperties28(String quad)
	{
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		ResultSet rs;
		try {
			Statement stmt = dbConnect.createStatement();
			String query = "SELECT * FROM listedproperties WHERE quadrant='"+ quad+"'";
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				stringList.add(new String[]{ rs.getString("ID"), rs.getString("TYPE"), String.valueOf(rs.getInt("numberOfBaths")),
						String.valueOf( rs.getInt("numberOfBedrooms")), (rs.getString("FURNISHED").equals("1")) ? "Yes" : "No",
								rs.getString("ADDRESS"), rs.getString("QUADRANT"), rs.getString("STATEOFLISTING") }) ;
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException i)
		{
			i.printStackTrace();
		}
		String[][] propertyData = new String[stringList.size()][];
		for(int i = 0; i < stringList.size(); i++)
		{
			propertyData[i] = stringList.get(i);
		}
		return propertyData;
	}
}



