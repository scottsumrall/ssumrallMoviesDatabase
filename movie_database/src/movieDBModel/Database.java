package movieDBModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Database 
{
	List<Movie> databaseMovies;
	
	HashMap<String, List<Movie>> moviesByRankings = new HashMap<String, List<Movie>>();
	

	
	public void addDatabaseMovies(List<Movie> databaseMovies)
	{
		this.databaseMovies = databaseMovies;
	}
	
	public void addRankedMovies(String rankingType, LinkedHashMap<String, String> titlesRanked)
	{
		moviesByRankings.put(rankingType, new ArrayList<Movie>());
		for(String title : titlesRanked.keySet())
		{
			int index = Collections.binarySearch(databaseMovies, title, (m1, m2) -> ((Movie) m1).getTitle().compareTo((String) m2));
			
			//if movie was found, add it to the ranked list and add the earnings
		    if (index >= 0) {
		    	databaseMovies.get(index).addAdditionalData(rankingType, Float.parseFloat(titlesRanked.get(title)));
		        moviesByRankings.get(rankingType).add(databaseMovies.get(index));
		    }
		}
		
		/*Debug:
		for(Movie movie : moviesByRankings.get(rankingType))
		{
			System.out.println(movie.toString());
		}
		*/
	}
	
	public List<Movie> getAllMovies()
	{
		return databaseMovies;
	}
	
	public List<Movie> getRankedMovies(String rankingType)
	{
		return moviesByRankings.get(rankingType);	
	}
}
