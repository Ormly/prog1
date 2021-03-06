
public class Tree {
	private Node rootNode;
	private Node[] treeNodes;;

	// Gets an array of leaf nodes, ordered by frequency, and builds the tree from ground up
	// then saves the root Node in rootNode.
	public Tree(Node[] leaves) {
		this.treeNodes = leaves;
		constructTree();
	}

	public Node getRoot() {
		return this.rootNode;
	}

	private void constructTree() {
		// Create first Node of two rarest symbols and 
		// Until Node frequency is equal to 1.0 
		// create n-1 array with new Node and the rest of the leaves
		// sort new array 
		// Create another node from two rarest symbols
		sortLeaves();

		Node currentNode;

		do{
			currentNode = new Node(this.treeNodes[0], this.treeNodes[1]);
			addNewNode(currentNode);
		}
		while (currentNode.getFrequency() < 1.0);

		this.rootNode = currentNode;
	}

	private void addNewNode(Node newNode) {
		Node[] temp = new Node[this.treeNodes.length -1];
		temp[0] = newNode;

		for(int tempIndex = 1, i=2; i < this.treeNodes.length; i++, tempIndex++) {
			temp[tempIndex] = this.treeNodes[i];
		}
		this.treeNodes = temp;
		sortLeaves();
	}

	// Bubble sorts the leaves of the tree
	private void sortLeaves() {

		boolean swapOccured;

		do {
			swapOccured = false;

			for(int index=0; index < this.treeNodes.length - 1; index++) {
				int currentLeaf = index;
				int nextLeaf = currentLeaf + 1;
				if(this.treeNodes[currentLeaf].getFrequency() > this.treeNodes[nextLeaf].getFrequency()) {
					swapLeaves(currentLeaf, nextLeaf);
					swapOccured=true;
				}
			}
		}
		while(swapOccured);
	}

	// Utility function of the bubble sorting algorithm
	private void swapLeaves(int firstLeaf, int secondLeaf) {
		Node temp = this.treeNodes[firstLeaf];
		this.treeNodes[firstLeaf] = this.treeNodes[secondLeaf];
		this.treeNodes[secondLeaf] = temp;
	}

	public String toString() {
		String output = "";
		for(int i=0; i<this.treeNodes.length; i++) {
			output += this.treeNodes[i].toString();
		}

		return output;
	}

	// Returns a double representing the sum of all frequencies of the leaves
	public double sumOfFrequencies() {
		double output = 0.0;
		for(int i=0; i<this.treeNodes.length; i++) {
			output += this.treeNodes[i].getFrequency();
		}

		return output;
	}
	
	public void print() {
		this.rootNode.print();
	}
}
