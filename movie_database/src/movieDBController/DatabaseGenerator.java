package movieDBController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import movieDBModel.Database;
import movieDBModel.Movie;

public class DatabaseGenerator 
{
    private static final String TITLE_KEY = "title";
    private static final String YEAR_KEY = "year";
    private static final String DIRECTOR_KEY = "director";
    private static final String CAST1_KEY = "cast 1";
    
    //all information for rank catagory should include rank, title, year, and data in that order
	private static final int RANK_CATAGORY_DATA_INDEX = 3;
	
    private static final int MAX_CAST_MEMBERS = 5;
    
	HashMap<String, Integer> headerIndices = new HashMap<>();
    
	private static final String RESOURCES_PATH = Paths.get(".").normalize().toAbsolutePath() + "/resources";
	private String allMoviesFileName;
	
	
	
	/**
	 * Adds all potential movies to the database, file should be in alphabetical order
	 * @param allMoviesFileName
	 */
	public void addAllMoviesToDatabase(String allMoviesFileName, Database database)
	{
		this.allMoviesFileName = allMoviesFileName;
		
		String path = RESOURCES_PATH + "/" + allMoviesFileName;
		
		List<Movie> allMovies = getMoviesArray(path);		
		
		database.addDatabaseMovies(allMovies);
		 /* Debug:
		for(Movie movie : allMovies)
		{
			System.out.println(movie.toString());
		}
		*/
	}
	
	/**
	 * Add all sub-categories of movies located in resources to database
	 */
	public void addSortedMoviesToDatabase(Database database)
	{
		File resourceDirectory = new File(RESOURCES_PATH);
		File[] filesInResources = resourceDirectory.listFiles();
		
		for(File file : filesInResources)
		{
			String fileName = file.getName();
			String path = RESOURCES_PATH + "/" + file.getName();
			
			if (!file.getName().equals(allMoviesFileName))
			{
				String[] fileNameComponents = fileName.split("_|\\.");
				
				String rankingType = fileNameComponents[fileNameComponents.length-2];
				//List<String> rankedTitles = getTitlesFromFile(path);
				LinkedHashMap<String, String> rankedTitles = getTitlesFromFile(path);
				database.addRankedMovies(rankingType, rankedTitles);
			}
		}
	}
	
	private LinkedHashMap<String, String> getTitlesFromFile(String path)
	{
		LinkedHashMap<String, String> movieTitles = new LinkedHashMap<String, String>();
		
		//ArrayList<String> movieTitles = new ArrayList<String>();
		try
		{

			BufferedReader currentReader = new BufferedReader(new FileReader(path));

			currentReader.readLine();
			
			//read through each line, delimit with comma to array, generate Movie object
			String line = currentReader.readLine();
			while(line != null)
			{
				String[] movieAsArray = line.split("\t");
				movieTitles.put(movieAsArray[headerIndices.get(TITLE_KEY)], movieAsArray[3]);
				line = currentReader.readLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return movieTitles;
	}
	
	private List<Movie> getMoviesArray(String path)
	{
		ArrayList<Movie> MoviesArray = new ArrayList<Movie>();
		try
		{

			BufferedReader currentReader = new BufferedReader(new FileReader(path));

			String line = currentReader.readLine();
			
			try {
				setHeaderIndices(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//read through each line, delimit with comma to array, generate Movie object
			line = currentReader.readLine();
			while(line != null)
			{
				String[] movieAsArray = line.split("\t");
				MoviesArray.add(convertArrayToMovie(movieAsArray));
				line = currentReader.readLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return MoviesArray;
	}
	
	private void setHeaderIndices(String headerLine) throws Exception
	{
		String[] headersArray = headerLine.split("\t");
		
		for(int x = 0; x< headersArray.length; x++)
		{
			String currentHeader = headersArray[x];
			//store the index of the title, year and director
			headerIndices.put(headersArray[x].toLowerCase(), x);	
		}
			
		if(!(headerIndices.containsKey(TITLE_KEY) && headerIndices.containsKey(YEAR_KEY) && headerIndices.containsKey(DIRECTOR_KEY)))
		{
			throw new Exception("Given database does not contain all required information");
		}
	}
	
	private Movie convertArrayToMovie(String[] movieAsArray)
	{
		if(movieAsArray.length < 3 && movieAsArray.length > 8)
		{
			return null;
		}
		
		//get all movie information from the array using the generated headerIndices
		String title = movieAsArray[headerIndices.get(TITLE_KEY)];
		int year = Integer.parseInt(movieAsArray[headerIndices.get(YEAR_KEY)]);
		String director = movieAsArray[headerIndices.get(DIRECTOR_KEY)];
		String cast1 = movieAsArray[headerIndices.get(CAST1_KEY)];

		ArrayList<String> cast = new ArrayList<String>();
		
		//Assume cast members are ordered consecutively starting at cast member 1
		for(int x = 0; x < MAX_CAST_MEMBERS; x++)
		{
			int index = x + headerIndices.get(CAST1_KEY);
			
			if(index == movieAsArray.length)
			{
				break;
			}
			cast.add(movieAsArray[index]);	
		}
		
		
		return new Movie(title, year, director, cast);
	}
	
}
