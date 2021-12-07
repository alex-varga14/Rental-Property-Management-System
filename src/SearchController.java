import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener
{
	private SearchView theView;
	
	public SearchController()
	{
		theView = new SearchView();
		//this.addListenersToView();
		theView.setVisible(true);
	}
	
	
//	public void addListenersToView()
//	{
//		theView.addStartListener(this);
//		theView.addSignInListener(this);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getActionCommand().equals("view properties")) {
//			theView.setVisible(false);
//		}
//		else if(e.getActionCommand().equals("sign in")) {
//			theView.setVisible(false);
//			LoginController loginController = new LoginController();
//		}
		
	}
}
