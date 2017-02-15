package edu.iit.xfz;

import java.util.ArrayList;

import edu.iit.xfz.HuffmanTree.TreeNode;

/**
 * Code and decode a sequence of String
 * 
 * @author Fangzhou Xiong
 * 
 */
public class HuffmanCode {

	/**
	 * Encode a String
	 * 
	 * @param s
	 *            - the input string
	 * @return
	 */
	public String encode(String s) {
		HuffmanTree<Character> ht = createHuffmanTree(s);
		String output = "";
		//get codetable:
		CS401ArrayImpl<CodeRow<Character>> codetable = ht.getCodeTable();
		for(int i = 0; i < codetable.size();i++){
			output +=codetable.get(i).value+" "+codetable.get(i).Encoding+"\n";
		}
		
		output +="**\n";
		String code = "";
		//get code
		for (int i = 0; i < s.length(); i++) {
			code += ht.encode((Character) s.charAt(i));
		}
		
		calculateSpaceSave(s,code);
		output +=code;
		return output;
	}// encode
	
	/**
	 * Decode a String
	 * 
	 * @param s
	 *            - the input string
	 * @return
	 */
	public String decode(CS401ArrayImpl<CodeRow<Character>> codeTable, String code) {
		HuffmanTree<Character> ht = new HuffmanTree<Character>(codeTable);
		return ht.decode(code);

	}// decode

	/**
	 * Create a huffman tree according to the input string
	 * 
	 * @param s
	 *            - clear text string
	 * @return
	 */
	public HuffmanTree<Character> createHuffmanTree(String s) {
		CS401PriorityQueue<HuffmanTree<Character>> pq = createHuffmanForest(s);
		HuffmanTree<Character> newTree = new HuffmanTree<Character>(null, 0);

		while (pq.getSize() > 1) {
			HuffmanTree<Character> left = pq.remove();
			HuffmanTree<Character> right = pq.remove();

			newTree = left.merge(right);
			pq.add(newTree);
		}

		//printTree(newTree);

		return newTree;
	}// create HuffmanTree

	/**
	 * Create a forest of huffmantrees according to the input string, the root
	 * node of each tree represent a character of the string
	 * 
	 * @param s
	 *            - input string
	 * @return - the forest of Huffmantrees
	 */
	private CS401PriorityQueue<HuffmanTree<Character>> createHuffmanForest(
			String s) {
		CS401PriorityQueue<HuffmanTree<Character>> pq = new CS401PriorityQueue<HuffmanTree<Character>>();
		ArrayList<Character> charlist = new ArrayList<Character>();

		for (int i = 0; i < s.length(); i++) {
			if (!charlist.contains((Character) s.charAt(i))) {
				int freq = 0;
				for (int j = 0; j < s.length(); j++) {
					if (s.charAt(j) == s.charAt(i)) {
						freq++;
					}
				}
				charlist.add(s.charAt(i));
				pq.add(new HuffmanTree<Character>(s.charAt(i), freq));
			}
		}
	    printPQ(pq);

		return pq;
	}// createHuffmanForest

	/**
	 * Caculate the saving ratio of the HuffmanCoding
	 * 
	 * @param clearText
	 * @param code
	 */
	public void calculateSpaceSave(String clearText, String code) {
		System.out.println("Bytes to represent cleartext strings: "
				+ clearText.length() + " bytes");
		System.out.println("Bytes to represent encoded strings: "
				+ Math.round(code.length() / 8) + " bytes");
		double ratio = Math.round(code.length() * 100 / clearText.length() / 8);
		System.out.println("Ratio of bytes saved: " + ratio / 100);

	}

	/**
	 * Print The PQ
	 * 
	 * @param pq
	 */
	public void printPQ(CS401PriorityQueue<HuffmanTree<Character>> pq) {
		System.out.println("This is the Priority Queue: " + pq.toString());
	}
	
	/**
	 * Print the Huffmantree
	 * @param ht
	 */
	public void printTree(HuffmanTree<Character> ht) {
		int td = ht.UpdateDepthAndCoordination();// depth of the whole tree
		int x = 0;// the x coordination of the node
		for (int i = 0; i <= td; i++) {// go through all the layer form depth 0
										// - td
			ArrayList<HuffmanTree.TreeNode<Character>> nodelist = ht
					.getNodeBydepth(i);

			String firtline = "";
			String secondline = "";

			for (int j = 0, p = 0; j < nodelist.size(); j++)// plot every node
															// according to his
															// coordination
															// layer by
															// layer
			{
				HuffmanTree.TreeNode<Character> ne = nodelist.get(j);
				if (ne != null) {
					int gap = (ne.getX() - p - 1) * 4;
					for (int k = 0; k < gap; k++) {
						firtline += " ";
						secondline += " ";
					}
					p = ne.getX();

					Character info = ne.getInfo();
					if (info == null) {
						info = '*';
					}
					firtline += "[" + info + "|" + ne.getKey() + "]";
					secondline += "    ";

				}
			}
			System.out.println(firtline);
			System.out.println(secondline);
		}
	}
}
