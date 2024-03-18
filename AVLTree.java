// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AVLTree<dataType extends Comparable<? super DataItem>> extends BinaryTree<DataItem> 
{
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
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
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
      if (node == null)
         return new BinaryTreeNode<DataItem> (d, null, null);
      if (d.compareTo (node.data) <= 0)
         node.left = insert (d, node.left);
      else
         node.right = insert (d, node.right);
      return balance (node);
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
   public BinaryTreeNode<DataItem> find ( DataItem d, BinaryTreeNode<DataItem> node )
   {
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
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
                DataItem queried = new DataItem(queryItem, null,0);
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
            System.out.println(itemName + ": " + resultNode.getData().getStatement());
            //System.out.println("Ahhh, who do we have here?");
        } else {
            System.out.println("Term not found: " + queryItem.getItem());
        }
    }
}

