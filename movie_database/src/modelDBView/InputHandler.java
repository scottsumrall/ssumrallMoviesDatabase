package modelDBView;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import movieDBModel.Database;
import movieDBModel.Movie;
import movieDBQueries.*;

public class InputHandler 
{

	DatabaseQuery currentQuery;
	Database database;
	
	public InputHandler(Database database)
	{
		this.database = database;

	}

	public String getUserQuery()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose an option:\nT Show the total earnings in the database for a single year.\nF List the top x most frequent directors in the database.\nD List all directors in the database.\nC List top cast members by movie ranking type\nT Get the total gross earnings of movie by year\nM Get list of all cast members in included movies");
		String queryType = scanner.nextLine();
		return queryType;
	}
}
