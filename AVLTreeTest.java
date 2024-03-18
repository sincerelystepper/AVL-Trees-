// Kopano's AVL test class with the main method
// 18 March 2024
// Kopano Maketekete


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
    avlTree.loadFromQuery("GenericsKB-queries.txt");

    // Adding the item objects to the avl tree. 
    

      

     // AVLTree<DataItem> avlTree = new AVLTree<>();
// Insert items into the tree (your code)

   

   // do a while loop for as long as there is a word in the list
 } 
}

