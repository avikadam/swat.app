package org.sits.probs.search;

/**
 * Binary Search Tree, is a node-based binary tree data structure which has the
 * following properties:
 * 
 * 1. The left subtree of a node contains only nodes with keys less than the
 * node’s key.
 * 
 * 2. The right subtree of a node contains only nodes with keys greater than the
 * node’s key.
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
	class Node {
		int key;
		Node left, right;

		public Node(int item) {
			key = item;
			left = right = null;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}

	// Root of BST
	Node root;

	// Constructor
	BinarySearchTree() {
		root = null;
	}

	// This method mainly calls insertRec()
	void insert(int key) {
		root = insertRec(root, key);
	}

	/* A recursive function to insert a new key in BST */
	Node insertRec(Node root, int key) {

		/* If the tree is empty, return a new node */
		if (root == null) {
			root = new Node(key);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (key < root.key)
			root.left = insertRec(root.left, key);
		else if (key > root.key)
			root.right = insertRec(root.right, key);

		/* return the (unchanged) node pointer */
		return root;
	}

	// This method mainly calls deleteRec()
	void deleteKey(int key) {
		root = deleteRec(root, key);
	}

	/* A recursive function to insert a new key in BST */
	Node deleteRec(Node root, int key) {
		/* Base Case: If the tree is empty */
		if (root == null)
			return root;

		/* Otherwise, recur down the tree */
		if (key < root.key)
			root.left = deleteRec(root.left, key);
		else if (key > root.key)
			root.right = deleteRec(root.right, key);

		// if key is same as root's key, then This is the node
		// to be deleted
		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// node with two children: Get the inorder successor (smallest
			// in the right subtree)
			root.key = minValue(root.right);

			// Delete the inorder successor
			root.right = deleteRec(root.right, root.key);
		}

		return root;
	}

	int minValue(Node root) {
		int minv = root.key;
		while (root.left != null) {
			minv = root.left.key;
			root = root.left;
		}
		return minv;
	}

	/**
	 * LEFT-ROOT-RIGHT
	 */
	public void inorder() {
		inorderRec(root);
	}

	/**
	 * ROOT-LEFT-RIGHT
	 */
	public void preorder() {
		preorderRec(root);
	}

	/**
	 * LEFT-RIGHT-ROOT
	 */
	public void postOrderTraversal() {
		doPostOrder(this.root);
	}

	/**
	 * A utility function to do in-order traversal of BST
	 * 
	 * @param root
	 */
	private void inorderRec(Node root) {
		if (root == null)
			return;
		inorderRec(root.getLeft());
		System.out.println(root.getKey());
		inorderRec(root.getRight());
	}

	private void preorderRec(Node root) {
		if (root == null)
			return;
		System.out.println(root.getKey());
		preorderRec(root.getLeft());
		preorderRec(root.getRight());
	}

	private void doPostOrder(Node root) {
		if (root == null)
			return;
		doPostOrder(root.getLeft());
		doPostOrder(root.getRight());
		System.out.print(root.getKey());
	}

	// Driver Program to test above functions
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		/*
		 * Let us create following BST 
		 *     50 
		 *    /  \ 
		 *   30   70 
		 *  / \   / \ 
		 * 20 40 60 80
		 */
		tree.insert(50);
		tree.insert(30);
		tree.insert(20);
		tree.insert(40);
		tree.insert(70);
		tree.insert(60);
		tree.insert(80);

		// print in-order traversal of the BST
		tree.inorder();
	}
}
