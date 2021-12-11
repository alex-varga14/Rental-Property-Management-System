package controller;
import java.awt.event.*;

import view.ClientView;

//Main controller of the application, essentially the homescreen controller
public class ClientController implements ActionListener
{
	//The Client View associated with the controller
	private ClientView theView;
	private SearchController searchController;
	private LoginController loginController;
	private SignUpController signUpController;
	
	//Ctor, initialize new view, set it visible and add its listeners to the controller
	public ClientController()
	{
		theView = new ClientView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	//Function to add all listeners to view
	public void addListenersToView()
	{
		theView.addStartListener(this);
		theView.addSignInListener(this);
		theView.addSignUpListener(this);
	}

	//ActionListener commands
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view properties")) {
			theView.setVisible(false);
			searchController = new SearchController();
		}
		if(e.getActionCommand().equals("sign in")) {
			theView.setVisible(false);
			loginController = new LoginController();
		}
		if(e.getActionCommand().equals("sign up")) {
			theView.setVisible(false);
			signUpController = new SignUpController();
		}
	}
}
