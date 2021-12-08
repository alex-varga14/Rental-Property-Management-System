import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePeriodController implements ActionListener
{
	private ChangePeriodView theView;
	
	public ChangePeriodController()
	{
		theView = new ChangePeriodView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addChangePeriodListener(this);
		theView.addCancelListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("change period")) {
			theView.setVisible(false);
			PropertyFees currentPeriod = PropertyFees.getInstance();
			currentPeriod.setPeriod(Integer.parseInt(theView.getNewPeriodField()));
			ManagerController searchController = new ManagerController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			ManagerController searchController = new ManagerController();
		}
	}
}
