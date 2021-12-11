package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.SummaryView;

//Summary Report Controller for manager functionality
public class SummaryController implements ActionListener
{
	private SummaryView theView;
	private ManagerController managerController;
	
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
			managerController = new ManagerController();
		}
	}
}