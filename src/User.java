
public class User 
{
	private String emailAddress;
	private String password;
	
	
	public User(String emailAddress, String password)
	{
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}
	
	public User() {}

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
