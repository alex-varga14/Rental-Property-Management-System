import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PropertyListedView extends JFrame 
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");

	 public PropertyListedView() {
	    	setTitle("Master Rental Property Management - Login");
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(Color.white);
			setSize(500, 180);
			setResizable(false);
			setLocationRelativeTo(null);
			setIconImage(img.getImage());
			
			JLabel text = new JLabel("Your Property has been Listed!");
			text.setFont(new Font("Arial", Font.BOLD, 20));
			text.setBounds(10, 31, 305, 20);
	    	getContentPane().add(text);
	 }
}
