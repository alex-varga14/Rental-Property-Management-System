package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.PropertyTypes;
import model.Quadrant;

public class SearchView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	String[] propertyTypes = {"", PropertyTypes.APARTMENT.toString(), PropertyTypes.CONDO.toString(), PropertyTypes.ATTACHED_HOUSE.toString(), PropertyTypes.DETACHED_HOUSE.toString(), 
			PropertyTypes.TOWN_HOUSE.toString(), PropertyTypes.SINGLE_FAMILY_HOME.toString(), PropertyTypes.MULTI_FAMILY_HOME.toString() };
	String[] numBedrooms = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] numBathrooms = {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] furnishedArr = {"","Yes", "No" };
	String[] quadrants = {"", Quadrant.SE.toString(), Quadrant.SW.toString(), Quadrant.NW.toString(), Quadrant.NE.toString() };
	JButton search = new JButton("Search Properties");
	JComboBox<String> propertyComboBox = new JComboBox<>(propertyTypes);
	JComboBox<String> bedroomsComboBox = new JComboBox<>(numBedrooms);
	JComboBox<String> bathroomsComboBox = new JComboBox<>(numBathrooms);
	JComboBox<String> furnishedComboBox = new JComboBox<>(furnishedArr);
	JComboBox<String> quadrantComboBox = new JComboBox<>(quadrants);
	
	public SearchView() {
		setTitle("Search Listed Master Management Properties - Search Properties");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		setSize(600, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
    	
		JLabel filters = new JLabel("Filters - ");
		JLabel propertyType = new JLabel("Property Type:");
		JLabel numofBedrooms = new JLabel("# of Bedrooms:");
		JLabel numofBathrooms = new JLabel("# of Bathrooms:");
		JLabel furnished = new JLabel("Furnished:");
		JLabel quadrant = new JLabel("City Quadrant:");
		
		filters.setFont(new Font("Arial", Font.BOLD, 20));
		filters.setBounds(10, 10, 305, 20);
		getContentPane().add(filters);
		
		propertyType.setBounds(10, 40, 90, 20);
    	getContentPane().add(propertyType);
    	propertyComboBox.setBounds(10, 70, 130, 20);
        getContentPane().add(propertyComboBox);
        
        numofBedrooms.setBounds(150, 40, 130, 20);
    	getContentPane().add(numofBedrooms);
    	bedroomsComboBox.setBounds(150, 70, 80, 20);
        getContentPane().add(bedroomsComboBox);
        
        numofBathrooms.setBounds(250, 40, 130, 20);
    	getContentPane().add(numofBathrooms);
    	bathroomsComboBox.setBounds(250, 70, 80, 20);
        getContentPane().add(bathroomsComboBox);
        
        furnished.setBounds(350, 40, 130, 20);
    	getContentPane().add(furnished);
    	furnishedComboBox.setBounds(350, 70, 80, 20);
        getContentPane().add(furnishedComboBox);
        
        quadrant.setBounds(450, 40, 130, 20);
    	getContentPane().add(quadrant);
    	quadrantComboBox.setBounds(450, 70, 80, 20);
        getContentPane().add(quadrantComboBox);
        
        search.setFont(new Font("Arial", Font.BOLD, 18));
        search.setBounds(170, 105, 240, 30);
        search.setActionCommand("search");
    	getContentPane().add(search);
	}
	
	public void addSearchListener(ActionListener a)  {
		search.addActionListener(a);
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
}
