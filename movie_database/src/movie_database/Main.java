package movie_database;

import movieDBController.Controller;

public class Main 
{
	public static void main(String args[])
	{
		Controller controller = new Controller();
		controller.startDatabase();
	}
}
