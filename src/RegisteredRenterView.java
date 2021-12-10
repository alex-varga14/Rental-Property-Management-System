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

public class RegisteredRenterView extends JFrame
{
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	private JMenu searchProperties = new JMenu("Search Properties");
	private JMenu matchedProperties = new JMenu("Matching Properties");
	private JMenu emails = new JMenu("Emails");
	private JMenuItem search = new JMenuItem("Search Listed");
	private JMenuItem matching = new JMenuItem("View Matched Listings");
	private JMenuItem inbox = new JMenuItem("Inbox");
	private JMenuItem sendEmail = new JMenuItem("Send Email");
	
	public RegisteredRenterView()
	{
		setTitle("Master Rental Property Management - Registered Renter Signed in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(230, 230, 250));
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		try
		{
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/RRBackground.jpg")))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		JMenuBar bar = new JMenuBar();
		
		searchProperties.setMnemonic(KeyEvent.VK_S);
		searchProperties.add(search);
		search.setActionCommand("search");
		
		matchedProperties.setMnemonic(KeyEvent.VK_S);
		matchedProperties.add(matching);
		matching.setActionCommand("matched");
		
		bar.add(searchProperties);
		bar.add(matchedProperties);
		bar.add(Box.createHorizontalGlue());
		
		emails.setMnemonic(KeyEvent.VK_S);
		emails.add(inbox);
		inbox.setActionCommand("inbox");
		emails.add(sendEmail);
		sendEmail.setActionCommand("send email");
		bar.add(emails);
		
        setJMenuBar(bar);
	}
	
	public void addSearchListener(ActionListener a) {
		search.addActionListener(a);
	}
	
	public void addMatchesListener(ActionListener a) {
		matching.addActionListener(a);
	}
	
	public void addInboxListener(ActionListener a) {
		inbox.addActionListener(a);
	}
	public void addSendEmailListener(ActionListener a) {
		sendEmail.addActionListener(a);
	}
}
