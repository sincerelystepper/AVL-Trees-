// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AVLTree<dataType extends Comparable<? super DataItem>> extends BinaryTree<DataItem> 
{
   private int searchCount = 0; 
   private int insertCount = 0;
   private int countBalance = 0;

   public int height ( BinaryTreeNode<DataItem> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }
   
   public int balanceFactor ( BinaryTreeNode<DataItem> node )
   {
      return height (node.right) - height (node.left);
   }
   
   public void fixHeight ( BinaryTreeNode<DataItem> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
   
   public BinaryTreeNode<DataItem> rotateRight ( BinaryTreeNode<DataItem> p )
   {
      BinaryTreeNode<DataItem> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   public BinaryTreeNode<DataItem> rotateLeft ( BinaryTreeNode<DataItem> q )
   {
      BinaryTreeNode<DataItem> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   
   public BinaryTreeNode<DataItem> balance ( BinaryTreeNode<DataItem> p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
            countBalance++; // Increments operation to the left
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
            countBalance++; // Increments operation to the right
         return rotateRight (p);
      }
      return p;
   }

   

   public void insert ( DataItem d )
   {
      root = insert (d, root);
   }
   public BinaryTreeNode<DataItem> insert ( DataItem d, BinaryTreeNode<DataItem> node )
   {
      if (node == null) {     
         insertCount++; // Increment count for empty tree   
         return new BinaryTreeNode<DataItem> (d, null, null);}

      if (d.compareTo (node.data) <= 0) {  
         insertCount++; // Increment count for comparisons
         node.left = insert (d, node.left);}        

      else 
         node.right = insert (d, node.right);   
         insertCount++;  // Increment count for comparisons
         return balance (node);
   }

   public int getInsertCount() {
      return insertCount;
  }

   
   public void delete ( DataItem d )
   {
      root = delete (d, root);
   }   
   public BinaryTreeNode<DataItem> delete ( DataItem d, BinaryTreeNode<DataItem> node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else
      {
         BinaryTreeNode<DataItem> q = node.left;
         BinaryTreeNode<DataItem> r = node.right;
         if (r == null)
            return q;
         BinaryTreeNode<DataItem> min = findMin (r);
         min.right = removeMin (r);
         min.left = q;
         return balance (min);
      }
      return balance (node);
   }
   
   public BinaryTreeNode<DataItem> findMin ( BinaryTreeNode<DataItem> node )
   {
      if (node.left != null)
         return findMin (node.left);
      else
         return node;
   }

   public BinaryTreeNode<DataItem> removeMin ( BinaryTreeNode<DataItem> node )
   {
      if (node.left == null)
         return node.right;
      node.left = removeMin (node.left);
      return balance (node);
   }

   public BinaryTreeNode<DataItem> find (DataItem d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   public BinaryTreeNode<DataItem> find ( DataItem d, BinaryTreeNode<DataItem> node ) // Search method
   {
      if (d.compareTo (node.data) == 0) {
         //System.out.println("Going through");
         searchCount++; // Search comparison which will return one comparison since it only compares one node to the root node.
         return node;} 
      else if (d.compareTo (node.data) < 0) {
         searchCount++; //  Search comparison to the left of the  AVL tree, recurcively
         return (node.left == null) ? null : find (d, node.left);}
      else {
         searchCount++; // Search comparison to the right of the AVL tree, recursively
         return (node.right == null) ? null : find (d, node.right);}
   }

   public int getSearchCount() {
      return searchCount;
  }

   
   public void treeOrder ()
   {
      treeOrder (root, 0);
   }
   public void treeOrder ( BinaryTreeNode<DataItem> node, int level )
   {
      if (node != null)
      {
         for ( int i=0; i<level; i++ )
            System.out.print (" ");
         System.out.println (node.data);
         treeOrder (node.left, level+1);
         treeOrder (node.right, level+1);
      }
   }
   
    /**
     * @param filename
     */
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String item = parts[0].trim();
                    String statement = parts[1].trim();
                    double confidence = Double.parseDouble(parts[2].trim());
                    DataItem itemToInsert = new DataItem(item, statement, confidence);
                    insert(itemToInsert);
                }
            } System.out.println("File loaded succesfully ");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void loadFromQuery(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {


                String queryItem = line.trim(); // Assuming each line contains a single query item
                DataItem queried = new DataItem(queryItem, null,0); // Creating an object of DataItem, with no statement and score, so that it can use the methods
                searchAndPrintResult(queried);
            }
            System.out.println("Queries processed successfully.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void searchAndPrintResult(DataItem queryItem) { 
        BinaryTreeNode<DataItem> resultNode = find(queryItem);
        if (resultNode != null) {
            String itemName = queryItem.getItem(); // Key
            //System.out.println("let's see if its you.");
            System.out.print(itemName + ": " + resultNode.getData().getStatement() + " ");
            System.out.println("(" + resultNode.getData().getConfidence() + ")");
            //System.out.println("Ahhh, who do we have here?");
        }         
        
        else {
            System.out.println("Term not found: " + queryItem.getItem());
        }
    }

    public String printComparisonCounts() {
      System.out.println();
      System.out.println("Search Comparisons: " + searchCount);
      System.out.println("Insert Comparisons: " + insertCount);
      System.out.println("Balance Comparisons: " + countBalance);
      System.out.println();
      return "Search Comparisons: " + searchCount + ", Insert Comparisons: " + insertCount + ", Balance Comparisons: " + countBalance;
    }

    public DataItem createDataItemFromLine(String line) {
      String [] parts = line.split("\t");
      if (parts.length == 3) {
         String item = parts[0].trim();
         String statement = parts[1].trim();
         Double confidence = Double.parseDouble(parts[2].trim());
         return new DataItem(item, statement, confidence);
      }
      else {
         System.out.println("The line does not have an expected format. ");
         return null;
      }
    }

    public void loadFromFile (String filename, List<String> SampleIndexes) throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;          
          List<DataItem> sampleData = new ArrayList<>(); // Temporary list for sample lines (sample DataItems)

          int lineCount = 0; // Keep track of line number during reading 
          while ((line = reader.readLine()) != null) {
              if (SampleIndexes.contains(String.valueOf(lineCount))) {
                  
                  DataItem itemToInsert = createDataItemFromLine(line);
                  sampleData.add(itemToInsert); // Add line if its index is in the sample
              }
              
          } lineCount++;
      } System.out.println("The list is loaded successfully.");

  }

  public static List<String> getRandomSample(List<DataItem> entireDataset, int sampleSize) {
   List<Integer> randomIndexes = new ArrayList<>();
   Random random = new Random();
 
   // Ensure sample size doesn't exceed dataset size
   if (sampleSize > entireDataset.size()) {
     sampleSize = entireDataset.size();
     System.out.println("Warning: Sample size exceeds dataset size. Using entire dataset.");
   }
 
   // Generate random indexes
   for (int i = 0; i < sampleSize; i++) {
     // Random index within the dataset bounds
     int randomIndex = random.nextInt(entireDataset.size());
     while (randomIndexes.contains(randomIndex)) { // Ensure unique indexes
       randomIndex = random.nextInt(entireDataset.size());
     }
     randomIndexes.add(randomIndex);
   }
 
   // Convert indexes to strings and return
   List<String> sampleIndexes = new ArrayList<>();
   for (int randomIndex : randomIndexes) {
     sampleIndexes.add(String.valueOf(randomIndex));
   }
   return sampleIndexes;
 }
 
  
}

