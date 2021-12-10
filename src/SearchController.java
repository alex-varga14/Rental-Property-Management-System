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
			PropertyController properties = new PropertyController();
		}
	}
}
/*
 if((theView.getPropertyBox().equals(""))&& (String.valueOf(theView.getBedroomsBox()).equals(""))&& (String.valueOf(theView.getBathroomsBox()).equals("")) &&
					(String.valueOf(theView.getFurnishedBox()).equals("")) && (theView.getQuadrantBox().equals("")))
			{
				PropertyController prop = new PropertyController();
			}
			PropertyController properties = new PropertyController(theView.getPropertyBox(), theView.getBedroomsBox(),theView.getBathroomsBox(),
					theView.getFurnishedBox(), theView.getQuadrantBox());
					*/
