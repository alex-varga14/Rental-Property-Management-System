import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUpController implements ActionListener
{
	private SignUpView theView;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database com = Database.getInstance();
		
		if(e.getActionCommand().equals("sign up")) {
			theView.setVisible(false);
			com.getConnection();
			com.addUser(theView.getEmail(),theView.getPassword(), theView.getUser());
		}
	}
}
