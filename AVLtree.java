
class AVLtree {

	Node1 root;
	int height;

	AVLtree() {
	}

	void insert(Node1 k) {
		if (root == null) {
			root = k;
			root.height += 1;
		} else if (root != null) {
			if (find(k.key) == null) {
				Node1 temp = root, child = null, parent = null;
				// insertion part
				while (true) {
					if (k.key > temp.key && temp.rightChild != null)
						temp = temp.rightChild;
					else if (k.key > temp.key && temp.rightChild == null) {
						k.parent = temp;
						k.height += 1;
						temp.rightChild = k;
						child = temp.rightChild;
						break;
					} else if (k.key < temp.key && temp.leftChild != null)
						temp = temp.leftChild;
					else if (k.key < temp.key && temp.leftChild == null) {
						k.parent = temp;
						k.height += 1;
						temp.leftChild = k;
						child = temp.leftChild;
						break;
					}
				}
				balanceHandler(child);
			} else
				System.out.println(k.key + " exist try another key");
		}
	}

	private int max(Node1 leftChild, Node1 rightChild) {
		return height(leftChild) > height(rightChild) ? height(leftChild) : height(rightChild);
	}

	private int height(Node1 Node) {
		if (Node == null)
			return 0;
		else
			return (Node.height);
	}

	private void balanceHandler(Node1 parent) {
		// updating height part while balancing
		parent.height = max(parent.leftChild, parent.rightChild) + 1;
		checkbalance(parent.leftChild, parent.rightChild);
		while (true) {
			if (parent == root)
				break;
			parent = parent.parent;
			parent.height = max(parent.leftChild, parent.rightChild) + 1;
			checkbalance(parent.leftChild, parent.rightChild);
		}
	}

	private void checkbalance(Node1 leftChild, Node1 rightChild) {
		if (height(leftChild) - height(rightChild) == 2) {
			if (height(leftChild.leftChild) - height(leftChild.rightChild) == 1)
				SRR(leftChild); // Single Right Rotation
			else if (height(leftChild.leftChild) - height(leftChild.rightChild) == -1)
				LRR(leftChild); // leftRight Rotation
		} else if (height(leftChild) - height(rightChild) == -2) {
			if (height(rightChild.leftChild) - height(rightChild.rightChild) == 1)
				RLR(rightChild); // Right left Rotation
			else if (height(rightChild.leftChild) - height(rightChild.rightChild) == -1)
				SLR(rightChild); // Single Left Rotation
		}
	}

	// Right Left Rotation
	private void RLR(Node1 theNode) {
		Node1 temp = theNode.parent, temp2 = theNode.leftChild;
		temp.height -= 2;

		theNode.leftChild = temp2.rightChild;
		if (temp2.rightChild != null)
			temp2.rightChild.parent = theNode;

		temp.rightChild = temp2.leftChild;
		if (temp2.leftChild != null)
			temp2.leftChild.parent = temp;

		temp2.leftChild = temp;
		temp2.rightChild = theNode;
		theNode.height -= 1;
		temp2.height += 1;
		if (temp == root) {
			root = temp2;
			temp2.parent = null;
		} else {
			if (temp2.key < temp.parent.key)
				temp.parent.leftChild = temp2;
			else
				temp.parent.rightChild = temp2;

			temp2.parent = temp.parent;
		}

		temp.parent = temp2;
		theNode.parent = temp2;
	}

	// Single Left Rotation
	private void SLR(Node1 theNode) {
		// TODO Auto-generated method stub
		Node1 temp = theNode.parent;
		temp.height -= 2;
		temp.rightChild = theNode.leftChild;

		if (theNode.leftChild != null)
			theNode.leftChild.parent = temp;

		theNode.leftChild = temp;
		if (temp == root) {
			root = theNode;
			theNode.parent = null;
		} else {

			if (theNode.key < temp.parent.key)
				temp.parent.leftChild = theNode;
			else
				temp.parent.rightChild = theNode;
			theNode.parent = temp.parent;
		}
		temp.parent = theNode;
	}

	// Left Right Rotation
	private void LRR(Node1 theNode) {
		// TODO Auto-generated method stub
		Node1 temp = theNode.parent, temp2 = theNode.rightChild;
		temp.height -= 2;

		temp.leftChild = temp2.rightChild;
		if (temp2.rightChild != null)
			temp2.rightChild.parent = temp;

		theNode.rightChild = temp2.leftChild;
		if (temp2.leftChild != null)
			temp2.leftChild.parent = theNode;

		temp2.leftChild = theNode;
		temp2.rightChild = temp;
		if (temp == root) {
			root = temp2;
			temp2.parent = null;
		} else {
			if (temp2.key < temp.parent.key)
				temp.parent.leftChild = temp2;
			else
				temp.parent.rightChild = temp2;

			temp2.parent = temp.parent;
		}
		theNode.parent = temp2;
		temp.parent = temp2;
		temp2.height += 1;
		theNode.height -= 1;
	}

