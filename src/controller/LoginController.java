package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Database;
import model.Lanlord;
import model.Manager;
import model.RegisteredRenter;
import view.LoginView;

//Controller class for the Login implementations 
public class LoginController implements ActionListener
{
	private LoginView loginView;
	private String loginType;
	private ErrorController errorController;
	private RegisteredRenterController registeredRenter;
	private LanlordController lanlord;
	private ManagerController manager;
	
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

	//ActionListenrs to determine what action to perform and what user to singleton instantiate
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database access = Database.getInstance();
		
		if(e.getActionCommand().equals("login")) {
			loginView.setVisible(false);
			access.getConnection();
			loginType = access.findUser(loginView.getEmail(), loginView.getPassword());
			if(loginType.equals(""))
			{
				errorController = new ErrorController();
			}
			if(loginType.equals("Lanlord"))
			{
				Lanlord l = Lanlord.getInstance(loginView.getEmail(), loginView.getPassword());
			}
			if(loginType.equals("Manager"))
			{
				Manager l = Manager.getInstance(loginView.getEmail(), loginView.getPassword());
			}
			if(loginType.equals("Registered Renter"))
			{
				RegisteredRenter l = RegisteredRenter.getInstance(loginView.getEmail(), loginView.getPassword());
			}
			homeScreen(loginType);
		}
	}
	//Function to send specific user to set homepage
	private void homeScreen(String login)
	{
		switch(login)
		{
		case "Registered Renter":
			registeredRenter = new RegisteredRenterController();
			break;
		case "Lanlord":
			lanlord = new LanlordController();
			break;
		case "Manager":
			manager = new ManagerController();
			break;
		}
	}
}
