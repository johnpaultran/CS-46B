package func;

public class MyCreativeFunction implements DoubleFunctionOfTwoInts
{
	@Override
	public double fOfXY(int x, int y)
	{
		Complex a = new Complex(x, y);
		Complex b = Complex.multiply(a, a);
		Complex c = Complex.multiply(b, b);
		return c.norm();
	}
	
	
	@Override
	public String getName()
	{
		return "MyCreativeFunction";
	}
}
