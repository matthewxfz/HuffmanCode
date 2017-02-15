package edu.iit.xfz;

import java.util.ArrayList;

/**
 * This binary tree used in the Huffman encoding
 * 
 * @author Fangzhou
 * 
 */
public class HuffmanTree<E> implements Comparable<HuffmanTree<E>> {

	private TreeNode<E> root; /* The absolute root of the tree. */

	private int depth;

	private CS401ArrayImpl<CodeRow<E>> codeTable = new CS401ArrayImpl<CodeRow<E>>();

	public HuffmanTree(E info, int key) {
		root = new TreeNode<E>(info, key);
	}

	public HuffmanTree(CS401ArrayImpl<CodeRow<E>> codeTable) {
		root = getHuffmanTreeByCodeTable(codeTable);
		this.codeTable = codeTable;
	}

	/**
	 * Get a huffman tree by code table, return the root of the tree
	 * 
	 * @param codeTable
	 * @return the root of the tree
	 */
	public TreeNode<E> getHuffmanTreeByCodeTable(CS401ArrayImpl<CodeRow<E>> codeTable) {
		TreeNode<E> root = new TreeNode<E>(null);
		TreeNode<E> cur = root;

		for (int i = 0; i < codeTable.size(); i++) {// iterate all rows in the
													// table
			CodeRow<E> nr = codeTable.get(i);
			String code = nr.Encoding;

			for (int k = 0; k < code.length(); k++) {// build tree
				char digit = code.charAt(k);
				if (digit == '0') {// go lfet if digit is 0
					if (cur.left == null) {// if there is no such node, bulid
											// one
						TreeNode<E> ntn = new TreeNode<E>(null);
						ntn.parent = cur;
						cur.left = ntn;
					}
					cur = cur.left;
				} else if (digit == '1') {// go right if digit is 1
					if (cur.right == null) {// if there is no such node, bulid
											// one
						TreeNode<E> ntn = new TreeNode<E>(null);
						ntn.parent = cur;
						cur.right = ntn;
					}
					cur = cur.right;
				} else {
					System.out.println("[Error]Codeing tabele problem, coding is not in format\n");
				}
			}
			cur.info = nr.value;
			cur = root;// go back to root for next build
		}
		return root;
	}

	/**
	 * Merge Two HuffmanTree to make a new one.
	 * 
	 * @param bt
	 *            - the other huffman tree
	 * @return
	 */
	public HuffmanTree<E> merge(HuffmanTree<E> bt) {
		TreeNode<E> newRoot = new TreeNode<E>(null, root.key + bt.root.key);

		if (this.compareTo(bt) == 1) {
			newRoot.right = this.root;
			newRoot.left = bt.root;
			this.root.parent = newRoot;
			bt.root.parent = newRoot;
			newRoot.parent = null;
		} else {
			newRoot.left = this.root;
			newRoot.right = bt.root;
			this.root.parent = newRoot;
			bt.root.parent = newRoot;
			newRoot.parent = null;
		}

		newRoot.right.code = "1";
		newRoot.left.code = "0";

		root = newRoot;

		return this;
	}

	/**
	 * Return the code for any character according to the exist code table
	 * 
	 * @param input
	 *            - the clear text character
	 * @return - the encode code
	 */
	public String encode(E input) {
		CS401ArrayImpl<CodeRow<E>> codeTable = getCodeTable();
		
		CodeRow<E> output = codeTable.contains(new CodeRow<E>(input));
		if (output == null) {
			System.out.println("[ERROR] Encode error");
			return null;
		} else {
			return output.Encoding;
		}
	}

	/**
	 * Return the text of the input code
	 * 
	 * @param input
	 *            - the code
	 * @return - the text
	 */
	public String decode(String code) {
		TreeNode<E> cur = root;
		String text = "";

		for (int i = 0; i < code.length(); i++) {
			char digit = code.charAt(i);
			if (digit == '0') {// go lfet if digit is 0
				cur = cur.left;
			} else if (digit == '1') {// go right if digit is 1
				cur = cur.right;
			} else {
				System.out.println("\n\n[Error]Codeing tabele problem, coding is not in format");
			}
			
			if(cur == null){
				System.out.println("\n\n[Error]The coding table is not complete\n");
				break;
			}
			
			if(cur.info != null){// if reach a node with info, go back to root
				text += cur.info;
				cur = root;
			}
		}
		
		return text;
	}// decode

	/**
	 * Create a code table according to this huffman tree
	 * 
	 * @return
	 */
	public CS401ArrayImpl<CodeRow<E>> getCodeTable() {
		if (codeTable.is_empty()) {
			fillCodeTable(root.right, codeTable);
			fillCodeTable(root.left, codeTable);

			return codeTable;
		} else {
			return codeTable;
		}

	}

