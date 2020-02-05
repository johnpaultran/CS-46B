package movies;
import java.util.ArrayList;
import java.util.TreeSet;

public class ListFilmArchive extends ArrayList<Movie> implements FilmArchive
{
	
	public ArrayList<Movie> getSorted()
	{
		TreeSet<Movie> tree = new TreeSet<Movie>(this);
		ArrayList<Movie> movies = new ArrayList<Movie>(tree);
		return movies;
	}

	// A method that checks every movie in the array list for deep equality to arg 
	// If you find a match return false, otherwise add it to the array list
	public boolean add(Movie that) 
	{
		for (Movie a: this)
			if (a.getYear() == that.getYear() && a.getTitle().equals(that.getTitle()))
				return false;
		boolean reallyAdded = super.add(that);
		return reallyAdded;
	}
	
	public static void main(String[] args) 
	{
		ListFilmArchive archive = new ListFilmArchive();
		for (Movie m : Movie.getTestMovies())
			archive.add(m);
		for (Movie m : archive)
			System.out.println(m);
		System.out.println("**************");
		for (Movie m: archive.getSorted())
			System.out.println(m);
	}
}
