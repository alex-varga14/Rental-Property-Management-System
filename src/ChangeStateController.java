import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeStateController implements ActionListener
{
	private ChangeStateView theView;
	private String propID;
	
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
				ManagerController searchController = new ManagerController();
			}
			
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			if(Lanlord.getInstance().getEmailAddress() != null) {
				LanlordController lanlordController = new LanlordController();
			}
			else if(Manager.getInstance().getEmailAddress() != null)
			{
				ManagerController searchController = new ManagerController();
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