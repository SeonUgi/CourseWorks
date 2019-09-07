package p3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class concatenates contents of several files into one file.
 */
public class CatFiles {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("»ç¿ë¹ý : CatFiles sourcefile1 sourcefile2 . . . targetfile");
			return;
		}
		
		try
		{
			FileWriter out = new FileWriter(args[args.length - 1]);
			for (int i =0; i < args.length - 1; i++) {
				FileReader in = new FileReader(args[i]);
				boolean more = true;
				while (more)
				{
					int ic = in.read();
					if(ic == -1){
						more = false;
					}	
					else
						out.write(ic);				
				}
				in.close();
			}
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
