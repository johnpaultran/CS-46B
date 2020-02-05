package func;

public class ComplexNormFunction implements DoubleFunctionOfTwoInts 
{
	@Override
	public double fOfXY(int x, int y)
	{
		Complex a = new Complex(x, y);
		return a.norm();
	}
	
	@Override
	public String getName()
	{
		return "ComplexNormFunction";
	}
}
