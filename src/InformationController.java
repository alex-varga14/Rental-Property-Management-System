import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationController implements ActionListener
{
	private InformationView theView;
	
	public InformationController(String infoType)
	{
		theView = new InformationView(infoType);
		theView.setVisible(true);
		addListenersToView();
	}
	
	public void addListenersToView()
	{
		//theView.addViewPropertyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}