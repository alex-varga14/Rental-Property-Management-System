package controller;
import java.awt.event.*;

import model.PropertyFees;
import view.ChangePeriodView;

//Controller class for the Change Period implementations for Manager Users
public class ChangePeriodController implements ActionListener
{
	//The view Change Period View associated with the controller
	private ChangePeriodView theView;
	private ManagerController managerController;
	
	//Ctor, initialize new view, set it visible and add its listeners to the controller
	public ChangePeriodController()
	{
		theView = new ChangePeriodView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	//Function to add all listeners to view
	public void addListenersToView()
	{
		theView.addChangePeriodListener(this);
		theView.addCancelListener(this);
	}

	//ActionListener commands
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("change period")) {
			theView.setVisible(false);
			//get singleton instance of property Fees/period
			PropertyFees currentPeriod = PropertyFees.getInstance();
			currentPeriod.setPeriod(Integer.parseInt(theView.getNewPeriodField()));
			managerController = new ManagerController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			managerController = new ManagerController();
		}
	}
}
