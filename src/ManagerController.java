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
			PropertyController propertyController = new PropertyController();
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
			InformationController renterInfoController = new InformationController("Registered Renter");
		}
		if(e.getActionCommand().equals("view lanlords")) {
			theView.setVisible(false);
			InformationController lanlordInfoController = new InformationController("Lanlord");
		}
		if(e.getActionCommand().equals("view property info")) {
			theView.setVisible(false);
			InformationController propertyInfoController = new InformationController("Property");
		}
		if(e.getActionCommand().equals("view summary")) {
			theView.setVisible(false);
			SummaryController summaryController = new SummaryController();
		}
		
	}
}
