package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Database;
import model.Property;
import view.PropertyFeesView;

//Controller class for the PropertyFee implementations
public class PropertyFeesController implements ActionListener
{
	private PropertyFeesView theView;
	private Property currentProperty;
	private LanlordController lanlord;
	
	//takes in current property
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

	//Action Commands for listing new property and cancelling actions
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Database com = Database.getInstance();
		
		if(e.getActionCommand().equals("list property")) {
			theView.setVisible(false);
			com.getConnection();
			com.listNewProperty(currentProperty);
			lanlord = new LanlordController();
		}
		if(e.getActionCommand().equals("cancel")) {
			theView.setVisible(false);
			lanlord = new LanlordController();
		}
	}
}