	/**
	 * Fill the data from the huffman tree to the code table
	 * 
	 * @param tn
	 *            - the root of the huffman tree
	 * @param codeTable
	 *            - the output table
	 */
	private void fillCodeTable(TreeNode<E> tn, CS401ArrayImpl<CodeRow<E>> codeTable) {
		if (tn.left == null && tn.right == null) {
			if (tn.parent != root)
				tn.code = tn.parent.code + tn.code;

			CodeRow<E> codeRow = new CodeRow<E>();
			codeRow.Encoding = tn.code;
			codeRow.Frequency = tn.key;
			codeRow.value = tn.info;
			codeTable.add(codeRow);
			return;
		} else {
			if (tn.parent != root) {
				tn.code = tn.parent.code + tn.code;
			}

			fillCodeTable(tn.left, codeTable);
			fillCodeTable(tn.right, codeTable);
		}
	}

	/*
	 * ------------------------------------------------------------------- Each
	 * node in the tree is an object of this type.
	 */
	protected static class TreeNode<E> implements Comparable<TreeNode<E>> {
		private TreeNode<E> left, right, parent;
		private E info;
		private int key;
		private String code;
		private int x;// x coordination of the node, for plot

		public TreeNode(E info, int key) {
			left = right = parent = null;
			this.info = info;
			this.key = key;
			// this.code = code;
		}

		public TreeNode(E info) {
			left = right = parent = null;
			this.info = info;
			// this.code = code;
		}

		public int getX() {
			return x;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public void setInfo(E info) {
			this.info = info;
		}

		public TreeNode<E> getLeft() {
			return left;
		}

		public void setLeft(TreeNode<E> left) {
			this.left = left;
		}

		public TreeNode<E> getRight() {
			return right;
		}

		public void setRight(TreeNode<E> right) {
			this.right = right;
		}

		public TreeNode<E> getParent() {
			return parent;
		}

		public void setParent(TreeNode<E> parent) {
			this.parent = parent;
		}

		public E getInfo() {
			return info;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public TreeNode<E> get_left() {
			return left;
		}

		public TreeNode<E> get_right() {
			return right;
		}

		@Override
		public int compareTo(TreeNode<E> ne) {
			if (this.key > ne.key) {
				return 1;
			} else if (this.key < ne.key) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int compareTo(HuffmanTree<E> nt) {
		return this.root.compareTo(nt.root);
	}

	public String toString() {
		return "<" + root.info.toString() + "|" + root.key + ">";
	}

	/**
	 * Uesding in plotting tree, update the depth of the tree and update the x
	 * coordinatio of each node
	 * 
	 * @return
	 */
	public int UpdateDepthAndCoordination() {
		depth = travelForDepth(root, 0);
		updateXCoordination(root, depth);
		return depth;
	}// UpdateDepthAndCoordination

	/**
	 * Get all the node of a layer of tree
	 * 
	 * @param depth
	 *            - the layer of the tree
	 * @return
	 */
	public ArrayList<TreeNode<E>> getNodeBydepth(int depth) {
		ArrayList<TreeNode<E>> nodeList = new ArrayList<HuffmanTree.TreeNode<E>>();
		travelForNodes(root, depth, nodeList);

		return nodeList;
	}// getNodeBydepth

	private void travelForNodes(TreeNode<E> ne, int depth, ArrayList<TreeNode<E>> nodeList) {
		if (ne == null) {
			return;
		} else if (depth == 0) {
			nodeList.add(ne);
			return;
		} else {
			depth--;
			travelForNodes(ne.left, depth, nodeList);
			travelForNodes(ne.right, depth, nodeList);
		}
	}// travelForNodes

	private int travelForDepth(TreeNode<E> ne, int depth) {
		if (ne == null) {
			return depth;
		} else {
			depth++;

			int left = travelForDepth(ne.left, depth);
			int right = travelForDepth(ne.right, depth);
			if (right > left) {
				return right;
			} else {
				return left;
			}
		}
	}// travelForDepth

	private void updateXCoordination(TreeNode<E> ne, int d) {
		if (ne == null) {
			return;
		} else if (ne == root) {
			root.x = (int) (2 * depth);
			d++;
			updateXCoordination(ne.left, d);
			updateXCoordination(ne.right, d);
		} else {
			if (ne.parent.left == ne) {
				int x = (int) (ne.parent.x - Math.pow(2, (depth - d)));
				ne.x = getXCoordination(ne, x);
			} else if (ne.parent.right == ne) {
				int x = (int) (ne.parent.x + Math.pow(2, (depth - d)));
				ne.x = getXCoordination(ne, x);
			}

			updateXCoordination(ne.left, d++);
			updateXCoordination(ne.right, d++);
		}
	}// travelForDepth

	private int getXCoordination(TreeNode<E> ne, int present) {
		if (ne == null) {
			return present;
		} else {
			return getXCoordination(ne.left, present++);
		}
	}// getXCoordination

	public TreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}

}
