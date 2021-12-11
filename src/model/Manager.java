package model;

//Manager Model
//Singleton Design Pattern
public class Manager
{
	//Fields to store manager instance, email and password
	private static Manager instance;
	private String emailAddress;
	private String password;

	//Manager ctor
	public Manager(String emailAddress, String password) {
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	//default ctor for Manager
	public Manager() {}
	
	//Initial sign up Manager Instance
	public static Manager getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new Manager(emailAddress, password);
		}
		return instance;
	}
	//get Manager instance once signed up
	public static Manager getInstance()
	{
		if(instance == null) 
		{
			instance = new Manager();
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
