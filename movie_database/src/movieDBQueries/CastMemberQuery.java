package movieDBQueries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



import movieDBModel.Database;
import movieDBModel.Movie;

public class CastMemberQuery extends DatabaseQuery
{
	private String rankingType;
	private int rank;
	private String castOrDirector;
	
	private ArrayList<String> results = new ArrayList<String>();
	
	private static final String CAST = "cast";
	private static final String DIRECTOR = "director";
	

	public CastMemberQuery(Database database) {
		super(database);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateQueryResult() {
		queryForCastInformation();
		

		
		databaseMovies = database.getRankedMovies(rankingType);
		Movie targetMovie = databaseMovies.get(rank-1);
		
		if(castOrDirector.equals(CAST))
		{
			results = (ArrayList<String>) targetMovie.getCast();
		}
		else if(castOrDirector.equals(DIRECTOR))
		{
			results.add(targetMovie.getDirector());
		}
	}

	private void queryForCastInformation()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the type of movie rank you would like to seek as 'gross' or 'toprated'");
		rankingType = scanner.nextLine();
		
		System.out.println("Enter the desired rank as a number");
		rank = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Indicate if you would like cast members or director by typing 'cast' or 'director'");
		castOrDirector = scanner.nextLine();
	}
	
	@Override
	protected void printQueryResult() {
		for(String result : results)
		{
			System.out.println(result);
		}
		
	}

}
