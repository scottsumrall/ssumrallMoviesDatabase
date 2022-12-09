package movieDBQueries;
import java.util.List;
import movieDBModel.Database;
import movieDBModel.Movie;
import movieDBModel.*;

public abstract class DatabaseQuery 
{
	protected Database database;
	protected List<Movie> databaseMovies;
	
	public DatabaseQuery(Database database)
	{
		this.database = database;
	}
	
	public void query()
	{
		generateQueryResult();
		printQueryResult();
	}
	
	protected abstract void generateQueryResult();
	
	protected abstract void printQueryResult();
}
