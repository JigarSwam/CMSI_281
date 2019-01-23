package tree.bst;

import tree.binary.BinaryTreeNode;

public class BST {
	
	BSTNode root;
	
	BST () {}
	
	
	/*
	 * Add a new int i into the existing BST
	 */
	public void add (int i) {
		// Case: empty tree
		if (root == null) {
			root = new BSTNode(i);
			return;
		}
		
		// Case: nodes in tree, find where new one
		// should life
		BSTNode current = root;
		while (current != null) {
			// Compare inserted value to that of current
			// Case: i < current, so belongs left
			if (i < current.data) {
				// Case: OK to insert left
				if (current.left == null) {
					current.left = new BSTNode(i);
					return;
				}
				// Case: need to keep searching
				current = current.left;
				// Case i is >= current, so belongs right
			} else { 
				if (current.right == null) {
					current.right = new BSTNode(i);
					return;
				}
				// Case: need to keep searching
				current = current.right;
			}
		}
	}
	
	public boolean contains (int i) {
		BSTNode current = root;
		while (current != null) {
			// Found it!
			if (current.data == i) { return true; }
			
			// Otherwise, look to appropriate subtree
			current = (i < current.data) ? current.left : current.right; 
		}
		return false;		
	}
	
	public void inOrderPrint () {
		inOrderPrint(root);
	}
	
	public static void inOrderPrint (BSTNode n) {
		// Base Case: done recursing, stop
		if (n == null) {return;}
		// Recursive case:
		inOrderPrint(n.left);
		// Visit node
		System.out.print(n.data + " ");
		// Recursive Case:
		inOrderPrint(n.right);
	}
	
	
	private class BSTNode {
		int data;
		BSTNode left, right;
		
		BSTNode (int d) {
			data = d;
		}
	}
	
	public static void main (String[] args) {
		BST tree = new BST();
		tree.add(10);
		tree.add(2);
		tree.add(12);
		tree.add(4);
		tree.add(3);
		tree.add(11);
		tree.inOrderPrint();
		System.out.println();
		System.out.println(tree.contains(10));
		System.out.println(tree.contains(3));
		System.out.println(tree.contains(13));
	}
}
