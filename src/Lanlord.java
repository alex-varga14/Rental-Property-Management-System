import java.sql.Connection;
import java.util.ArrayList;

public class Lanlord //extends User 
{
	private ArrayList<Property> LanlordProperties = new ArrayList<Property>();
	private static Lanlord instance;
	
	public Lanlord(String firstName, String lastName, String emailAddress, String password)
	{
		//super(firstName, lastName, emailAddress, password, "Lanlord");
	}
	
	public Lanlord() {
	}
	
	public static Lanlord getInstance()
	{
		if(instance == null) 
		{
			instance = new Lanlord();
		}
		return instance;
	}
	
	public Lanlord getLanlord() 
	{
		return instance;
	}
	
	public Property registerProperty(String type, String address, int nBath, int nBed, boolean furn, String quad)
	{
		Property newProperty = new Property(type, address, nBath, nBed, furn, quad);
		newProperty.setInformation(new PropertyFees());
		System.out.println("Fees paid");
		newProperty.setState(ListingState.ACTIVE);
		new PropertyListings(newProperty);
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

}
