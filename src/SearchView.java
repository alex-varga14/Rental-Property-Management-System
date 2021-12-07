import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	String[] propertyTypes = {"", PropertyTypes.APARTMENT.toString(), PropertyTypes.CONDO.toString(), PropertyTypes.ATTACHED_HOUSE.toString(), PropertyTypes.DETACHED_HOUSE.toString(), 
			PropertyTypes.TOWN_HOUSE.toString(), PropertyTypes.SINGLE_FAMILY_HOME.toString(), PropertyTypes.MULTI_FAMILY_HOME.toString() };
	String[] numBedrooms = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] numBathrooms = {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] furnishedArr = {"","Yes", "No" };
	String[] quadrants = {"", Quadrant.SE.toString(), Quadrant.SW.toString(), Quadrant.NW.toString(), Quadrant.NE.toString() };
	
	public SearchView() {
		setTitle("Search Listed Master Management Properties - Search Properties");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		JLabel text = new JLabel("Search Properties - ");
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setBounds(10, 31, 305, 20);
    	getContentPane().add(text);
		
		JLabel filters = new JLabel("Filters - ");
		JLabel propertyType = new JLabel("Property Type:");
		JLabel numofBedrooms = new JLabel("# of Bedrooms:");
		JLabel numofBathrooms = new JLabel("# of Bathrooms:");
		JLabel furnished = new JLabel("Furnished:");
		JLabel quadrant = new JLabel("City Quadrant:");
		
		JTextField searchInput = new JTextField();
		searchInput.setBounds(20, 64, 630, 26);
    	getContentPane().add(searchInput);
		searchInput.setColumns(10);
		
		filters.setFont(new Font("Arial", Font.BOLD, 20));
		filters.setBounds(10, 200, 80, 50);
		getContentPane().add(filters);
		
		propertyType.setBounds(10, 250, 90, 20);
    	getContentPane().add(propertyType);
    	JComboBox<String> propertyComboBox = new JComboBox<>(propertyTypes);
    	propertyComboBox.setBounds(10, 280, 130, 20);
        getContentPane().add(propertyComboBox);
        
        numofBedrooms.setBounds(150, 250, 130, 20);
    	getContentPane().add(numofBedrooms);
    	JComboBox<String> bedroomsComboBox = new JComboBox<>(numBedrooms);
    	bedroomsComboBox.setBounds(150, 280, 80, 20);
        getContentPane().add(bedroomsComboBox);
        
        numofBathrooms.setBounds(250, 250, 130, 20);
    	getContentPane().add(numofBathrooms);
    	JComboBox<String> bathroomsComboBox = new JComboBox<>(numBathrooms);
    	bathroomsComboBox.setBounds(250, 280, 80, 20);
        getContentPane().add(bathroomsComboBox);
        
        furnished.setBounds(350, 250, 130, 20);
    	getContentPane().add(furnished);
    	JComboBox<String> furnishedComboBox = new JComboBox<>(furnishedArr);
    	furnishedComboBox.setBounds(350, 280, 80, 20);
        getContentPane().add(furnishedComboBox);
        
        quadrant.setBounds(450, 250, 130, 20);
    	getContentPane().add(quadrant);
    	JComboBox<String> quadrantComboBox = new JComboBox<>(quadrants);
    	quadrantComboBox.setBounds(450, 280, 80, 20);
        getContentPane().add(quadrantComboBox);
        
        JButton search = new JButton("Search Properties");
        search.setBounds(80, 95, 90, 20);
    	getContentPane().add(search);
    	
	}

}
