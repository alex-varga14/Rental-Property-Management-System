package model;
import java.sql.Connection;
import java.util.ArrayList;

//Lanlord Model
//Singleton Design Pattern
public class Lanlord
{
	//Fields to hold lanlord instance, lanlordProperties,email and password
	private ArrayList<Property> LanlordProperties = new ArrayList<Property>();
	private static Lanlord instance;
	private String emailAddress;
	private String password;
	
	//Lanlord ctor
	public Lanlord(String emailAddress, String password)
	{
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	//default ctor for lanlord
	public Lanlord() {}
	
	//Initial sign up lanlord Instance
	public static Lanlord getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new Lanlord(emailAddress, password);
		}
		return instance;
	}
	//get lanlord instance once signed up
	public static Lanlord getInstance()
	{
		if(instance == null) 
		{
			instance = new Lanlord();
		}
		return instance;
	}
	
	//Register property function, sets property fees and period as well as ACTIVE listing state
	public Property registerProperty(String type, String address, int nBath, int nBed, boolean furn, String quad, String email)
	{
		Property newProperty = new Property(type, address, nBath, nBed, furn, quad);
		newProperty.setInformation(new PropertyFees());
		newProperty.setState(ListingState.ACTIVE);
		newProperty.setLanlordEmail(this.getEmailAddress());
		LanlordProperties.add(newProperty);
		return newProperty;
	}
	
	//Simple getters and setters for functionality
	public ArrayList<Property> getLanlordProperties()
	{
		return LanlordProperties;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
