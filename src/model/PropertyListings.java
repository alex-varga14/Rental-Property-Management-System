package model;
import java.util.ArrayList;

public class PropertyListings 
{
	private ArrayList<Property> allListedProperties = new ArrayList<Property>();
	
	public PropertyListings(Property newListedProperty)
	{
		allListedProperties.add(newListedProperty);
	}
	
	public void changeListingState(String listingID, ListingState state) 
	{
		
	}	
}
