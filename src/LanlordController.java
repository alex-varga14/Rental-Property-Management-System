import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanlordController implements ActionListener
{
	private LanlordView theView;
	
	public LanlordController()
	{
		theView = new LanlordView();
		theView.setVisible(true);
		this.addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addViewPropertyListener(this);
		theView.addRegisterPropertyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view listed")) {
			theView.setVisible(false);
			//SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("register new property")) {
			theView.setVisible(false);
			RegPropertyController registerPropertyController = new RegPropertyController();
		}
		
	}

}
