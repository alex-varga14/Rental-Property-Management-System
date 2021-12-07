import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

public class Test {
	
	public static void main(String [] args) 
	{
		Database login = Database.getInstance();
		ClientController theController = new ClientController();

//		Database login;
//		Lanlord newLanlord = new Lanlord("James", "Smith", "jamesSmith123@gmail.com", "ILoveDog");
//		login = Database.getInstance();
//		login.initializeConnection(newLanlord.getEmailAddress(), newLanlord.getPassword(), "Lanlord");
//		newLanlord.registerProperty("Apartment", "123 Solana Lane", 6, 8, false, "SW");
//		newLanlord.registerProperty("Attached House", "1 Solana Lane", 1, 1, false, "SE");
//		newLanlord.registerProperty("Detached House", "872 Solana Lane", 2, 4, false, "NW");
//		newLanlord.registerProperty("Condo", "1521 Solana Lane", 2, 5, false, "NE");
//		newLanlord.registerProperty("Single Family Home", "99 Solana Lane", 1, 3, false, "SW");
//		for(int i = 0; i < newLanlord.getLanlordProperties().size(); i++)
//		{
//			login.listNewProperty(newLanlord.getPropertyIndex(i));
//		}
		
		//Search listings,
		// - can make builder pattern to make it work for various amounts of arugments
		//login.searchListings("Apartment", 2, 5, true, "NW");
	}
}
