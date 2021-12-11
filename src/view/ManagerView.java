package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//Manager Homepage view
public class ManagerView extends JFrame
{
	//all necessary components to implement manager functionalities
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	private JMenu properties = new JMenu("Properties");
	private JMenu propertieFees = new JMenu("Property Fees/Period");
	private JMenu personInformation = new JMenu("Personel Information");
	private JMenu periodicalSummary = new JMenu("Periodical Summary");
	private JMenuItem viewListedProperties = new JMenuItem("View Properties");
	private JMenuItem changeFees = new JMenuItem("Change Property Fees");
	private JMenuItem changePeriod = new JMenuItem("Change Property Listing Period");
	private JMenuItem renters = new JMenuItem("View Renters");
	private JMenuItem lanlords = new JMenuItem("View Lanlords");
	private JMenuItem propertiesInfo = new JMenuItem("View Properties Information");
	private JMenuItem viewSummary = new JMenuItem("View Summary");
	
	//CTOR to construct manager homepage
	public ManagerView()
	{
		setTitle("Master Rental Property Management - Manager Signed in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(img.getImage());
		try
		{
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/MMBackground.jpg")))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		JMenuBar bar = new JMenuBar();
		
		properties.setMnemonic(KeyEvent.VK_S);
		properties.add(viewListedProperties);
		viewListedProperties.setActionCommand("view listed");
		
		propertieFees.setMnemonic(KeyEvent.VK_S);
		propertieFees.add(changeFees);
		propertieFees.add(changePeriod);
		changeFees.setActionCommand("change fees");
		changePeriod.setActionCommand("change period");
		
		personInformation.setMnemonic(KeyEvent.VK_S);
		personInformation.add(renters);
		personInformation.add(lanlords);
		personInformation.add(propertiesInfo);
		renters.setActionCommand("view renters");
		lanlords.setActionCommand("view lanlords");
		propertiesInfo.setActionCommand("view property info");
		
		periodicalSummary.setMnemonic(KeyEvent.VK_S);
		periodicalSummary.add(viewSummary);
		viewSummary.setActionCommand("view summary");
	
		
		bar.add(properties);
		bar.add(propertieFees);
		bar.add(Box.createHorizontalGlue());
		bar.add(personInformation);
		bar.add(periodicalSummary);
		
		
	    setJMenuBar(bar);
		}
		
		//action listeners for all functional components related to manager view
		public void addViewPropertyListener(ActionListener a) {
			viewListedProperties.addActionListener(a);
		}
		public void addChangePropertyFeeListener(ActionListener a) {
			changeFees.addActionListener(a);
		}
		public void addPropertyPeriodListener(ActionListener a) {
			changePeriod.addActionListener(a);
		}
		public void addViewRentersListener(ActionListener a) {
			renters.addActionListener(a);
		}
		public void addViewLanlordsListener(ActionListener a) {
			lanlords.addActionListener(a);
		}
		public void addPropertyInfoListener(ActionListener a) {
			propertiesInfo.addActionListener(a);
		}
		public void addSummaryListener(ActionListener a) {
			viewSummary.addActionListener(a);
		}
		
}
