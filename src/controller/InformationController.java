package controller;
import java.awt.event.*;

import view.InformationView;

//Controller class for Manager Information Access 
public class InformationController implements ActionListener
{
	private InformationView theView;
	
	public InformationController(String infoType)
	{
		theView = new InformationView(infoType);
		theView.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}