import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SummaryController implements ActionListener
{
	private SummaryView theView;
	
	public SummaryController()
	{
		theView = new SummaryView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addHomePageListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("home")) {
			theView.setVisible(false);
			ManagerController managerController = new ManagerController();
		}
	}
}