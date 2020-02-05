package dotlab;

import java.io.*;


public class DotReader 
{
	private BufferedReader br;
	
	public DotReader(BufferedReader br)
	{
		this.br = br;
	}
	
	public Dot readDot() throws IOException, DotException
	{
		String line = br.readLine();
		String[] array;
		if(line==null)
		{	
			return null;
		}
		else
		{
			array = line.split(",");
		}
		if (array.length == 4)
		{
			int x = Integer.parseInt(array[1]);
			int y = Integer.parseInt(array[2]);
			int r = Integer.parseInt(array[3]);
			Dot d = new Dot(array[0], x,y,r);
			return d;
		}
		else 
		{
			String error = "Incorrect parsed line size " + array.length;
			DotException de = new DotException(error);
			throw de;
			}
	}
}
