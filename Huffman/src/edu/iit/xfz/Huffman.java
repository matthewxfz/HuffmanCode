package edu.iit.xfz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Huffman {
	protected static CS401ArrayImpl<CodeRow<Character>> codetable = new CS401ArrayImpl<CodeRow<Character>>();
	protected static String coding = "";

	protected static File file = null;

	protected static final String FILE_ERROR = "[ERROR]File reading failed\n "
			+ "The default path /opt/students-64000.dat\n\n" + "Please input your absolute path to the file:\n ";

	public static void main(String[] args) {
		if(args[0].equals("encode")){
			if(args[1] != null && args[2] != null){
				HuffmanCode hc = new HuffmanCode();
				String info = hc.encode(args[1]);
				write(info, args[2]);
				System.out.println("\n\nEncoding Complete!\n");
			}else{
				help();
			}
		}else if(args[0].equals("decode")){
			if(args[1] != null){
				HuffmanCode hc = new HuffmanCode();
				readFile(getFileIterator(args[1]));
				
				System.out.println(hc.decode(codetable, coding)+"\n$");
			}else{
				help();
			}
		}else{
			help();
		}

	}// main

	public static void help() {
		String help = "Usage:\n\n";
		help += "java -jar Huffman.jar encode \"[encoding text]\" <path/output file name> \n"
				+ "java -jar Huffman.jar decode <path/output file name>\n\n" + "format of the output file:\n"
				+ "$ cat output.txt\n" + "    010\n" + "  e 1010\n" + "  g 011\n" + "  i 100\n" + "  m 1011\n"
				+ "  n 00\n" + "  s 11\n" + "  **\n" + "  1011101000010111000001111010111000110011\n";
		System.out.println(help);
	}

	/**
	 * Read the file and store the code table and sequence in the globale
	 * variable
	 * 
	 * @param -
	 *            the file iterator
	 */
	public static void readFile(FileIterator iterator) {
		CodeRow<Character> crow = new CodeRow<Character>();

		// read coding table
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (line.equals("**")) {
				break;
			} else {
				crow = new CodeRow<Character>();
				crow.value = line.charAt(0);
				crow.Encoding = line.substring(2, (line.length()));
				codetable.add(crow);
			}
		}

		// read code sequence
		while (iterator.hasNext()) {
			coding = iterator.next();
		}

	}// readFile

	/**
	 * Return the file iterator
	 * 
	 * @return the FileIterator object to the file
	 * @param the
	 *            file contains Students records
	 */
	private static FileIterator getFileIterator(String path) {
		FileIterator iterator = null;
		while (true) {
			file = new File(path);
			if (file.isFile()) {
				try {
					iterator = new FileIterator(file);
//					System.out.println("Read file successfull!");
					break;
				} catch (FileNotFoundException e) {
					System.out.println(FILE_ERROR);
					Scanner sc = new Scanner(System.in);
					path = sc.nextLine();
				}
			} else {
				System.out.println(FILE_ERROR);
				Scanner sc = new Scanner(System.in);
				path = sc.nextLine();
			}
		}
		return iterator;
	}// getFileIterator

	/**
	 * Create file for the encoding informaiton
	 * 
	 * @param info
	 *            -encoding information
	 * @param destPath
	 *            -the file name
	 */
	public static void write(String info, String destPath) {
		try {
			File f = new File(destPath);
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			writer.write(info);
			writer.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
