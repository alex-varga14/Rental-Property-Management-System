package controller;
import java.awt.event.*;

import view.ErrorView;

//Controller class for Invalid login input
public class ErrorController implements ActionListener
{
	private ErrorView errorView;
	private LoginController loginController;
	private SignUpController signUpController;
	
	public ErrorController()
	{
		errorView = new ErrorView();
		errorView.setVisible(true);
		addListenersToView();
	}
	
	private void addListenersToView()
	{
		errorView.addTryAgainListener(this);
		errorView.addSignUpListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("try again")) {
			errorView.setVisible(false);
			loginController = new LoginController();
		}
		if(e.getActionCommand().equals("sign up")) {
			errorView.setVisible(false);
			signUpController = new SignUpController();
		}
	}
}
