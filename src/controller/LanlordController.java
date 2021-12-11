package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LanlordView;

//Controller class for the Lanlord users
public class LanlordController implements ActionListener
{
	private LanlordView theView;
	private PropertyController propertyController;
	private RegPropertyController registerPropertyController;
	private EmailController sendEmail;
	
	public LanlordController()
	{
		theView = new LanlordView();
		theView.setVisible(true);
		this.addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addViewPropertyListener(this);
		theView.addRegisterPropertyListener(this);
		theView.addSendEmailListener(this);
		theView.addInboxListener(this);
	}

	//ActionListeners for viewing listed properties, registering new property, sending and viewing email
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view listed")) {
			theView.setVisible(false);
			propertyController = new PropertyController();
		}
		if(e.getActionCommand().equals("register new property")) {
			theView.setVisible(false);
			registerPropertyController = new RegPropertyController();
		}
		if(e.getActionCommand().equals("send email")) {
			theView.setVisible(false);
			sendEmail = new EmailController("send");
		}
		if(e.getActionCommand().equals("inbox")) {
			theView.setVisible(false);
			sendEmail = new EmailController("inbox");
		}
		
	}

}
