package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class ClientView extends JFrame
{
	private JButton start = new JButton("View Properties");
	private JMenu sourceMenu = new JMenu("Login or Sign Up");
	private JMenuItem login = new JMenuItem("Login");
	private JMenuItem signUp = new JMenuItem("Sign Up");
	ImageIcon img = new ImageIcon("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/Untitled.png");
	
	public ClientView() {
		setTitle("Master Rental Property Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width/2, height/2);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(img.getImage());
		try
		{
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Alex School\\eclipse-workspace\\ENSF480_RPMS\\src\\assets/ClientBackground.jpg")))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		JLabel info = new JLabel("We Specialize in Rental Property Management", JLabel.CENTER);
		info.setForeground(Color.white);
		info.setFont(new Font("Arial", Font.BOLD, 25));
		info.setBounds(50, 225, 600, 40);
		getContentPane().add(info);
		
		JMenuBar bar = new JMenuBar();
		
		JMenu company = new JMenu("Master Management");
		company.setMnemonic(KeyEvent.VK_O);
		JMenuItem aboutUs = new JMenuItem("About");
		company.add(aboutUs);
		JMenuItem services = new JMenuItem("Services");
		company.add(services);
		
        bar.add(company);
        bar.add(Box.createHorizontalGlue());
        
        sourceMenu.setMnemonic(KeyEvent.VK_S);
        sourceMenu.add(login);
        sourceMenu.add(signUp);
        login.setActionCommand("sign in");
        signUp.setActionCommand("sign up");
        bar.add(sourceMenu);
        setJMenuBar(bar);
		
		start.setBounds(240, 275, 160, 50);
		start.setForeground(Color.black);
		start.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(start);
		start.setActionCommand("view properties");
    }
	
	public void addStartListener(ActionListener a)  {
    	start.addActionListener(a);
	}
	
	public void addSignInListener(ActionListener a) {
		login.addActionListener(a);
	}
	
	public void addSignUpListener(ActionListener a) {
		signUp.addActionListener(a);
	}

}
