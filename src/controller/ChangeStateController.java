package controller;
import java.awt.event.*;

import model.Database;
import model.Lanlord;
import model.Manager;
import view.ChangeStateView;

public class ChangeStateController implements ActionListener
{
	private ChangeStateView theView;
	private String propID;
	private LanlordController lanlordController;
	private ManagerController managerController;
	
	
	public ChangeStateController(String propID, String state)
	{
		theView = new ChangeStateView(state);
		this.setPropertyID(propID);
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		theView.addChangeStateListener(this);
		theView.addCancelListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Database com = Database.getInstance();
		
		if(e.getActionCommand().equals("change state")) {
			theView.setVisible(false);
			
			com.getConnection();
			com.updatePropertyState(getPropertyID(), theView.getStateBox());
			if(Lanlord.getInstance().getEmailAddress() != null) {
				LanlordController lanlordController = new LanlordController();
			}
			else if(Manager.getInstance().getEmailAddress() != null)
			{
				managerController = new ManagerController();
			}
			
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			if(Lanlord.getInstance().getEmailAddress() != null) {
				lanlordController = new LanlordController();
			}
			else if(Manager.getInstance().getEmailAddress() != null)
			{
				managerController = new ManagerController();
			}
		}
	}
	
	public String getPropertyID()
	{
		return propID;
	}
	
	public void setPropertyID(String i) {
		propID = i;
	}
}