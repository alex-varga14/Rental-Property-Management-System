import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController implements ActionListener
{
	private ManagerView theView;
	
	public ManagerController()
	{
		theView = new ManagerView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addViewPropertyListener(this);
		theView.addChangePropertyFeeListener(this);
		theView.addPropertyPeriodListener(this);
		theView.addViewRentersListener(this);
		theView.addViewLanlordsListener(this);
		theView.addPropertyInfoListener(this);
		theView.addSummaryListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view listed")) {
			theView.setVisible(false);
			//SearchController searchController = new SearchController();
		}
		if(e.getActionCommand().equals("change fees")) {
			theView.setVisible(false);
			ChangeFeeController feeController = new ChangeFeeController();
		}
		if(e.getActionCommand().equals("change period")) {
			theView.setVisible(false);
			ChangePeriodController feeController = new ChangePeriodController();
		}
		if(e.getActionCommand().equals("view renters")) {
			theView.setVisible(false);
		}
		if(e.getActionCommand().equals("view lanlords")) {
			theView.setVisible(false);
		}
		if(e.getActionCommand().equals("view property info")) {
			theView.setVisible(false);
		}
		if(e.getActionCommand().equals("view summary")) {
			theView.setVisible(false);
		}
		
	}
}
