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

public class EarningsByYearQuery extends DatabaseQuery
{
	HashMap<Integer, Float> earningsByYear = new HashMap<>();
	private static final String GROSS_RANK_TYPE = "gross";
	int targetYear;
	
	public EarningsByYearQuery(Database database) {
		super(database);

	}

	@Override
	protected void generateQueryResult() {
		databaseMovies = database.getRankedMovies(GROSS_RANK_TYPE);
		
		for (Movie movie : databaseMovies)
		{
			int year = movie.getYear();
;
			Float count = earningsByYear.get(year);
			if (count == null) {
				earningsByYear.put(year, movie.getAdditionalData(GROSS_RANK_TYPE));
			}
			else {
				earningsByYear.put(year, year + movie.getAdditionalData(GROSS_RANK_TYPE));
			}
		}
		
		//Sort the hashmap of directors by number of appearances
		//https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
		List<Map.Entry<Integer, Float> > list =
	               new LinkedList<Map.Entry<Integer, Float> >(earningsByYear.entrySet());
	 
	        // Sort the list
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Float> >() {
	            public int compare(Map.Entry<Integer, Float> o1,
	                               Map.Entry<Integer, Float> o2)
	            {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });
	    
	        queryForCastInformation();
	}
	
	private void queryForCastInformation()
	{
		Scanner scanner = new Scanner(System.in);		
		System.out.println("Enter the desired year as a number");
		targetYear = Integer.parseInt(scanner.nextLine());
	}

	@Override
	protected void printQueryResult() {
		int totalGross = Math.round(earningsByYear.get(targetYear));
		System.out.println(totalGross);
		
	}

}
