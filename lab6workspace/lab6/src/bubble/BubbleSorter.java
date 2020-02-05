package bubble;

public class BubbleSorter 
{
	private int[]		a;
	private long		nVisits;
	private long		nSwaps;
	
	
	public BubbleSorter(int[] a)
	{
		this.a = a; 
	}
	
	
	public void sort()
	{
		int n = a.length;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n-i-1; j++)
			{
				nVisits = nVisits + 2;
				if (a[j] > a[j+1])
				{
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					nSwaps++;
				}
			}
		}
	}
	
	
	public String toString()
	{
		String s = nVisits + " visits, " + nSwaps + " swaps\n{";
		for (int n: a)
			s += " " + n;
		s += " }";
		return s;
	}
	
	
	public boolean isSorted()
	{
		int prev = a[0];
		for (int i = 0; i < a.length; i++)
		{
			if (a[i] < prev)
			{
				return false;
			}
			prev = a[i];
		}
		return true;
	}
	
	
	public long getNVisits()
	{
		return nVisits;
	}
	
	
	public long getNSwaps()
	{
		return nSwaps;
	}
	
	
	public int[] getArray()
	{
		return a;
	}
	
	
	public static void main(String[] args)
	{
		int[] a = BubbleSortTestCaseMaker.getBackward();
		
		BubbleSorter sorter = new BubbleSorter(a);
		sorter.sort();
		System.out.println(sorter);
		System.out.println(sorter.isSorted()  ?  "SORTED"  :  "NOT SORTED");
	}
}
