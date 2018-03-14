package romwa.editors;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.util.Scanner;

/**
 * This class reads a file
 * @author romwa
 * @version Alpha
 */
public class FileReader {
	
	/**
	 * Reads the file
	 * @param file File
	 * @return The file as a String
	 */
	public String readFile(File file) {
		String sFile = "";
		try {
			java.io.FileReader reader = new java.io.FileReader(file);
			Scanner in = new Scanner(reader);
			if(reader != null) {
				while(in.hasNext()) {
					sFile += in.nextLine() + "\n";
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
//			System.out.println("The file " + file.getName() + " was not found");
			e.printStackTrace();
			return "The file " + file.getName() + " was not found";
		}
//		System.out.println(sFile);
		return sFile;
	}

	/**
	 * Gets the line with the phrase
	 * @pre file != null, phrase != null
	 * @param file File
	 * @param phrase Phrase that would get lines # of
	 * @return The lines that contains the phrase
	 */
	public String getLine(File file, String phrase) {
		String sFile = readFile(file);
		String[] lines = getLines(file);
		for(int i = 0; i < lines.length;) {
			if(lines[i].contains(phrase)) {
				return lines[i];
			}
			i++;
		}
		return "Phrase was not found";
	}
	
	/**
	 * Gets the file in array of lines
	 * @param file File
	 * @return The array of lines of the file
	 */
	public String[] getLines(File file) {
		String sFile = readFile(file);
		return sFile.split("\n");
	}
	
	/**
	 * Gets the line number in a file
	 * @pre file != null, phrase != null
	 * @param file File
	 * @param phrase Phrase or line that would get lines # of
	 * @return The line's #. Returns -1 when line is not found
	 */
	public int getLineNumber(File file, String phrase) {
		FileReader reader = new FileReader();
		String sFile = reader.readFile(file);
		String focusedLine = reader.getLine(file, phrase);
		String[] lines = reader.getLines(file);
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].contains(focusedLine))
				return i+1;
		}
		
		return -1;
	}
}
