import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController implements ActionListener
{
	private ClientView theView;
	
	public ClientController()
	{
		theView = new ClientView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	
	public void addListenersToView()
	{
		theView.addStartListener(this);
		theView.addSignInListener(this);
		theView.addSignUpListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view properties")) {
			theView.setVisible(false);
			SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("sign in")) {
			theView.setVisible(false);
			LoginController loginController = new LoginController();
		}
		if(e.getActionCommand().equals("sign up")) {
			theView.setVisible(false);
			SignUpController signUpController = new SignUpController();
		}
	}
}
