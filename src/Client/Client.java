package Client;

import controller.ClientController;
import model.Database;

//Client class to start RPMS application
public class Client 
{
	Client(){}
	public static void main(String [] args) 
	{
		//takes in database username and password
		if(args.length != 2)
		{
			System.out.println("Invalid Database Information! Try Again");
			System.exit(1);
		}
		Database.initializeConnection(args[0], args[1]);
		
		ClientController theController = new ClientController();
	}
}
