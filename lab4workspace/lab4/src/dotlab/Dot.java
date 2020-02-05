package dotlab;


public class Dot 
{
	private String colorName;
	private int x;
	private int y;
	private int radius;
	private static String[] LEGAL_COLOR_NAMES =
	{
		"RED", "YELLOW", "BLUE", "CYAN", "GREEN", "MAGENTA", "ORANGE", "BLACK"
	};
	
	public Dot(String colorName, int x, int y, int radius) throws IllegalArgumentException
	{
		boolean colorFound = false;
		
		for (int i = 0; i < LEGAL_COLOR_NAMES.length; i++)
		{
			String c = LEGAL_COLOR_NAMES[i];
			if (c.equals(colorName))
			{
				colorFound = true;
				this.colorName = colorName;
				break;
			}
		}
		
		if (!colorFound)
		{
			throw new IllegalArgumentException(this.colorName + " is not a legal color name");
		}
		
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public String getColorName()
	{
		return colorName;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	@Override
	public String toString()
	{
		String dot = getColorName() + ", " + getX() + ", " + getY() + ", " + getRadius();
		return dot;
	}
	
	public static void main(String args[])
	{
		Dot a =  new Dot("RED" , 2, 2, 4);
		System.out.println(a.toString());
	}
}
