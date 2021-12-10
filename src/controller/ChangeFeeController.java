package controller;
import java.awt.event.*;

import model.PropertyFees;
import view.ChangeFeeView;

public class ChangeFeeController implements ActionListener
{
	private ChangeFeeView theView;
	
	public ChangeFeeController()
	{
		theView = new ChangeFeeView();
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addChangeFeeListener(this);
		theView.addCancelListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("change fee")) {
			theView.setVisible(false);
			PropertyFees currentFee = PropertyFees.getInstance();
			currentFee.setFee(Integer.parseInt(theView.getNewFeeField()));
			ManagerController searchController = new ManagerController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			ManagerController searchController = new ManagerController();
		}
	}
}
