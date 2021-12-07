import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener
{
	private LoginView loginView;
	private String loginType;
	
	public LoginController()
	{
		loginView = new LoginView();
		loginView.setVisible(true);
		addListenersToClass();
	}
	
	private void addListenersToClass()
	{
		loginView.addLoginListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database access = Database.getInstance();
		
		if(e.getActionCommand().equals("login")) {
			loginView.setVisible(false);
			access.getConnection();
			loginType = access.findUser(loginView.getEmail(), loginView.getPassword());
			if(loginType.equals(""))
			{
				ErrorController errorController = new ErrorController();
			}
			else {
			homeScreen(loginType);
			}
		}
		
	}
	
	private void homeScreen(String login)
	{
		switch(login)
		{
		case "Registered Renter":
			RegisteredRenterController registeredRenter = new RegisteredRenterController();
			break;
		case "Lanlord":
			LanlordController lanlord = new LanlordController();
			break;
		case "Manager":
			ManagerController manager = new ManagerController();
			break;
		}
	}

}
