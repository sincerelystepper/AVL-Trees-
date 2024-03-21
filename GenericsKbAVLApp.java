// Kopano's AVL test class with the main method
// 18 March 2024
// Kopano Maketekete

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenericsKbAVLApp extends DataItem// Convert this to GenericsKbAVLApp later
{

    public GenericsKbAVLApp(String term, String statement, double confidenceScore){
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
    System.out.println();
    System.out.println();
    System.out.println("These are the test query values used: ");
    avlTree.loadFromQuery("test_queries.txt");
    avlTree.printComparisonCounts();

    System.out.println("~Part One Ends Here~");


  // Instrumentation starts here

      //Data set file 
      String dataSetFile = "GenericsKB.txt";

     // Experiment sizes (adjust as needed)
     int[] datasetSizes = {10, 100, 1000, 10000, 100000};
     

     // Perform experiment for each size
     for (int n : datasetSizes) {
      System.out.println("Dataset Size (Sampled from File): " + n);
      List<DataItem> entireDataset = new ArrayList<>(); }
/*
      public static List<DataItem> getRandomSample(List<DataItem> entireDataset, int sampleSize) {
        List<DataItem> sample = new ArrayList<>();
        Random random = new Random();
    
        // Ensure sample size doesn't exceed dataset size
        if (sampleSize > entireDataset.size()) {
            sampleSize = entireDataset.size();
            System.out.println("Warning: Sample size exceeds dataset size. Using entire dataset.");
        }
    
        for (int i = 0; i < sampleSize; i++) {
            // Random index within the dataset bounds
            int randomIndex = random.nextInt(entireDataset.size());
            sample.add(entireDataset.get(randomIndex));
        }
        return sample;
    }
    

      // Random sample generation
      List<String> randomSubset = getRandomSample(entireDataset, n);

      try {
                avlTree.loadFromFile("GenericsKB.txt");
                //entireDataset = avlTree.getData().getItem(); // Assuming a method to get all items
      } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                continue; // Skip to next size if loading fails
      }



    }

    
*/
 } 
}

