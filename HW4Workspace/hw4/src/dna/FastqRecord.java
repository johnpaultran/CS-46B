package dna;

//FastqRecord contains the defline, sequence, and quality string
public class FastqRecord implements DNARecord 
{
	private String defline;
	private String sequence;
	private String quality; 

	
	 //Constructor for the FastqRecord class
	
	 //Checks first character of variable defline to determine if it starts with '@'
	
	 //Throws an exception if it doesn't
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException 
	{
		if (defline.charAt(0) != '@') 
		{
			throw new RecordFormatException ("Bad 1st char in defline in fastq record: saw X, expected @");
		}
		else 
		{
			this.defline = defline; 
		}
		
		this.sequence = sequence;
		this.quality = quality; 
	}
	
	//The get methods getDefline() and getSequence() that satisfy the DNARecord interface
	public String getDefline() 
	{
		return defline; 
	}
	
	public String getSequence() 
	{
		return sequence; 
	}
	
	 //The equals method for all three instance variables that checks for deep equality 
	public boolean equals(Object x) 
	{
		FastqRecord something = (FastqRecord) x; 
		
		if (!defline.equals(something.defline)) 
		{
			return false; 
		}
		
		if(!sequence.equals(something.sequence)) 
		{
			return false; 
		}
		
		if(!quality.equals(something.quality)) 
		{
			return false; 
		}
		
		return true; 
	}	

	 //Determines the quality based on whether it contains a $ and # signs
	public boolean qualityIsLow()
	{
		boolean qualityCheck = false; 
		if(quality.contains("$") && quality.contains("#")) 
		{
			qualityCheck = true; 
		}
		return qualityCheck; 
	} 
	
	 // Returns all the sums of the hashcodes of defline, sequence, and quality
	public int hashCode()
	{
		int sum = defline.hashCode() + sequence.hashCode() + quality.hashCode();
		return sum; 
	}
}
