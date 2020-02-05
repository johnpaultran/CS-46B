package weather;

public class CirrusCloud extends Cloud
{
	public CirrusCloud(float bottom, float top)
	{
		super(bottom, top);
	}
	
	public String rain()
	{
		return "I cannot make rain";
	}
}
