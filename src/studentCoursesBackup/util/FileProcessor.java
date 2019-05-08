package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to process the file given and read all the data from it
 * @author pragyavishalakshi
 *
 */
public class FileProcessor {
	private BufferedReader br = null;

	/**
	 * get the BufferedReader object of class
	 * @return BufferedReader object
	 */
	public BufferedReader getBr() {
		return br;
	}

	/**
	 * set the BufferedReader object of class to given value
	 * @param BufferedReader object to be set
	 */
	public void setBr(BufferedReader br) {
		this.br = br;
	}

	/**
	 * parameterized constructor.
	 * Reads the file provided in BufferedReader
	 * @param absolute path of file
	 */
	public FileProcessor(String inputIn) {
		super();
		try {
			br = new BufferedReader(new FileReader(inputIn));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		}
	}

	/**
	 * returns the list of all the lines read by buffered reader from file
	 * @return list of all the lines read from file
	 */
	public ArrayList<String> readLine() {
		ArrayList<String> textLines = new ArrayList<>();
		try {
			String line = br.readLine();
			while(line != null) {
				textLines.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return textLines;
	}

}
