package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SearchView;

//Search Controller for search functions available to renters and reg renters
public class SearchController implements ActionListener
{
	private SearchView theView;
	private PropertyController properties;
	private boolean[] flags = new boolean[5];
	
	public SearchController()
	{
		theView = new SearchView();
		this.addListenersToView();
		theView.setVisible(true);
		for(int i = 0; i < 5; i++) {
			flags[i]= false;
		}
	}
	
	public void addListenersToView()
	{
		theView.addSearchListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search")) {
			theView.setVisible(false);
			int bool = 0;
			int bath = 0;
			int bed = -0;
			if(!theView.getPropertyBox().equals(""))
			{
				flags[0] = true;
			}
			if(!theView.getBedroomsBox().equals(""))
			{
				flags[1] = true;
				bed = Integer.parseInt(theView.getBedroomsBox());
			}
			if(!theView.getBathroomsBox().equals(""))
			{
				flags[2] = true;
				bath = Integer.parseInt(theView.getBathroomsBox());
			}
			if(theView.getFurnishedBox() == true)
			{
				flags[3] = true;
				bool = 1;
				
			}
			if(!theView.getQuadrantBox().equals(""))
			{
				flags[4] = true;
			}
			for(int i = 0; i < 5; i++) {
				System.out.println(flags[i]);
			}
			
			if(flags[0] == true || flags[1] == true || flags[2] == true|| flags[3] == true|| flags[4] == true)
			{
				System.out.println("Bath " + bath + " Bed " + bed);
				
				properties = new PropertyController(flags,theView.getPropertyBox(), bed, bath ,bool, theView.getQuadrantBox());
			}
			else
			{
				properties = new PropertyController();
			}
		}
	}
}