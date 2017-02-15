package edu.iit.xfz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class TestClass {
	
	@Test
	public void testEncode(){
		HuffmanCode hc = new HuffmanCode();
		System.out.println("Encode hello world:\n\n"+hc.encode("hello world"));
	}
	
	@Test
	public void testDecode(){
		
	}
	
	@Test
	public void testIO(){
		String destPath = "/Users/matthewxfz/Workspaces/401/Homework-2-DEC_FangzhouXiong/test.txt";
		String info = "hello";
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
	
	public CS401ArrayImpl<CodeRow<Character>> getCodeTable(){
		CS401ArrayImpl<CodeRow<Character>> codetable = new CS401ArrayImpl<CodeRow<Character>>();
		
		codetable.add(new CodeRow<Character>('o',"110"));
		codetable.add(new CodeRow<Character>('r',"1110"));
		codetable.add(new CodeRow<Character>('d',"1111"));
		codetable.add(new CodeRow<Character>('h',"000"));
		codetable.add(new CodeRow<Character>('e',"001"));
		codetable.add(new CodeRow<Character>(' ',"010"));
		codetable.add(new CodeRow<Character>('w',"011"));
		codetable.add(new CodeRow<Character>('l',"10"));
		
		return codetable;
	}
}
