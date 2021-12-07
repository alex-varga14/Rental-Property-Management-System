import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegPropertyView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	JButton enterButton = new JButton("Register Property");
	String[] propertyTypes = {"", PropertyTypes.APARTMENT.toString(), PropertyTypes.CONDO.toString(), PropertyTypes.ATTACHED_HOUSE.toString(), PropertyTypes.DETACHED_HOUSE.toString(), 
			PropertyTypes.TOWN_HOUSE.toString(), PropertyTypes.SINGLE_FAMILY_HOME.toString(), PropertyTypes.MULTI_FAMILY_HOME.toString() };
	String[] numBedrooms = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] numBathrooms = {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] furnishedArr = {"","Yes", "No" };
	String[] quadrants = {"", Quadrant.SE.toString(), Quadrant.SW.toString(), Quadrant.NW.toString(), Quadrant.NE.toString() };
	
	JComboBox<String> propertyComboBox = new JComboBox<>(propertyTypes);
	JComboBox<String> bedroomsComboBox = new JComboBox<>(numBedrooms);
	JComboBox<String> bathroomsComboBox = new JComboBox<>(numBathrooms);
	JComboBox<String> furnishedComboBox = new JComboBox<>(furnishedArr);
	JComboBox<String> quadrantComboBox = new JComboBox<>(quadrants);
	JTextField addressInput = new JTextField();
	
	public RegPropertyView()
	{
		setTitle("Master Rental Property Management - Register Property");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(550, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		JLabel text = new JLabel("Register New Property - ");
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setBounds(10, 10, 305, 30);
    	getContentPane().add(text);
    	
    	enterButton.setFont(new Font("Arial", Font.BOLD, 13));
    	enterButton.setBounds(185, 125, 150, 30);
    	getContentPane().add(enterButton);
    	enterButton.setActionCommand("register property");
    	
    	JLabel propertyType = new JLabel("Property Type:");
		JLabel numofBedrooms = new JLabel("# of Bedrooms:");
		JLabel numofBathrooms = new JLabel("# of Bathrooms:");
		JLabel furnished = new JLabel("Furnished:");
		JLabel quadrant = new JLabel("City Quadrant:");
		JLabel address = new JLabel("Address:");
		
		propertyType.setBounds(10, 40, 90, 20);
    	getContentPane().add(propertyType);
    	propertyComboBox.setBounds(10, 60, 115, 20);
        getContentPane().add(propertyComboBox);
        
        numofBedrooms.setBounds(140, 40, 130, 20);
    	getContentPane().add(numofBedrooms);
    	bedroomsComboBox.setBounds(170, 60, 40, 20);
        getContentPane().add(bedroomsComboBox);
        
        numofBathrooms.setBounds(240, 40, 130, 20);
    	getContentPane().add(numofBathrooms);
    	bathroomsComboBox.setBounds(270, 60, 40, 20);
        getContentPane().add(bathroomsComboBox);
        
        furnished.setBounds(345, 40, 130, 20);
    	getContentPane().add(furnished);
    	furnishedComboBox.setBounds(350, 60, 50, 20);
        getContentPane().add(furnishedComboBox);
        
        quadrant.setBounds(430, 40, 130, 20);
    	getContentPane().add(quadrant);
    	quadrantComboBox.setBounds(450, 60, 50, 20);
        getContentPane().add(quadrantComboBox);
        
        address.setFont(new Font("Arial", Font.BOLD, 16));
        address.setBounds(10, 95, 90, 20);
    	getContentPane().add(address);
        addressInput.setBounds(100, 90, 300, 30);
    	getContentPane().add(addressInput);
    	addressInput.setColumns(10);
		
		
	}
	
	public void addRegPropertyListener(ActionListener al)  {
    	enterButton.addActionListener(al);
	}
	
	public String getPropertyBox()
	{
		return propertyComboBox.getSelectedItem().toString();
	}
	
	public int getBedroomsBox()
	{
		return Integer.parseInt(bedroomsComboBox.getSelectedItem().toString());
	}
	
	public int getBathroomsBox()
	{
		return Integer.parseInt(bathroomsComboBox.getSelectedItem().toString());
	}

	public boolean getFurnishedBox()
	{
		if(furnishedComboBox.getSelectedItem().toString().equals(("Yes")))
			return true;
		else
			return false;
	}
	
	public String getQuadrantBox()
	{
		return quadrantComboBox.getSelectedItem().toString();
	}
	
	public String getAddressField()
	{
		return addressInput.getText();
	}
	

}
