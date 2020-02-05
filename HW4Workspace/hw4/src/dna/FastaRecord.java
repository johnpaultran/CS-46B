package dna;


public class FastaRecord implements DNARecord 
{	
	private String defline; 
	private String sequence; 
	
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		if (defline.charAt(0) != '>') 
		{
			throw new RecordFormatException ("Bad 1st char in defline in fasta record: saw X, expected >");
		}
		else 
		{
			this.defline = defline; 
		}
		
		this.sequence = sequence; 
	}
	
	
	// Initialize defline and sequence from the input record. The defline should be the
	// defline of the fastq record, but with a '>' in the first position rather than a '@'
	public FastaRecord(FastqRecord fastqRec)
	{
		this.defline = fastqRec.getDefline();
		if(fastqRec.getDefline().charAt(0) == '@')
		{
		    this.defline = ">" + fastqRec.getDefline().substring(1);
		}
		this.sequence = fastqRec.getSequence();
	}
	
	// The 2 methods that satisfy the interface.
	public String getDefline() 
	{
		return defline; 
	}
	
	public String getSequence() 
	{
		return sequence; 
	}
	
	// Provide an equals() method. 
	public boolean equals(Object x) {
		FastaRecord something = (FastaRecord) x; 
		
		if (!defline.equals(something.getDefline())) {
			return false; 
		}
		
		if(!sequence.equals(something.getSequence())) {
			return false; 
		}
		
		return true; 
	}

	// A hashCode() method that returns the sum of the hashcodes of defline and sequence.
	public int hashCode()
	{
		int sum = defline.hashCode() + sequence.hashCode();
		return sum; 
	}
}
