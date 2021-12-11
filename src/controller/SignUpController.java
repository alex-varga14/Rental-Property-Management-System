package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import model.Database;
import model.Lanlord;
import model.Manager;
import model.RegisteredRenter;
import view.SignUpView;

//Sign up controller for new users signing up
public class SignUpController implements ActionListener
{
	//declares types of users
	private SignUpView theView;
	private Lanlord l;
	private Manager m;
	private RegisteredRenter rr;
	
	public SignUpController()
	{
		theView = new SignUpView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	public void addListenersToView()
	{
		theView.addSignUpListener(this);
	}

	//Action commands for signing up user and their desginated functionalities
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database com = Database.getInstance();
		
		if(e.getActionCommand().equals("sign up")) {
			theView.setVisible(false);
			com.getConnection();
			if(theView.getUser().equals("Lanlord"))
			{
				l.getInstance(theView.getEmail(),theView.getPassword());
			}
			if(theView.getUser().equals("Manager"))
			{
				m.getInstance(theView.getEmail(),theView.getPassword());
			}
			if(theView.getUser().equals("Registered Renter"))
			{
				rr.getInstance(theView.getEmail(),theView.getPassword());
			}
			com.addUser(theView.getEmail(),theView.getPassword(), theView.getUser());
		}
	}
}
