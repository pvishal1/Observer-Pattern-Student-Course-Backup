package studentCoursesBackup.util;

import java.util.ArrayList;

import studentCoursesBackup.myTree.Node;

/**
 * Class used for building Tree using Node objects
 * @author pragyavishalakshi
 *
 */
public class TreeBuilder {
	private Node myRoot, backUpNodeOne, backUpNodeSecond;
	private Node n1, n2;

	/**
	 * get the value of myRoot Node
	 * @return root Node
	 */
	public Node getMyRoot() {
		return myRoot;
	}

	/**
	 * Set the value of myRoot Node
	 * @param myRoot
	 */
	public void setMyRoot(Node myRoot) {
		this.myRoot = myRoot;
	}

	/**
	 * get the value of backUpNodeOne Node
	 * @return backUpNodeOne object
	 */
	public Node getBackUpNodeOne() {
		return backUpNodeOne;
	}

	/**
	 * Set the value of myRoot Node
	 * @param subjectNode
	 */
	public void setBackUpNodeOne(Node subjectNode) {
		this.backUpNodeOne = subjectNode;
	}

	/**
	 * get the value of backUpNodeSecond Node
	 * @return backUpNodeSecond
	 */
	public Node getBackUpNodeSecond() {
		return backUpNodeSecond;
	}

	/**
	 * Set the value of backUpNodeSecond Node
	 * @param observerNode
	 */
	public void setBackUpNodeSecond(Node observerNode) {
		this.backUpNodeSecond = observerNode;
	}

	/**
	 * Default constructor
	 */
	public TreeBuilder() {
		super();
		myRoot = null;
	}

	/**
	 * @returns the String to about the treeBuilder class data
	 */
	@Override
	public String toString() {
		String r = "Original tree Root: " + myRoot.toString() + " || First Backup tree Root: "
				+ backUpNodeOne.toString() + " || Second Backup tree Root: " + backUpNodeSecond.toString();

		return r;
	}

	/**
	 * tree is empty the return true else false
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (myRoot == null);
	}

	/**
	 * search the given Bno and return the node having B number and courses
	 * associated with it
	 * 
	 * @param bNoIn
	 * @return
	 */
	public Node searchBNo(int bNoIn) {
		if (myRoot != null) {
			return searchBNo(myRoot, bNoIn);
		}
		return null;
	}

	/**
	 * Search the node in the tree based on the B number
	 * @param nodeIn
	 * @param bNoIn
	 * @return the searched node if found else returns null
	 */
	private Node searchBNo(Node nodeIn, int bNoIn) {
		Node sNode = null;
		if (nodeIn != null) {
			if (nodeIn.getbNo() > bNoIn) {
				sNode = searchBNo(nodeIn.getLeft(), bNoIn);
			} else if (nodeIn.getbNo() < bNoIn) {
				sNode = searchBNo(nodeIn.getRight(), bNoIn);
			} else {
				sNode = nodeIn;
			}
		}
		return sNode;
	}

	/**
	 * gets the maximum number among the two provided
	 * @param aIn
	 * @param bIn
	 * @return maximum from aIn and bIn
	 */
	private int max(int aIn, int bIn) {
		return (aIn > bIn) ? aIn : bIn;
	}

	/**
	 * this gives the height of the tree from the node given
	 * @param Node object
	 * @return height of tree from the given Node
	 */
	private int getHeight(Node nodeIn) {
		int h;
		if (nodeIn == null) {
			return 0;
		} else {
			int l_h = getHeight(nodeIn.getLeft()); // to get height of left child
			int r_h = getHeight(nodeIn.getRight()); // get height of right child
			int max_h = max(l_h, r_h); // get the max height of left or right child
			h = 1 + max_h;
		}
		return h;
	}

	/**
	 * gives the difference of left and right child of the node
	 * @return difference of height of left and right tree from the node given
	 */
	private int checkDiff(Node nodeIn) {
		int l_h = getHeight(nodeIn.getLeft());
		int r_h = getHeight(nodeIn.getRight());
		return (l_h - r_h); // get height difference of left and right child
	}

	/**
	 * left rotation of the node and return the new node created after rotation
	 * @param Node on which rotation is required
	 * @return node after rotating
	 */
	private Node l_rotation(Node nodeIn) {
		Node temp = nodeIn.getRight();
		nodeIn.setRight(temp.getLeft());
		temp.setLeft(nodeIn);
		;
		return temp;
	}

	/**
	 * right rotation of the node and return the new node created after rotation
	 * @param Node on which rotation is required
	 * @return node after rotating
	 */
	private Node r_rotation(Node nodeIn) {
		Node temp = nodeIn.getLeft();
		nodeIn.setLeft(temp.getRight());
		temp.setRight(nodeIn);
		return temp;
	}

