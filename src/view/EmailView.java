package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Database;

public class EmailView extends JFrame
{
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	Database access = Database.getInstance();
	
	String[] columnNames = { "Sender", "Email"};
	JTable inbox;
	String[][] dataa;
	JButton backHome = new JButton("Return Home");
	
	public EmailView()
	{
		setTitle("Master Rental Property Management - Email Inbox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		populateInbox();
		inbox = new JTable(dataa, columnNames);
		
		inbox.setDefaultEditor(Object.class, null);
		JScrollPane sp = new JScrollPane(inbox);
		sp.setBounds(0, 0, width/2, 100);
		getContentPane().add(sp);
		
		backHome.setBounds(250, 300, 120, 30);
	   	getContentPane().add(backHome);
	   	backHome.setActionCommand("home");
	}
	
	public void populateInbox()
	{
		dataa = access.getAllMessages();
	}
	
	public void addHomePageListener(ActionListener a)  {
		backHome.addActionListener(a);
	 }
}
