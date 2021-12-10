
public class Manager
{
	private static Manager instance;
	private String emailAddress;
	private String password;

	public Manager(String emailAddress, String password) {
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	
	public Manager() {}
	
	public static Manager getInstance(String emailAddress, String password)
	{
		if(instance == null) 
		{
			instance = new Manager(emailAddress, password);
		}
		return instance;
	}
	
	public static Manager getInstance()
	{
		if(instance == null) 
		{
			instance = new Manager();
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
}
