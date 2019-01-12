
class Node1 {
	int key, height = 0;
	Node1 leftChild, rightChild, parent;

	Node1(int key) {
		this.key = key;
	}

	void display() {
		System.out.println("the Node is " + this.key + " and its height is " + this.height);
		if (this.leftChild != null)
			System.out
					.println("the left Child is " + this.leftChild.key + " and its height is " + this.leftChild.height);
		if (this.rightChild != null)
			System.out.println(
					"the right Child is " + this.rightChild.key + " and its height is " + this.rightChild.height);
		System.out.println("\n\n");
	}
}