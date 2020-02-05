package movies;

public class Movie implements Comparable<Movie>
{
	private String title;
	private int year;
	
	// Constructor for Movie class
	public Movie(String title, int year)
	{
		this.title = title;
		this.year = year;
	}
	
	// A method needed to satisfy the Comparable<Movie> interface
	// Movies are compared by title then by year
	public int compareTo(Movie that) 
	{
		int titleCompareInteger = this.title.compareTo(that.title);
		if (titleCompareInteger != 0) 
			return titleCompareInteger;
		else if (titleCompareInteger == 0 && this.year == that.year)
			return titleCompareInteger;
		else if (this.year < that.year) 
			return -1;
		else 
			return 1;
	}
	
	public String getTitle() 
	{
		return title;
	}

	public int getYear() 
	{
		return year;
	}
	
	// A method that is compatible with compareTo
	public boolean equals(Object x) 
	{
			Movie that = (Movie) x;
			return this.compareTo(that) == 0;	
	}
	
	public String toString()
	{
		return "Movie " + this.title + " (" + this.year + ")";
	}
	
	// Creates an array of movies for methods to test
	public static Movie[] getTestMovies()
	{
		Movie[] testMovies = new Movie[10];
		testMovies[0] = new Movie("Batman",1998);
		testMovies[1] = new Movie("Batman", 2008);
		testMovies[2] = new Movie("Iron Man", 2010);
		testMovies[3] = new Movie("Thor", 2010);
		testMovies[4] = new Movie("Captain America", 2011);
		testMovies[5] = new Movie("Captain America", 2011);
		testMovies[6] = new Movie("Spiderman", 2016);
		testMovies[7] = new Movie("Hulk", 2004);
		testMovies[8] = new Movie("Hawkeye", 1994);
		testMovies[9] = new Movie("Superman", 1990);
		return testMovies;	
	}

	public int hashCode()
	{
		return title.hashCode() + year;
	}
}

