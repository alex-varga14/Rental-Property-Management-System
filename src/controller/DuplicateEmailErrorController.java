package controller;
import java.awt.event.*;

import view.DuplicateEmailErrorView;

//Controller class for Duplicate Email view when signing up
public class DuplicateEmailErrorController implements ActionListener
{
	private DuplicateEmailErrorView errorView;
	private SignUpController signUpController;
	private LoginController loginController;
	
	public DuplicateEmailErrorController()
	{
		errorView = new DuplicateEmailErrorView();
		errorView.setVisible(true);
		addListenersToClass();
	}
	
	private void addListenersToClass()
	{
		errorView.addTryAgainListener(this);
		errorView.addSignInListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("try again")) {
			errorView.setVisible(false);
			signUpController = new SignUpController();
		}
		if(e.getActionCommand().equals("sign in")) {
			errorView.setVisible(false);
			loginController = new LoginController();
		}
	}
}
