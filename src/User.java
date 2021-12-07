
public class User 
{
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String userType;
	
	public User(String firstName, String lastName, String emailAddress, String password, String user)
	{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
		this.setUserType(user);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
