package controller;
import java.awt.event.*;

import view.EmailView;
import view.SendEmailView;

public class EmailController implements ActionListener
{
	private EmailView theView;
	private SendEmailView theViewSend;
	
	
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
		//theView.addStartListener(this);
	}
	public void addListenersToViewSend()
	{
		//theView.addStartListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}