package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Lanlord;
import model.Property;
import view.RegPropertyView;

public class RegPropertyController implements ActionListener
{
	private RegPropertyView theView;
	private Lanlord currentLanlord = Lanlord.getInstance();
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
			currentProperty = currentLanlord.registerProperty(theView.getPropertyBox(), theView.getAddressField(), theView.getBathroomsBox(), theView.getBedroomsBox()
					, theView.getFurnishedBox(), theView.getQuadrantBox(), currentLanlord.getEmailAddress());
			PropertyFeesController feeController = new PropertyFeesController(currentProperty);
		}
		
	}
	
}
