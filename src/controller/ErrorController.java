package controller;
import java.awt.event.*;

import view.ErrorView;

public class ErrorController implements ActionListener
{
	private ErrorView errorView;
	
	public ErrorController()
	{
		errorView = new ErrorView();
		errorView.setVisible(true);
		addListenersToClass();
	}
	
	private void addListenersToClass()
	{
		errorView.addTryAgainListener(this);
		errorView.addSignUpListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("try again")) {
			errorView.setVisible(false);
			LoginController loginController = new LoginController();
		}
		if(e.getActionCommand().equals("sign up")) {
			errorView.setVisible(false);
			SignUpController signUpController = new SignUpController();
		}
	}

}
