package controller;
import java.awt.event.*;

import model.Database;
import model.Lanlord;
import model.Manager;
import model.RegisteredRenter;
import model.PropertyFees;
import view.EmailView;
import view.SendEmailView;

public class EmailController implements ActionListener
{
	private EmailView theView;
	private SendEmailView theViewSend;
	private String sendTo;
	Database com = Database.getInstance();
	
	
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
				LanlordController client = new LanlordController();
			}
			else if (RegisteredRenter.getInstance().getEmailAddress() != null){
				com.sendEmail(RegisteredRenter.getInstance().getEmailAddress(), sendTo, theViewSend.getBody());
				RegisteredRenterController client = new RegisteredRenterController();
			}
			else
			{
				com.sendEmail("Guest Renter", sendTo, theViewSend.getBody());
				ClientController client = new ClientController();
			}
		}
		if(e.getActionCommand().equals("home")) {
			theView.setVisible(false);
			LanlordController client = new LanlordController();
		}
	}
	
}