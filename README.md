# Observer-Pattern-Student-Course-Backup

The execution takes 5 parameters, absolute path of all the files are provided.

Observer Pattern:

Node.java that implements Cloneable, SubjectI and ObjectI
One-to-many relationship is defined for Node class. TreeBuilder has 3 instance of Node, each behaving as the root for 1 original and 2 backup trees.
myRoot in TreeBuilder is the original tree root. Each node in this original tree stores the reference to it's listener node object. Each time when a new object of Node is created for myRoot tree, the two reference nodes are created and are stored in it's respective reference.
- And while creating the backup node, the node object from the orginal node is taken instead of creating a new node again.
- When there's an update in any of the original node, observing node calls the notify method of SubjectI which in turn calls update method of ObserverI to update the listeners (No traversing on backup tree takes place for updating the backups, they are updated by the reference stored in the original node).
- Cloneable is implemented as the clone of original Nodes are created for back up. I have not used super.clone(), as it does the shallow copy, so whenever there's an update in original node, the data in backup node also gets updated and the Observer pattern implementation is hindered. Hence, deep copy method is written in clone() method.
- If the Bno is not present in tree, error message is printed in the console. But if Bno is present, but the course is missing, it just moves ahead wiothout printing any message.

TreeBuilder.java
- All the methods related to tree operations are written in this class. I have used AVL tree.
- Complexity: It takes more time than BST while inserting the code, as the (mainly) rotation is involved after inserting the node. But it takes O(lg n) , where n is number of nodes, time for other operations. And since most of the operation of tree revolves around search (and deletion), this reduces the overall complexity of running the algorithm, when huge inputs are provided.

Results.java, implements the FileDisplayInterface and StdoutDisplayInterface
- In Driver class, the instance of Results is created, but is typecasted to it's respective interface based on the function it is suppose to perform. StdoutDisplayInterface instace is used for debugging purpose (to print in console), hence I have commented that code in Driver.
- Whereas, fileDisplayInterface instance does print the data in output file. If the file is not present, it creates it, and overwrite it with new data, if the output file is already present.

FileProcessor.java
- It reads all the line in the file and returns the ArrayList of String with all the lines that are read one after another in sequence.

Driver.java
It contains the main method.
