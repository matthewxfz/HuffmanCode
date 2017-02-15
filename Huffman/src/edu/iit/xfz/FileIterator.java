package edu.iit.xfz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Scanner;

/**
 * This class initialize a scanner and return the student line by line from the
 * students file
 * 
 * @author Fangzhou Xiong
 * 
 */
public class FileIterator {

	private Scanner sc;

	public FileIterator(File file) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		sc = new Scanner(fis);
	}// constructor

	/**
	 * Return the student of the next line info form the studnet file.
	 * 
	 * @return Student object base on the next line information
	 */
	public String next() {
		try {
			String line = sc.nextLine();
			return line;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean hasNext() {
		return sc.hasNext();
	}
	
}
