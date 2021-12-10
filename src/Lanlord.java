import java.sql.Connection;
import java.util.ArrayList;

public class Lanlord
{
	private ArrayList<Property> LanlordProperties = new ArrayList<Property>();
	private static Lanlord instance;
	private String emailAddress;
	private String password;
	
	public Lanlord(String emailAddress, String password)
	{
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	
	public Lanlord() {
	}
	
	public static Lanlord getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new Lanlord(emailAddress, password);
		}
		return instance;
	}
	
	public static Lanlord getInstance()
	{
		if(instance == null) 
		{
			instance = new Lanlord();
		}
		return instance;
	}
	
	
	public Property registerProperty(String type, String address, int nBath, int nBed, boolean furn, String quad, String email)
	{
		Property newProperty = new Property(type, address, nBath, nBed, furn, quad);
		newProperty.setInformation(new PropertyFees());
		System.out.println("Fees paid");
		newProperty.setState(ListingState.ACTIVE);
		new PropertyListings(newProperty);
		newProperty.setLanlordEmail(this.getEmailAddress());
		System.out.println("Email: "+ email);
		LanlordProperties.add(newProperty);
		return newProperty;
	}
	
	public Property getPropertyIndex(int i) {
		return LanlordProperties.get(i);
	}
	
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
