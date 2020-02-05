package dna;
import java.io.*;

// Reads lines from a BufferedReader and builds a FastqRecord.
public class FastqReader 
{
	private BufferedReader br; 
	
	// Constructor that initializes instance variable
	public FastqReader(BufferedReader br) 
	{
		this.br = br; 
	}
	
	// Returns next record in the file, or null if EOF (end-of-file)
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		String defline = br.readLine();
		
		// Read the defline from the BufferedReader
		
		//Return null if you read null, indicating end of file
		if (defline == null) 
		{
			return null;
		}
		
		// Reads the next 3 lines from the buffered reader 
		
		//Construct and return a FastqRecord
		else {
			
			String sequence = br.readLine(); 
			String plus = br.readLine();
			String quality = br.readLine();
			FastqRecord newFast = new FastqRecord(defline, sequence, quality);
			
			return newFast; 
		}

	}
}
