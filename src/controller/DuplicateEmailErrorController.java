package controller;
import java.awt.event.*;

import view.DuplicateEmailErrorView;

public class DuplicateEmailErrorController implements ActionListener
{
	private DuplicateEmailErrorView errorView;
	
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
			SignUpController signUpController = new SignUpController();
		}
		if(e.getActionCommand().equals("sign in")) {
			errorView.setVisible(false);
			LoginController loginController = new LoginController();
		}
	}
}