	// Single Right Rotation
	private void SRR(Node1 theNode) {
		// TODO Auto-generated method stub
		Node1 temp = theNode.parent;
		temp.height -= 2;

		temp.leftChild = theNode.rightChild;
		if (theNode.rightChild != null)
			theNode.rightChild.parent = temp;
		theNode.rightChild = temp;

		if (temp == root) {
			root = theNode;
			theNode.parent = null;
		} else {
			if (theNode.key < temp.parent.key)
				temp.parent.leftChild = theNode;
			else
				temp.parent.rightChild = theNode;

			theNode.parent = temp.parent;
		}
		temp.parent = theNode;
	}

	public void preorder() {
		preorder(root);
	}

	private void preorder(Node1 r) {
		if (r != null) {
			System.out.print(r.key + " ");
			preorder(r.leftChild);
			preorder(r.rightChild);
		}
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(Node1 r) {
		if (r != null) {
			inorder(r.leftChild);
			System.out.print(r.key + " ");
			inorder(r.rightChild);
		}
	}

	Node1 find(int key) {
		Node1 j = null;
		if (root.key == key) {
			System.out.print("the node is the root" + root.key + " and the key to find was " + key);
		} else {
			j = find(key, root);

			if (j != null && j.key == key) {
				j.display();
			} else
				return null;
		}
		return j;
	}

	private Node1 find(int key, Node1 node) {
		while (node != null && node.key != key) {
			if (key > node.key)
				node = node.rightChild;

			else if (key < node.key)
				node = node.leftChild;
		}
		return node;
	}

	void delete(int key) {
		Node1 node = root;
		while (key != node.key) {
			if (key > node.key)
				node = node.rightChild;
			else if (key < node.key)
				node = node.leftChild;
		}
		if (node.rightChild == null && node.leftChild == null)
			firstCaseDeletion(node);

		// case 2 has one child
		else if (hasOneChild(node))
			secondCaseDeletion(node);

		// 3rd case has 2 children
		else if (!hasOneChild(node) && node.height > 1)
			thirdCaseDeletion(node);
	}

	private boolean hasOneChild(Node1 node) {
		// TODO Auto-generated method stub
		if ((node.leftChild != null && node.rightChild == null) || (node.rightChild != null && node.leftChild == null))
			return true;
		else
			return false;
	}

	private void firstCaseDeletion(Node1 node) {
		if (node.key > node.parent.key) {
			node = node.parent;
			node.rightChild = null;
		} else if (node.key < node.parent.key) {
			node = node.parent;
			node.leftChild = null;
		}
		balanceHandler(node);
	}

	private void secondCaseDeletion(Node1 node) {
		if (node.rightChild == null && node.leftChild != null) {
			if (node.key > node.parent.key)
				node.parent.rightChild = node.leftChild;

			// the equal is for the 3rd case deletion to work if the leaf node had a child.
			// and was the immediate left child instead of a
			// right leaf of this left child.
			else if (node.key <= node.parent.key)
				node.parent.leftChild = node.leftChild;

			// either case I will assign the grandparent as the parent of the deleted node's
			// child.
			node.leftChild.parent = node.parent;
		} else if (node.leftChild == null && node.rightChild != null) {
			if (node.key > node.parent.key)
				node.parent.rightChild = node.rightChild;

			else if (node.key < node.parent.key)
				node.parent.leftChild = node.rightChild;

			node.rightChild.parent = node.parent;
		}
		balanceHandler(node.parent);
	}

	private void thirdCaseDeletion(Node1 node) {
		Node1 parent = node.parent, leafNode = node.leftChild, leafCopy = null;

		while (leafNode.rightChild != null)
			leafNode = leafNode.rightChild;

		leafCopy = new Node1(leafNode.key);

		if (node.key > parent.key) {
			parent.rightChild = leafCopy;
			leafCopy.parent = parent;
		} else if (node.key < parent.key) {
			parent.leftChild = leafCopy;
			leafCopy.parent = parent;
		}

		// the height of leafCopy will be updated in the deletion of the leaf Node.
		leafCopy.rightChild = node.rightChild;
		node.rightChild.parent = leafCopy;

		leafCopy.leftChild = node.leftChild;
		node.leftChild.parent = leafCopy;

		if (hasOneChild(leafNode))
			secondCaseDeletion(leafNode);
		else
			firstCaseDeletion(leafNode);
	}
}
