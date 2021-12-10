package model;
import java.util.ArrayList;

public class RegisteredRenter 
{
	public ArrayList<PropertyListings> favouritedProperties;
	private static RegisteredRenter instance;
	private String emailAddress;
	private String password;
	
	public RegisteredRenter(String emailAddress, String password) {
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	public RegisteredRenter() {}
	
	public static RegisteredRenter getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new RegisteredRenter(emailAddress, password);
		}
		return instance;
	}
	
	public static RegisteredRenter getInstance()
	{
		if(instance == null) 
		{
			instance = new RegisteredRenter();
		}
		return instance;
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
