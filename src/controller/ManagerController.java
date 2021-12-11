package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ManagerView;

//Controller class for the Manager users
public class ManagerController implements ActionListener
{
	private ManagerView theView;
	private PropertyController propertyController;
	private InformationController InfoController;
	private ChangeFeeController feeController;
	private ChangePeriodController periodController;
	private SummaryController summaryController;
	
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

	//ActionListeners for viewing listed properties, changing fees/period, viewing renters, lanlords or properties
	//and requesting summary
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("view listed")) {
			theView.setVisible(false);
			propertyController = new PropertyController();
		}
		if(e.getActionCommand().equals("change fees")) {
			theView.setVisible(false);
			feeController = new ChangeFeeController();
		}
		if(e.getActionCommand().equals("change period")) {
			theView.setVisible(false);
			periodController = new ChangePeriodController();
		}
		if(e.getActionCommand().equals("view renters")) {
			theView.setVisible(false);
			InfoController = new InformationController("Registered Renter");
		}
		if(e.getActionCommand().equals("view lanlords")) {
			theView.setVisible(false);
			InfoController = new InformationController("Lanlord");
		}
		if(e.getActionCommand().equals("view property info")) {
			theView.setVisible(false);
			InfoController = new InformationController("Property");
		}
		if(e.getActionCommand().equals("view summary")) {
			theView.setVisible(false);
			summaryController = new SummaryController();
		}
		
	}
}