	/**
	 * left rotation on left of node and then do right rotation on node
	 * @param Node on which rotation is required
	 * @return node after rotating
	 */
	private Node lr_rotation(Node nodeIn) {
		Node temp = nodeIn.getLeft();
		nodeIn.setLeft(l_rotation(temp)); // left rotate the left child of node
		return r_rotation(nodeIn); // right rotate the node
	}

	/**
	 * right rotation on right of node and then do left rotation on node
	 * @param Node on which rotation is required
	 * @return node after rotating
	 */
	private Node rl_rotation(Node nodeIn) {
		Node temp = nodeIn.getRight();
		nodeIn.setRight(r_rotation(temp)); // right rotate the right child of node
		return l_rotation(nodeIn); // left rotate the ndoe
	}

	/**
	 * check the balance of the tree and perfrom the rotation (whichever is
	 * required)
	 * @param Node on which rotation is required
	 * @return node after rotating
	 */
	private Node rotation(Node nodeIn) {
		int difference = checkDiff(nodeIn);
		if (difference > 1) {
			if (checkDiff(nodeIn.getLeft()) > 0) {
				nodeIn = r_rotation(nodeIn);
			} else {
				nodeIn = lr_rotation(nodeIn);
			}
		} else if (difference < -1) {
			if (checkDiff(nodeIn.getRight()) < 0) {
				nodeIn = l_rotation(nodeIn);
			} else {
				nodeIn = rl_rotation(nodeIn);
			}
		}
		return nodeIn;
	}

	/**
	 * wrapper for insert in Tree
	 * @param B number to be inserted
	 * @param Course to be inserted
	 */
	public void insert(int bNoIn, String courseIn) {
		if ((bNoIn <= 9999) && (courseIn.compareTo("L") < 0)) {
			myRoot = insert(bNoIn, courseIn, myRoot);
			if (n1 != null && n2 != null) {
				backUpNodeOne = insertNewNode(bNoIn, courseIn, backUpNodeOne, n1);
				backUpNodeSecond = insertNewNode(bNoIn, courseIn, backUpNodeSecond, n2);
			}
		}
	}

	/**
	 * insert the node in the tree
	 * @param B number to be inserted
	 * @param Course to be inserted
	 * @param root Node of the tree
	 * @return the node after insertion
	 */
	private Node insert(int bNoIn, String courseIn, Node rootNodeIn) {
		Node node = searchBNo(bNoIn);
		if (node != null) {
			ArrayList<String> arr = node.getCourse();
			if (!arr.contains(courseIn)) {
				arr.add(courseIn);
				node.setCourse(arr);
				node.notifyAll(courseIn, Node.operations.insert.toString());
			}
		} else {
			if (rootNodeIn == null) {
				rootNodeIn = new Node(bNoIn, courseIn);
				rootNodeIn.register(bNoIn, courseIn);
				n1 = rootNodeIn.getBackUpNodeOneRef();
				n2 = rootNodeIn.getBackUpNodeSecondRef();
			} else if (bNoIn < rootNodeIn.getbNo()) {
				rootNodeIn.setLeft(insert(bNoIn, courseIn, rootNodeIn.getLeft()));
				rootNodeIn = rotation(rootNodeIn);
			} else if (bNoIn > rootNodeIn.getbNo()) {
				rootNodeIn.setRight(insert(bNoIn, courseIn, rootNodeIn.getRight()));
				rootNodeIn = rotation(rootNodeIn);
			}
		}
		return rootNodeIn;
	}

	/**
	 * Insert the node(listener), from observer of the node
	 * @param bNoIn
	 * @param courseIn
	 * @param rootNodeIn
	 * @param nodeIn
	 * @return node after inserting
	 */
	private Node insertNewNode(int bNoIn, String courseIn, Node rootNodeIn, Node nodeIn) {
		if (rootNodeIn == null) {
			rootNodeIn = nodeIn;
		} else if (bNoIn < rootNodeIn.getbNo()) {
			rootNodeIn.setLeft(insertNewNode(bNoIn, courseIn, rootNodeIn.getLeft(), nodeIn));
			rootNodeIn = rotation(rootNodeIn);
		} else if (bNoIn > rootNodeIn.getbNo()) {
			rootNodeIn.setRight(insertNewNode(bNoIn, courseIn, rootNodeIn.getRight(), nodeIn));
			rootNodeIn = rotation(rootNodeIn);
		}
		return rootNodeIn;
	}

	/**
	 * delete the node and balance the avl tree after deletion
	 * @param B number to be deleted
	 * @param Course to be deleted
	 */
	public void delete(int bNoIn, String courseIn) {
		Node node = searchBNo(bNoIn);
		if (node != null) {
			ArrayList<String> arr = node.getCourse();
			if (arr.contains(courseIn)) {
				arr.remove(arr.indexOf(courseIn));
				node.setCourse(arr);
				node.notifyAll(courseIn, Node.operations.delete.toString());
			}
		} else {
			System.out.println("Student record for " + bNoIn + " doesn't exists.");
		}
	}
}
