package movieDBQueries;

import java.util.ArrayList;
import java.util.List;

import movieDBModel.Database;
import movieDBModel.Movie;

public class allCastMembersQuery extends DatabaseQuery
{
	List<String> castMembers;
	
	public allCastMembersQuery(Database database) {
		super(database);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateQueryResult() {
		databaseMovies = database.getAllMovies();
		
		castMembers = new ArrayList<String>();
		
		for (Movie movie : databaseMovies)
		{
			List<String> curCast = (List<String>) movie.getCast();
			
			for(String castMember : curCast)
			{
				if(!castMembers.contains(castMember))
				{
					castMembers.add(castMember);
				}
			}
		}
		
	}

	@Override
	protected void printQueryResult() {
		for (String castMember : castMembers)
		{
			System.out.println(castMember);
		}
		
	}

}
