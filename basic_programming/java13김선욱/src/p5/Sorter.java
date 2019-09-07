package p5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Sorter {

	public static void main(String[] args) {
		Scanner in = null;
		PrintWriter out = null;
		try
		{
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				in = new Scanner(selectedFile);
			}
			else
				System.exit(0);

			out = new PrintWriter(new File("sorted.txt"));
			List<String> list = new ArrayList<String>();
			while (in.hasNext()) {


				String a = in.next();

				list.add(a);

			}
			Collections.sort(list);
			
			for(String a : list)
			{
				
				    out.write(a);
					out.write('\n');
					out.write('\n');
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
		in.close();
	}

}
