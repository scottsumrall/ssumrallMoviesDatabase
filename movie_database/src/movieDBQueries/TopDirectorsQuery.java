package movieDBQueries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import movieDBModel.Database;
import movieDBModel.Movie;

public class TopDirectorsQuery extends allDirectorsQuery
{
	HashMap<String, Integer> directorsCount = new HashMap<>();

	public TopDirectorsQuery(Database database) {
		super(database);

	}
	
	@Override
	public void generateQueryResult() {
		databaseMovies = database.getAllMovies();
		
		//Count the number of times each director appears
		for (Movie movie : databaseMovies)
		{
			String director = movie.getDirector();
			
			Integer count = directorsCount.get(director);
			if (count == null) {
			    directorsCount.put(director, 1);
			}
			else {
			    directorsCount.put(director, count + 1);
			}
		}
		int numDirectors = queryForNumDirectors();	
		
		//Sort the hashmap of directors by number of appearances
		//https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
		List<Map.Entry<String, Integer> > list =
	               new LinkedList<Map.Entry<String, Integer> >(directorsCount.entrySet());
	 
	        // Sort the list
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2)
	            {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });
	    
	    directors = new ArrayList<String>();
	        
	    for(int x = 0; x < numDirectors; x++)
	    {
	    	directors.add(list.get(x).getKey());
	    }
	}
	
	private int queryForNumDirectors()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of directors you wish to display");
		return Integer.parseInt(scanner.nextLine());
	}
}
