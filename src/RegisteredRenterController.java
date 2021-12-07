import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class RegisteredRenterController implements ActionListener
{

	private RegisteredRenterView theView;
	
	public RegisteredRenterController()
	{
		theView = new RegisteredRenterView();
		theView.setVisible(true);
		this.addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addSearchListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search")) {
			theView.setVisible(false);
			SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("matched")) {
			theView.setVisible(false);
			//SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("inbox")) {
			theView.setVisible(false);
			//SearchController searchController = new SearchController();
			//EmailController
		}
		
	}
}
