package com.sits.java_sample;

/**
 * Binary Search Tree, is a node-based binary tree data structure which has the
 * following properties:
 * 
 * 1. The left subtree of a node contains only nodes with keys less than the
 * node�s key.
 * 
 * 2. The right subtree of a node contains only nodes with keys greater than the
 * node�s key.
 * 
 * 3. The left and right subtree each must also be a binary search tree. There
 * must be no duplicate nodes.
 *
 */
/**
 * @author admin
 *
 */
public class BinarySearchTree {
	/* Class containing left and right child of current node and key value */
	class Tree {
		int x;
		Tree l;
		Tree r;

		public Tree(int item) {
			x = item;
			l = r = null;
		}

	}

	// Root of BST
	Tree root;

	// Constructor
	BinarySearchTree() {
		root = null;
	}

	// This method mainly calls insertRec()
	void insert(int key) {
		root = insertRec(root, key);
	}

	/* A recursive function to insert a new key in BST */
	Tree insertRec(Tree root, int key) {

		/* If the tree is empty, return a new node */
		if (root == null) {
			root = new Tree(key);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (key < root.x)
			root.l = insertRec(root.l, key);
		else if (key > root.x)
			root.r = insertRec(root.r, key);

		/* return the (unchanged) node pointer */
		return root;
	}

	public static Integer findHeight(BinarySearchTree tree) {
		return getNodeHeight(tree.root);
	}

	private static Integer getNodeHeight(Tree t) {
		if (t == null) {
			return -1;
		}
		return Math.max(getNodeHeight(t.l), getNodeHeight(t.r)) + 1;
	}

	// Driver Program to test above functions
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(5);
		tree.insert(3);
		tree.insert(10);
		tree.insert(20);
		tree.insert(21);
		tree.insert(1);
		// tree.insert(80);
		System.out.println(findHeight(tree));
	}
}
