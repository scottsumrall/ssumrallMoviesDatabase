package movieDBQueries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import movieDBModel.Database;
import movieDBModel.Movie;

public class allDirectorsQuery extends DatabaseQuery
{
	List<String> directors;
	
	public allDirectorsQuery(Database database) 
	{
		super(database);

	}

	@Override
	public void generateQueryResult() {
		databaseMovies = database.getAllMovies();
		directors = new ArrayList<String>();
		
		for (Movie movie : databaseMovies)
		{
			String director = movie.getDirector();
			if(!directors.contains(director))
			{
				directors.add(director);
			}
		}
		
	}

	@Override
	protected void printQueryResult() {
		for(String director : directors)
		{
			System.out.println(director);
		}
		
	}
}
