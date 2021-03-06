package model;


//Property Model
public class Property 
{
	//Fields for all required property data
	private String typeOfProperty;
	private String address;
	private int numberOfBathrooms;
	private int numberOfBedrooms;
	private boolean furnished; //0 for no, 1 yes
	private String quadrant;
	private PropertyFees information;
	private ListingState state;
	private String lanlordEmail;
	
	//CTOR to instantiate a property
	public Property(String type, String a, int nBath, int nBed, boolean furnished, String quad)
	{
		for(PropertyTypes t : PropertyTypes.values())
		{
			String property = t.name();
			if(propertyGeneralization(type.strip().toUpperCase()).equals(property))
				{
					this.typeOfProperty = property.toString();
					break;
				}
		}
		this.setTypeOfProperty(type);
		this.setAddress(a);
		this.setNumberOfBathrooms(nBath);
		this.setNumberOfBedrooms(nBed);
		this.setFurnished(furnished);
		this.setQuadrant(quad);
	}
	
	//Simple function to format ENUM property Type into proper valid strings
	public String propertyGeneralization(String t)
	{
		StringBuilder tmp = new StringBuilder();
		for(int i = 0; i < t.length(); i++)
		{
			if(t.charAt(i) == ' ') {
				tmp.append("_");
			}
			else
			{
				tmp.append(t.charAt(i));
			}
		}
		return tmp.toString();
	}
	
	//Functional getters and setters
	public void setTypeOfProperty(String type)
	{
		typeOfProperty = type;
	}
	
	public String getTypeOfProperty()
	{
		return typeOfProperty;
	}
	
	public void setInformation(PropertyFees newFees)
	{
		information = newFees;
	}
	public PropertyFees getInformation()
	{
		return information;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public ListingState getState() {
		return state;
	}

	public void setState(ListingState state) {
		this.state = state;
	}

	public String getLanlordEmail() {
		return lanlordEmail;
	}

	public void setLanlordEmail(String lanlordEmail) {
		this.lanlordEmail = lanlordEmail;
	}
}

