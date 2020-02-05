package space;

import java.util.*;


public class grade 
{
	private static boolean				verbose = true;
	
	
	static void setVerbose(boolean verbose)	
	{
		Grader46B.verbose = verbose;
	}
	
	
	/*
	 There are 8 homework assignments. HW6 is 25 points. The rest are 100 points.
	 
	 For HW5 and HW7, the score is the max of the 2 columns in Canvas.
	 
	 Possible 10 extra credit points for HW5.
	 
	 The lowest 100-point score is dropped. Then the total score is computed
	 and scaled to 40 points (because homework is 40% of final grade).
	*/
	static float computeHWScore(int hw1, int hw2, int hw3, int hw4, 
			                    int hw5, int hw5Again, int hw5XC, int hw6, int hw7, int hw7Again, int hw8)
	{
		if (verbose)
		{
			sop("Raw Homework Scores:\n1=" + hw1 + " 2=" + hw2 + " 3=" + hw3 + " 4=" + hw4 +
					" 5=" + hw5 + " 5again=" + hw5Again + " 5xc=" + hw5XC + " 6=" + hw6 + " 7=" +
					hw7 + " 7again=" + hw7Again + " 8=" + hw8);
		}
		
		// Collect scores into an array list. Score for HWN is at N-1.
		ArrayList<Integer> individualScores = new ArrayList<>();
		individualScores.add(hw1);
		individualScores.add(hw2);
		individualScores.add(hw3);
		individualScores.add(hw4);
		individualScores.add(Math.max(hw5, hw5Again) + hw5XC);
		individualScores.add(hw6);
		individualScores.add(Math.max(hw7, hw7Again));
		individualScores.add(hw8);
		if (verbose)
		{
			sop("==>>");
			for (int i=0; i<individualScores.size(); i++)
				System.out.print("HW" + (i+1) + "=" + individualScores.get(i) + "  ");
		}
		
		// Assert known constraints.
		assert individualScores.size() == 8  :  "Should have 8 scores, saw " + individualScores.size();
		for (int assignmentNum=1; assignmentNum<=8; assignmentNum++)
		{
			int score = individualScores.get(assignmentNum-1);
			assert score >= 0  :  "Negative score: " + score + " for HW" + assignmentNum;
			int max = 100;
			if (assignmentNum == 5)
				max = 110;
			if (assignmentNum == 6)
				max = 25;
			assert score <= max  :  "Score is too high for HW" + assignmentNum + ": saw " + score + ", max is " + max;
		}
		assert individualScores.get(8-1) == 100;	// everybody got 100 on HW8
				
		// Find lowest score on a 100-point assigment.
		int min = Integer.MAX_VALUE;
		int assignmentNumWithMin = -1;
		for (int assignmentNum=1; assignmentNum<=8; assignmentNum++)
		{
			if (assignmentNum == 6)
				continue;							// don't drop HW6, which is 25 points
			int score = individualScores.get(assignmentNum-1);
			if (score < min)
			{
				min = score;
				assignmentNumWithMin = assignmentNum;
			}
		}
		assert assignmentNumWithMin >= 0;
		if (verbose)
			sop("\nDropping lowest (HW" + assignmentNumWithMin + ")");
		
		// Compute total points, excluding the dropped score.
		int totalPoints = 0;
		for (int assignmentNum=1; assignmentNum<=8; assignmentNum++)
			if (assignmentNum != assignmentNumWithMin)
				totalPoints += individualScores.get(assignmentNum-1);
		
		// Scale to 40 points.
		float maxPossiblePoints = 625;
		float frac = totalPoints / maxPossiblePoints;
		float outOf40 = frac * 40;
		assert outOf40 >= 0  &&  outOf40 <= 41;
		if (verbose)
		{
			float pct = 100f * frac;
			sop("Total raw points = " + totalPoints + " of 625 = " + pct + "%");
			sop("Scaled to 40 points (because homework is 40% of final grade) => " + outOf40);
		}
		return outOf40;
	}
	
	
	/*
	 * If MT2 score is better than MT1 score, then MT1 score is adjusted to the average.
	 * If final exam score is better than MT3 score, then MT3 score is adjusted to the average;
	 * this does not affect MT1 score.
	 * 
	 * The first white card adds 2 points to the final exam score. Subsequent white cards 
	 * add 1 point.
	 * 
	 * Returns the exams part of the score (out of 60 points).
	 */
	private static float computeExamScores(float mt1, float mt2, float finalExam, int nWhiteCards)
	{
		assert mt1 >= 0  &&  mt1 <= 100;
		assert mt2 >= 0  &&  mt2 <= 100;
		assert finalExam >= 0  &&  finalExam <= 100;
		assert nWhiteCards >= 0  &&  nWhiteCards <= 10;
		
		if (verbose)
		{
			sop("Raw exam scores: mt1=" + mt1 + " mt2=" + mt2 + " final=" + finalExam + "\n# white cards = " + nWhiteCards);
		}
		
		// Adjust MT1.
		if (mt2 > mt1)
		{
			mt1 = (mt1 + mt2) / 2;
		}

		// Adjust MT2.
		if (finalExam > mt2)
		{
			mt2 = (mt2 + finalExam) / 2;
		}
		
		// Adjust final exam.
		if (nWhiteCards > 0)
		{
			finalExam += (nWhiteCards+1);
			if (verbose)
				sop("White card adjustment to final exam => " + finalExam);
		}
		
		// Scale to 60 points (15 for each midterm, 30 for the final).
		float fracMt1 = mt1 / 100;
		float scaledMt1 = fracMt1 * 15;
		float fracMt2 = mt2 / 100;
		float scaledMt2 = fracMt2 * 15;
		float fracFinal = finalExam / 100;
		float scaledFinal = fracFinal * 30;
		if (verbose)
		{
			sop("mt2 > mt1, adjusting mt1 to " + mt1 +
					"   Scaled to 15 points (because mt1 is 15% of final grade) => " + scaledMt1);
			sop("final exam > mt1, adjusting mt2 to " + mt2 + 
					"   Scaled to 15 points (because mt2 is 15% of final grade) => " + scaledMt2);
			sop("final exam score = " + finalExam + 
					"   Scaled to 30 points (because final exam is 30% of final grade) => " + scaledFinal);
		}
		float outOf60 = scaledMt1 + scaledMt2 + scaledFinal;
		return outOf60;
	}
	
	
	private static String toLetterGrade(float score)
	{
		if (score >= 93)
			return "A";
		else if (score >= 90)
			return "A-";
		else if (score >= 87)
			return "B+";
		else if (score >= 83)
			return "B";
		else if (score >= 80)
			return "B-";
		else if (score >= 77)
			return "C+";
		else if (score >= 72)
			return "C";
		else if (score >= 70)
			return "C-";
		else if (score >= 67)
			return "D+";
		else if (score >= 62)
			return "D";
		else if (score >= 60)
			return "D-";
		else
			return "F";
	}
	
	
	static void sop(Object x)		{ System.out.println(x); }
	
	
	public static void main(String[] args)
	{
		sop("START");
		//                            1    2    3    4    5  5again  5xc    6    7   7again   8
		float hwScore = computeHWScore(100, 100, 100, 100, 100, 0,     10,  25,    0,   100,   100);
		sop("-----------------");
		float examScore = computeExamScores(95, 100, 66, 0);	// MT1, MT2, Final, white cards
		sop("-----------------");
		float totalScore = hwScore + examScore;
		sop("Total score = " + totalScore + "% = " + toLetterGrade(totalScore));
	}
}
