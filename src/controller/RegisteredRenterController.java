package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import view.RegisteredRenterView;

//Controller class for the Registered Renters users
public class RegisteredRenterController implements ActionListener
{
	private RegisteredRenterView theView;
	private SearchController searchController;
	private EmailController sendEmail;
	
	public RegisteredRenterController()
	{
		theView = new RegisteredRenterView();
		theView.setVisible(true);
		this.addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addSearchListener(this);
		theView.addSendEmailListener(this);
		theView.addInboxListener(this);
		theView.addMatchesListener(this);
	}

	//Action commands for searching properties, viewing matched properties, inbox, and send email
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search")) {
			theView.setVisible(false);
			searchController = new SearchController();
		}
		if(e.getActionCommand().equals("matched")) {
			theView.setVisible(false);
			//SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("inbox")) {
			theView.setVisible(false);
			sendEmail = new EmailController("inbox");
		}
		if(e.getActionCommand().equals("send email")) {
			theView.setVisible(false);
			sendEmail = new EmailController("send");
		}
	}
}
