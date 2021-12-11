package model;
import java.util.ArrayList;

//Registered Renter Model
//Singleton Design Pattern
public class RegisteredRenter 
{
	//Fields to hold matched properties, email and password
	//private MatchingProperties notifications;
	private static RegisteredRenter instance;
	private String emailAddress;
	private String password;
	
	//Initial RR ctor for sign up
	public RegisteredRenter(String emailAddress, String password) {
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	//default ctor for once signed up
	public RegisteredRenter() {}
	
	//Initial sign up RegisteredRenter Instance
	public static RegisteredRenter getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new RegisteredRenter(emailAddress, password);
		}
		return instance;
	}
	
	//get RegisteredRenter instance once signed up
	public static RegisteredRenter getInstance()
	{
		if(instance == null) 
		{
			instance = new RegisteredRenter();
		}
		return instance;
	}
	
	//Simple getters and setters for functionality
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
