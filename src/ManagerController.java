import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController implements ActionListener
{
	private ManagerView theView;
	
	public ManagerController()
	{
		theView = new ManagerView();
		theView.setVisible(true);
		//addListenersToClass();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
