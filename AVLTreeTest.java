// Hussein's AVL Tree
// 2 April2017
// Hussein Suleman


public class AVLTreeTest extends DataItem// Convert this to GenericsKbAVLApp later
{

    public AVLTreeTest(String term, String statement, double confidenceScore){
        super(term, statement, confidenceScore);   
    
    }

    
   /**
 * @param args
 */
public static void main ( String [] args ){

    
      
      // Usage:
    AVLTree<DataItem> avlTree = new AVLTree<>();

    avlTree.loadFromFile("GenericsKB.txt");

    avlTree.insert(new DataItem("apple", "A fruit", 0.9));

    avlTree.insert(new DataItem("banana", "Yellow and sweet", 0.85));
    avlTree.insert(new DataItem("Kopano", "Kopano is hot.", 1));


      

     // AVLTree<DataItem> avlTree = new AVLTree<>();
// Insert items into the tree (your code)

DataItem targetItem = new DataItem("Kopano", null, 0); // Adjust for your target item
BinaryTreeNode<DataItem> foundNode = avlTree.find(targetItem); 

if (foundNode != null) {
    DataItem foundData = foundNode.data;
    System.out.println("Item found: " + foundData.getItem());
} else {
    System.out.println("Item not found");

   }
 } 
}

