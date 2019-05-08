package studentCoursesBackup.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import studentCoursesBackup.myTree.Node;

/**
 * Result class to store the inorder traversal of the tree and it's two backUp listeners
 * @author pragyavishalakshi
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	/**
	 * arrayList to store the inorder traversal of the tree
	 */
	private ArrayList<String> resultList = null;

	/**
	 * default Constructor of Results
	 */
	public Results() {
		resultList = new ArrayList<>();
	}

	/**
	 * add the string in the resultList
	 * @param dataIn
	 */
	public void addResult(String dataIn) {
		if(dataIn != null) {
			resultList.add(dataIn);
		}
	}

	/**
	 * Inorder Traverse the tree from the node given
	 * @param nodeIn
	 */
	private void inOrder(Node nodeIn) {
		Node locptr;
		locptr = nodeIn;
		if(locptr != null) {
			inOrder(locptr.getLeft());			//recursive call of inOrder on left node
			addResult(locptr.toString());		//add the node's value in ArrayList object of Results
			inOrder(locptr.getRight());			//recursive call of inOrder on right node
		}
	}

	@Override
	public void printConsole(TreeBuilder treeIn) {
		System.out.println("Student Courses Data in Node");
		print(treeIn.getMyRoot());

		System.out.println("Student Courses Data in Backup1");
		print(treeIn.getBackUpNodeOne());

		System.out.println("Student Courses Data in Backup2");
		print(treeIn.getBackUpNodeSecond());
	}

	/**
	 * print on console the data in the resultList after inorder traversal
	 * @param rootIn
	 */
	private void print(Node rootIn) {
		inOrder(rootIn);
		for (String data : resultList) {
			System.out.println(data);
		}
		resultList.clear();
	}

	@Override
	public void writeToFile(TreeBuilder treeIn, String fileOp1In, String fileOp2In, String fileOp3In) {
		writeFile(treeIn.getMyRoot(), fileOp1In);
		writeFile(treeIn.getBackUpNodeOne(), fileOp2In);
		writeFile(treeIn.getBackUpNodeSecond(), fileOp3In);
	}

	/**
	 * write to File the data in the resultList after inorder traversal
	 * @param rootIn
	 * @param fileIn
	 */
	private void writeFile(Node rootIn, String fileIn) {
		BufferedWriter br = null;
		try {
			inOrder(rootIn);
			br = new BufferedWriter(new FileWriter(fileIn));
			for (String data : resultList) {
				br.write(data+"\n");
			}
			resultList.clear();
		} catch (IOException e) {
			System.out.println("IOException for Write file");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
