package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LanlordView;

public class LanlordController implements ActionListener
{
	private LanlordView theView;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view listed")) {
			theView.setVisible(false);
			PropertyController propertyController = new PropertyController();
		}
		if(e.getActionCommand().equals("register new property")) {
			theView.setVisible(false);
			RegPropertyController registerPropertyController = new RegPropertyController();
		}
		if(e.getActionCommand().equals("send email")) {
			theView.setVisible(false);
			EmailController sendEmail = new EmailController("send");
		}
		if(e.getActionCommand().equals("inbox")) {
			theView.setVisible(false);
			EmailController sendEmail = new EmailController("inbox");
		}
		
	}

}
