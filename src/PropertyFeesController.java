import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PropertyFeesController implements ActionListener
{
	private PropertyFeesView theView;
	private Property currentProperty;
	
	public PropertyFeesController(Property p)
	{
		currentProperty = p;
		theView = new PropertyFeesView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	
	public void addListenersToView()
	{
		theView.addListPropertyListener(this);
		theView.addCancelListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database com = Database.getInstance();
		
		if(e.getActionCommand().equals("list property")) {
			theView.setVisible(false);
			com.getConnection();
			com.listNewProperty(currentProperty);
			PropertyListedView congrats = new PropertyListedView();
			
			LanlordController test = new LanlordController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			LanlordController test = new LanlordController();
		}
	}
}
