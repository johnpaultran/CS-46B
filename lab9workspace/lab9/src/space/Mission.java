package space;

import java.util.ArrayList;


public class Mission implements Comparable<Mission>
{
	private String			destination;
	private float			cost;
	
	
	public Mission(String destination, Float cost)
	{
		this.destination = destination;
		this.cost = cost;
	}
	
	
	public String getDestination()
	{
		return destination;
	}
	
	
	public float getCost()
	{
		return cost;
	}


	// Compare by cost, then by destination.
	public int compareTo(Mission that) 
	{ 		
		if(that.getCost()==this.getCost())
		{
			return this.getDestination().compareTo(that.getDestination());
		}
		else if(this.getCost()>that.getCost())
		{
			return 1;
		}
		return -1;
	}
	
	
	// Let compareTo() do the work. This method should just be 1 line.
	public boolean equals(Object x)
	{
		return this.compareTo((Mission)x)==0;
	}
	
	
	// As you saw in lecture, create an ArrayList<Object>. Add destination and
	// cost to the list. Return the list's hash code.
	public int hashCode()
	{
		ArrayList<Object> gg = new ArrayList<>();
		gg.add(destination);
		gg.add(cost);
		return gg.hashCode();
	}
	
	
	public String toString()
	{
		return "Mission to " + destination + " will cost " + cost;
	}
}
