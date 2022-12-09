package movieDBModel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Movie 
{
	private String title;
	private int year;
	private String director;
	private Collection<String> cast;
	private HashMap<String, Float> additionalData = new HashMap<String, Float>();
	
	public Movie(String title, int year, String director, Collection<String> cast)
	{
		this.title = title;
		this.year = year;
		this.director = director;
		this.cast = cast;
	}


	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public String getDirector() {
		return director;
	}

	public Collection<String> getCast() {
		return cast;
	}
	
	public void addAdditionalData(String rankType, float value)
	{
		additionalData.put(rankType, value);
	}
	
	public float getAdditionalData(String rankType)
	{
		return additionalData.get(rankType);
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", year=" + year + ", director=" + director + ", cast=" + cast + "]";
	}
}
