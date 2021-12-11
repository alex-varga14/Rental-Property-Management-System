package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;


//send email view for sending emails
public class SendEmailView extends JFrame
{
	//JtextArea for body of email
	ImageIcon img = new ImageIcon(".\\src\\assets/Untitled.png");
	JTextArea message = new JTextArea();
    JButton sendEmail = new JButton("Send Email");
	
    //set up JFrame
	public SendEmailView()
	{
		setTitle("Master Rental Property Management - Send Email");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setResizable(false);
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
        
        sendEmail.setActionCommand("send email");
        JLabel space2 = new JLabel("  ");

        JScrollPane emailBody = new JScrollPane(message);
        emailBody.setPreferredSize(new Dimension(100, 400));
        emailBody.setBorder(BorderFactory.createTitledBorder("Email Body"));
        emailBody.setViewportView(message);
        emailBody.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel bigButtons = new JPanel();
        bigButtons.setLayout(new BoxLayout(bigButtons, BoxLayout.PAGE_AXIS));
        bigButtons.add(sendEmail);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(emailBody);
        all.add(space2);
        all.add(bigButtons);
        all.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(all);
	}
	//action listener to send email
	public void addSendEmailListener(ActionListener a)  {
		sendEmail.addActionListener(a);
	}
	//getter for JTextArea
	public String getBody ()
	{
		return message.getText();
	}
}
