package controller;
import java.awt.event.*;

import model.Database;
import model.Lanlord;
import model.Manager;
import model.RegisteredRenter;
import model.PropertyFees;
import view.EmailView;
import view.SendEmailView;

//Controller class for the Email implementations for Lanlord and Renters
public class EmailController implements ActionListener
{
	//add both the send email and inbox email view
	private EmailView theView;
	private SendEmailView theViewSend;
	private String sendTo;
	private LanlordController lanlord;
	private RegisteredRenterController rr;
	private ClientController client;
	//get instance of database 
	Database com = Database.getInstance();
	
	//CTOR taking in email mode 
	// 1. send
	// 2. inbox
	// ID for Renters interested in properties
	public EmailController(String mode, String ID)
	{
		if(mode.equals("inbox"))
		{
			theView = new EmailView();
			this.addListenersToView();
			theView.setVisible(true);
		}
		else if(mode.equals("send"))
		{
			com.getConnection();
			sendTo = com.findLanlordEmail(ID);
			theViewSend = new SendEmailView();
			this.addListenersToViewSend();
			theViewSend.setVisible(true);
		}
	}
	//Overloaded CTOR for Lanlord access, will not be interested in property therefore no ID argument required
	public EmailController(String mode)
	{
		if(mode.equals("inbox"))
		{
			theView = new EmailView();
			this.addListenersToView();
			theView.setVisible(true);
		}
		else if(mode.equals("send"))
		{
			theViewSend = new SendEmailView();
			this.addListenersToViewSend();
			theViewSend.setVisible(true);
		}
	}
	
	public void addListenersToView()
	{
		theView.addHomePageListener(this);
	}
	public void addListenersToViewSend()
	{
		theViewSend.addSendEmailListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send email")) {
			theViewSend.setVisible(false);
			com.getConnection();
			if(Lanlord.getInstance().getEmailAddress() != null) {
				com.sendEmail(Lanlord.getInstance().getEmailAddress(), sendTo, theViewSend.getBody());
				lanlord  = new LanlordController();
			}
			else if (RegisteredRenter.getInstance().getEmailAddress() != null){
				com.sendEmail(RegisteredRenter.getInstance().getEmailAddress(), sendTo, theViewSend.getBody());
				rr = new RegisteredRenterController();
			}
			else
			{
				com.sendEmail("Guest Renter", sendTo, theViewSend.getBody());
				client = new ClientController();
			}
		}
		if(e.getActionCommand().equals("home")) {
			theView.setVisible(false);
			lanlord = new LanlordController();
		}
	}
	
}