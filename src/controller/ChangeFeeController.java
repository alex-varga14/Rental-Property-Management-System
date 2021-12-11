package controller;
import java.awt.event.*;

import model.PropertyFees;
import view.ChangeFeeView;

//Controller class for the Change Fee implementations for Manager Users
public class ChangeFeeController implements ActionListener
{
	//The Change Fee View associated with the controller
	private ChangeFeeView theView;
	private ManagerController managerController;
	
	//Ctor, initialize new view, set it visible and add its listeners to the controller
	public ChangeFeeController()
	{
		theView = new ChangeFeeView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	//Function to add all listeners to view
	public void addListenersToView()
	{
		theView.addChangeFeeListener(this);
		theView.addCancelListener(this);
	}

	//ActionListener commands
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("change fee")) {
			theView.setVisible(false);
			//get singleton instance of property Fees
			PropertyFees currentFee = PropertyFees.getInstance();
			currentFee.setFee(Integer.parseInt(theView.getNewFeeField()));
			managerController = new ManagerController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			managerController = new ManagerController();
		}
	}
}
