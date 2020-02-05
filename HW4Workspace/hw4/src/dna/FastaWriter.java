package dna;

import java.io.*;

// Writes a fasta record to a print writer
public class FastaWriter 
{
	private PrintWriter thePrintWriter;
	
	//Constructor to initialize instance 
	public FastaWriter(PrintWriter thePrintWriter) 
	{
		this.thePrintWriter = thePrintWriter;
	}
	
	// Writes the rec as 2 separate lines: first the defline, then the sequence
	public void writeRecord(FastaRecord rec) throws IOException
	{
		thePrintWriter.println(rec.getDefline());
		thePrintWriter.println(rec.getSequence());
	}
}
