package edu.iit.xfz;

/**
 * CodeRow used in code Table
 * 
 * @author FangZhou Xiong
 * 
 * @param <E>
 */
public class CodeRow<E> implements Comparable<CodeRow<E>> {
	protected E value;
	protected int Frequency;
	protected String Encoding;

	public CodeRow() {
	}

	public CodeRow(E value) {
		this.value = value;
	}
	
	public CodeRow(E value, String code) {
		this.value = value;
		this.Encoding  = code;
	}

	@Override
	public int compareTo(CodeRow<E> cr) {
		return ((Comparable<E>) this.value).compareTo(cr.value);
	}
	
	@Override
	public String toString() {
		return "["+value+", "+Encoding+", "+Frequency+"]";
	}
}