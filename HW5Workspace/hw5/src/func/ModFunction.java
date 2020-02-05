package func;

public class ModFunction implements DoubleFunctionOfTwoInts
{
	@Override
	public double fOfXY(int x, int y)
	{
		if (y==0)
		{
			y = 1;
		}
		return x%y;
	}
	
	@Override
	public String getName()
	{
		return "Modulo";
	}

}
