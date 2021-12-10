package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PropertyView;

public class PropertyController implements ActionListener
{
	private PropertyView theView;
	
//	public PropertyController(String type, int nBath, int nBed, boolean furn, String quad)
//	{
//		theView = new PropertyView(type, nBath, nBed, furn, quad);
//		this.addListenersToView();
//		theView.setVisible(true);
//	}
	
	public PropertyController()
	{
		theView = new PropertyView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	
	public void addListenersToView()
	{
		theView.addEmailListener(this);
		theView.addStateListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("email")) {
			theView.setVisible(false);
			EmailController sendEmail = new EmailController("send");
		}
		if(e.getActionCommand().equals("state")) {
			theView.setVisible(false);
			ChangeStateController changeState = new ChangeStateController(theView.getID(), theView.getSelected());
		}
	}
	
}
