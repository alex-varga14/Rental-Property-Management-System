import java.util.ArrayList;

public class RegisteredRenter extends User 
{
	public ArrayList<PropertyListings> favouritedProperties;
	
	public RegisteredRenter(String firstName, String lastName, String emailAddress, String password, String user) {
		super(firstName, lastName, emailAddress, password, user);
	}
	/*
	public void update(ArrayList<PropertyListings> in)
	{
		favouritedProperties.clear();
	        for(int i = 0; i < in.size(); i++)
	        {
	        	favouritedProperties.add(in.get(i));
	        }
	        display();
	}
	
	 public void display()
	    {
	        System.out.println();
	        System.out.println("Interested In properties:");
	        for(int i = 0; i < favouritedProperties.size(); i++)
	        {
	            System.out.print(favouritedProperties.get(i) + " ");
	        }
	    } */

}
