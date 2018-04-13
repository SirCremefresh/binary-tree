package ch.sircremefresh;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree {

	private Node rootNode = null;

//	public void visualize() {
//		int depth = getDepth();
//		if (depth == 0) return;
//		int width = (int) Math.pow(2, depth - 1);
//		for (int i = 0; depth - 1 >= i; i++) {
//			List<Integer> list = new LinkedList<>();
//			getRow(rootNode, i, 0, list);
//
//			for (int y = 0; width / 2 - (int) Math.pow(2, i -1) > y; y++) {
//				System.out.print("   ");
//			}
//			for (Integer val: list) {
//				if (val == null) {
//					System.out.print("|.|");
//				} else {
//					System.out.print("|" + val + "|");
//				}
//			}
//			System.out.print("\n");
//		}
//	}

	public List<Integer> getRow(int depth) {
		List<Integer> row = new LinkedList<>();
		getRow(rootNode, depth, 0, row);
		return row;
	}

	private void getRow(Node node, int depth, int currentDepth, List<Integer> list) {
		if (currentDepth > depth) return;
		if (node == null) {
			if (depth == currentDepth) {
				list.add(null);
			} else {
				int namChildren = (int) Math.pow(2,depth - currentDepth);
				for (int i = 0; namChildren > i; i++) {
					list.add(null);
				}
			}
			return;
		}
		getRow(node.getLeftChild(), depth, currentDepth + 1, list);
		if (depth == currentDepth) {
			list.add(node.value);
		}
		getRow(node.getRightChild(), depth, currentDepth + 1, list);
	}

	public int getDepth() {
		return getDepth(rootNode);
	}

	private int getDepth(Node node) {
		if (node == null) {
			return 0;
		}
		int leftDepth = getDepth(node.getLeftChild());
		int rightDepth = getDepth(node.getRightChild());

		return Math.max(leftDepth, rightDepth) + 1;
	}

	public void add(int num) {
		Node newNode = new Node(num);
		if (rootNode == null) {
			rootNode = newNode;
			return;
		}

		Node node = rootNode;
		while (true) {
			if (num < node.getValue()) {
				if (node.getLeftChild() != null) {
					node = node.getLeftChild();
				} else {
					node.setLeftChild(newNode);
					return;
				}
			} else {
				if (node.getRightChild() != null) {
					node = node.getRightChild();
				} else {
					node.setRightChild(newNode);
					return;
				}
			}
		}
	}

	public boolean search(int num) {
		if (rootNode == null)
			return false;
		return search(num, rootNode);
	}

	private boolean search(int num, Node node) {
		if (node.getValue() == num)
			return true;

		if (num < node.getValue()) {
			if (node.getLeftChild() == null)
				return false;
			return search(num, node.getLeftChild());
		} else {
			if (node.getRightChild() == null)
				return false;
			return search(num, node.getRightChild());
		}
	}

	private class Node {
		private Node leftChild = null;
		private Node rightChild = null;
		private int value;

		public Node() {
		}

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
}
