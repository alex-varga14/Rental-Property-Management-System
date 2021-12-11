package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Database;
import view.PropertyView;

//Controller class for the Property 
public class PropertyController implements ActionListener
{
	private PropertyView theView;
	private EmailController sendEmail;
	private ChangeStateController changeState;
	
	public PropertyController()
	{
		theView = new PropertyView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	public PropertyController(boolean[] flags,  String type, int beds, int baths, int furn, String quad)
	{
		theView = new PropertyView(flags, type, beds, baths, furn, quad);
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	
	public void addListenersToView()
	{
		theView.addEmailListener(this);
		theView.addStateListener(this);
	}

	//Action listners for renters emailing if interested in property as well as lanlords and managers changing listing state
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("email")) {
			theView.setVisible(false);
			sendEmail = new EmailController("send", theView.getID());
		}
		if(e.getActionCommand().equals("state")) {
			theView.setVisible(false);
			changeState = new ChangeStateController(theView.getID(), theView.getSelected());
		}
	}
	
}
