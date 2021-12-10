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

public class LanlordView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	private JMenu properties = new JMenu("Properties");
	private JMenu emails = new JMenu("Emails");
	private JMenuItem viewListedProperties = new JMenuItem("My Listed Properties");
	private JMenuItem regNewProperty = new JMenuItem("Register New Property");
	private JMenuItem inbox = new JMenuItem("Inbox");
	private JMenuItem sendEmail = new JMenuItem("Send Email");
	
	public LanlordView()
	{
		setTitle("Master Rental Property Management - Lanlord Signed in");
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
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/LLBackground.jpg")))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		JMenuBar bar = new JMenuBar();
		
		properties.setMnemonic(KeyEvent.VK_S);
		properties.add(viewListedProperties);
		properties.add(regNewProperty);
		viewListedProperties.setActionCommand("view listed");
		regNewProperty.setActionCommand("register new property");

		
		bar.add(properties);
		bar.add(Box.createHorizontalGlue());
		
		emails.setMnemonic(KeyEvent.VK_S);
		emails.add(inbox);
		inbox.setActionCommand("inbox");
		emails.add(sendEmail);
		sendEmail.setActionCommand("send email");
		bar.add(emails);
		
        setJMenuBar(bar);
	}
	
	public void addViewPropertyListener(ActionListener a) {
		viewListedProperties.addActionListener(a);
	}
	public void addRegisterPropertyListener(ActionListener a) {
		regNewProperty.addActionListener(a);
	}
	public void addSendEmailListener(ActionListener a) {
		sendEmail.addActionListener(a);
	}
	public void addInboxListener(ActionListener a) {
		inbox.addActionListener(a);
	}
	
}
