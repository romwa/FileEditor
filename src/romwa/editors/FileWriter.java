package romwa.editors;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;

/**
 * This class write (makes edits) to a file
 * @author romwa
 * @version Alpha
 */
public class FileWriter {

	/**
	 * Rewrites the file. Deleting everything in the file
	 * @param file File
	 * @param content The new content of the file
	 * @post Rewrites the File
	 */
	public void rewriteFile(File file, String content) {
		try {
			java.io.FileWriter writer = new java.io.FileWriter(file);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (NullPointerException e) {
			System.out.println("Content is empty");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + file.getName() + " was not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds to the file, after the original content
	 * @param file File
	 * @param content The new content
	 * @post Adds content to the file
	 */
	public void concatToFile(File file, String content) {
		FileReader reader = new FileReader();
		String sFile = reader.readFile(file);
		try {
			java.io.FileWriter writer = new java.io.FileWriter(file);
			writer.write(sFile + content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot access file");
		}
	}

	/**
	 * Deletes a line from the file
	 * @param file File
	 * @param content The content or line to be deleted
	 * @pre The content cannot be more than a line in the file
	 * @post The line will be deleted from the file
	 */
	public void deleteLine(File file, String content) {
		FileReader reader = new FileReader();
		String sFile = reader.readFile(file);
		String line = reader.getLine(file, content);
		String[] lines = sFile.split("\n");
		System.out.println(lines.length);
		int count = 0;
		for(int j = 0; j < lines.length; j++) {
			if(lines[j].equals(line)) {
				count++;
			}
		}
//		System.out.println("count: " + count);
		String[] updatedLines = new String[lines.length - count];
		for(int i = 0; i < updatedLines.length; i++) {
			int skip = 0;
			if(!lines[i].equals(line)) {
				updatedLines[i] = lines[i + skip];
			} else {
				skip++;
				updatedLines[i] = lines[i + skip];
			}
		}
		rewriteFile(file, updatedLines[0]);
		for(int k = 1; k < updatedLines.length; k++) {
			concatToFile(file, updatedLines[k]);
		}
	}
	
	/**
	 * Deletes a specific word or phrase from a file
	 * @param file File
	 * @param lineNum The # of the line for which to delete the word from
	 * @param word The word that will be deleted
	 * @post The word of phrase will be deleted from the file
	 */
	public void deletePhraseOnLine(File file, int lineNum, String phrase) {
		FileReader reader = new FileReader();
		String[] lines = reader.getLines(file);
		String newLine = lines[lineNum-1];
		if(newLine.contains(phrase)) {
			int sNum = newLine.indexOf(phrase);
			int eNum = sNum+phrase.length();
			newLine = newLine.substring(0, sNum-1) + newLine.substring(eNum, newLine.length());
			lines[lineNum-1] = newLine;
			rewriteFile(file, lines[0]);
			for(int j = 1; j < lines.length; j++) {
				concatToFile(file, lines[j]);
			}
		} else {
			System.out.println("The line does not contain the phrase");
		}
	}
	
	/**
	 * Replaces a phrase on a specific line
	 * @param file File
	 * @param lineNum THe line #
	 * @param phrase The phrase to be replaced
	 * @param newPhrase The new phrase
	 * @post The phrase will be replaced
	 */
	public void replacePhrase(File file, int lineNum, String phrase, String newPhrase) {
		FileReader reader = new FileReader();
		String[] lines = reader.getLines(file);
		String newLine = lines[lineNum-1];
		if(newLine.contains(phrase)) {
			int sNum = newLine.indexOf(phrase);
			int eNum = sNum+phrase.length();
			newLine = newLine.substring(0, sNum) + "" + newPhrase + newLine.substring(eNum, newLine.length());
			lines[lineNum-1] = newLine;
			rewriteFile(file, lines[0]);
			for(int j = 1; j < lines.length; j++) {
				concatToFile(file, lines[j]);
			}
		} else {
			System.out.println("The line does not contain the phrase");
		}
	}
	
	
	
	
	
	
	
}
