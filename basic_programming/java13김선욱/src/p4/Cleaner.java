package p4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class Cleaner {

	public static void main(String[] args) {
		FileReader fin = null;
		PrintWriter out = null;
		try
		{
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				fin = new FileReader(selectedFile);
			}
			else
				System.exit(0);
			
			out = new PrintWriter(new File("cleaned.txt"));
			
			boolean more = true;
			while (more) {
				int ic = fin.read();
				if (ic == -1)
					more = false;
				else {
					char c = (char) ic;
					if (0xAC00 <= c && c <= 0xD7A3)	// 한글 코드 범위
						out.write(c);
					else if (c == ' ' || c == '\t' || c == '\r' || c == '\n')
						out.write(c);
				}
			}
			
			out.flush();		// 출력 버퍼에 남아 있는 글자들을 화면으로 모두 내 보냄.
			out.close();
			fin.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
