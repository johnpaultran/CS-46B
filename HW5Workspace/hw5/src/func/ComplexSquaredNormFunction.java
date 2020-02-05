package func;

public class ComplexSquaredNormFunction implements DoubleFunctionOfTwoInts
{
	@Override
	public double fOfXY(int x, int y)
	{
		Complex a = new Complex(x, y);
		Complex b = Complex.multiply(a, a);
		return b.norm();
	}
	
	
	@Override
	public String getName()
	{
		return "ComplexSquaredNormFunction";
	}
}
