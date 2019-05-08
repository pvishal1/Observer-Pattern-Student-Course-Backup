package studentCoursesBackup.myTree;

import java.util.ArrayList;

/**
 * Node class to store the B-number, it's courses, left and right node for this node and two listener of this node
 * @author pragyavishalakshi
 *
 */
public class Node implements SubjectI, ObserverI, Cloneable{
	private int bNo;
	private ArrayList<String> course; 
	private Node left, right;
	ObserverI backUpNodeOneRef, backUpNodeSecondRef;

	public enum operations {
		insert, delete
	}

	/**
	 * parameterized Constructor
	 * @param bNoIn
	 * @param courseIn
	 */
	public Node(int bNoIn, String courseIn) {
		super();
		this.bNo = bNoIn;
		this.course = new ArrayList<>();
		course.add(courseIn);
		this.left = null;
		this.right = null;
	}

	/**
	 * register the observers with this node
	 * @param bNoIn
	 * @param courseIn
	 */
	public void register(int bNoIn, String courseIn) {
		backUpNodeSecondRef = cloneNode(bNoIn, courseIn);
		backUpNodeOneRef = cloneNode(bNoIn, courseIn);
	}
	
	/**
	 * un-register the observers with this node
	 * @param bNoIn
	 * @param courseIn
	 */
	public void unRegister(int bNoIn, String courseIn) {
		backUpNodeSecondRef = null;
		backUpNodeOneRef = null;
	}

	/**
	 * get the B-number of the Node
	 * @return b-number
	 */
	public int getbNo() {
		return bNo;
	}

	/**
	 * set the B-number to this node 
	 * @param bNo
	 */
	public void setbNo(int bNoIn) {
		this.bNo = bNoIn;
	}

	/**
	 * get the ArrayList of courses for this node
	 * @return ArrayList<String> of courses
	 */
	public ArrayList<String> getCourse() {
		return course;
	}

	/**
	 * set the course of this node to the ArrayList of course provided
	 * @param ArrayList of course to be set
	 */
	public void setCourse(ArrayList<String> courseIn) {
		this.course = courseIn;
	}

	/**
	 * get the left node of this node
	 * @return Left node
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * set the left node of this node to the node provided
	 * @param node to be set as left node
	 */
	public void setLeft(Node leftIn) {
		this.left = leftIn;
	}

	/**
	 * get the right node of this node
	 * @return Right node
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * set the right node of this node to the node provided
	 * @param node to be set as right node
	 */
	public void setRight(Node rightIn) {
		this.right = rightIn;
	}

	/**
	 * @returns the String containing b-number and respective courses for this node
	 */
	@Override
	public String toString() {
		String r = bNo + ": ";
		for (String string : course) {
			r = r.concat(string + " ");
		}
		return r;
	}

	/**
	 * create deep copy of the node into a new node
	 * @param bNoIn
	 * @param courseIn
	 * @return new Node
	 */
	private Node cloneNode(int bNoIn, String courseIn) {
		return new Node(bNoIn, courseIn);
	}

	/**
	 * get the one reference of this node, typecasted to Node
	 * @return reference node
	 */
	public Node getBackUpNodeOneRef() {
		return (Node) backUpNodeOneRef;
	}

	/**
	 * set the node as the first reference
	 * @param node to be set as reference
	 */
	public void setBackUpNodeOneRef(Node subjectI) {
		this.backUpNodeOneRef = subjectI;
	}

	/**
	 * get the second reference of this node, typecasted to Node
	 * @return reference node
	 */
	public Node getBackUpNodeSecondRef() {
		return (Node) backUpNodeSecondRef;
	}

	/**
	 * set the node as the second reference
	 * @param node to be set as reference
	 */
	public void setBackUpNodeSecondRef(Node observerI) {
		this.backUpNodeSecondRef = observerI;
	}

	/**
	 * Notify all the listeners about the changed course
	 * @param course to be inserted or deleted
	 * @param operationIn (insert or delete)
	 */
	public void notifyAll(String courseIn, String operationIn) {
		backUpNodeOneRef = notify(backUpNodeOneRef, courseIn, operationIn);
		backUpNodeSecondRef = notify(backUpNodeSecondRef, courseIn, operationIn);
	}

	/**
	 * notify the observer with the course to be edited and what operation to be performed
	 */
	@Override
	public Node notify(ObserverI obIn, String courseIn, String operationIn) {
		return obIn.update(courseIn, operationIn);
	}

	/**
	 * update the observer with the course to be edited and what operation to be performed
	 */
	@Override
	public Node update(String courseIn, String operationIn) {
		ArrayList<String> courses = this.getCourse();
		if(operationIn.equals(operations.insert.toString())) {
			if(!courses.contains(courseIn)) {
				courses.add(courseIn);
				this.setCourse(courses);
			}
		} else if(operationIn.equals(operations.delete.toString())) {
			if(courses.contains(courseIn)) {
				courses.remove(courses.indexOf(courseIn));
				this.setCourse(courses);
			}
		}
		return (Node)this;
	}
}
