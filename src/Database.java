import java.sql.*;
import java.util.*;

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
	 
		
	public void listNewProperty(Property n)
	{
        try {
        	String query = "INSERT INTO listedproperties ( type, numberOfBaths, numberOfBedrooms, furnished, address, quadrant, stateOfListing, listingStart, listingEnd) "
        			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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


