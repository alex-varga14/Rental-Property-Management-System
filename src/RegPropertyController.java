import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegPropertyController implements ActionListener
{
	private RegPropertyView theView;
	private Lanlord currentLanlord;
	private Property currentProperty;
	
	public RegPropertyController()
	{
		theView = new RegPropertyView();
		this.addListenersToView();
		theView.setVisible(true);
	}
	
	
	public void addListenersToView()
	{
		theView.addRegPropertyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("register property")) {
			theView.setVisible(false);
			currentProperty = currentLanlord.getInstance().getLanlord().registerProperty(theView.getPropertyBox(), theView.getAddressField(), theView.getBathroomsBox(), theView.getBedroomsBox()
					, theView.getFurnishedBox(), theView.getQuadrantBox());
			PropertyFeesController feeController = new PropertyFeesController(currentProperty);
		}
		
	}
	
}
