package bubble;

import java.util.*;


public class Statistician 
{
	 private final static int N_REPETITIONS = 1000;
	private static void getStats(int arrayLength)
	{
		ArrayList<Long> visitCounts = new ArrayList<>();
		ArrayList<Long> swapCounts = new ArrayList<>();
		
		for (int i=0; i<N_REPETITIONS; i++)
		{
			int[] array = BubbleSortTestCaseMaker.buildRandom(arrayLength, arrayLength*100);
			BubbleSorter sorter = new BubbleSorter(array);
			sorter.sort();
			// Assert that the sorter sorted correctly.
			assert sorter.isSorted() : "Array is sorted";
			// Append # visits and # swaps to the array lists.
		}

		// Compute and print min/average/max number of visits.
		// Compute and print min/average/max number of swaps.
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("1000:");
		getStats(1000);
		
		System.out.println("3000:");
		getStats(3000);
	}
}
