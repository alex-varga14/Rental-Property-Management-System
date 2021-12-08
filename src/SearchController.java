import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener
{
	private SearchView theView;
	
	public SearchController()
	{
		theView = new SearchView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	public void addListenersToView()
	{
		theView.addSearchListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search")) {
			theView.setVisible(false);
		}
	}
}
