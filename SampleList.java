import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleList {
    public SampleList (){}

    public static void main(String[] args) {
        // Load your entire dataset of DataItems      
        
        
        AVLTree<DataItem> avlTree = new AVLTree<>();

        avlTree.loadFromFile("GenericsKB.txt");
        avlTree.loadFromQuery("GenericsKB-queries.txt");
        System.out.println();
        System.out.println();
        System.out.println("These are the test query values used: ");
        avlTree.loadFromQuery("test_queries.txt");
        avlTree.printComparisonCounts();
    
        System.out.println("~Part One Ends Here~");

        System.out.println("");
        System.out.println("~Part Two Starts Here~");

        List<DataItem> entireDataset = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader("GenericsKB.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Replace this with your logic to parse the line and create a DataItem
        String[] parts = line.split("\t"); // Assuming data is tab-delimited
        if (parts.length == 3) {
          String item = parts[0].trim();
          String statement = parts[1].trim();
          double confidence = Double.parseDouble(parts[2].trim());
          entireDataset.add(new DataItem(item, statement, confidence));
        } else {
          // Handle cases where the line doesn't have the expected format (optional)
          System.err.println("Warning: Line skipped due to incorrect format: " + line);
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    
    
        // Define sample sizes and run count
        int[] sampleSizes = {10, 100, 1000, 4000, 8000, 10000, 40000, 50000, 55000, 100000};
        int numRuns = 10; // Number of runs for each sample size
    
        List<Integer>[] insertCounts = new List[sampleSizes.length];
        List<Integer>[] searchCounts = new List[sampleSizes.length];
    
        for (int i = 0; i < sampleSizes.length; i++) {
            insertCounts[i] = new ArrayList<>();
            searchCounts[i] = new ArrayList<>();
    
            for (int j = 0; j < numRuns; j++) {
                int insertComparisons = 0;
                int searchComparisons = 0;
    
                // ... (code to load entireDataset and create sampleIndexes remains unchanged)
    
                AVLTree<DataItem> tree = new AVLTree<>();
    
                for (String sampleIndex : sampleIndexes) {
                    // ... (code to insert itemToInsert remains unchanged)
                    int dataItemIndex = Integer.parseInt(sampleIndex); // Convert index to integer
                    DataItem itemToInsert = entireDataset.get(dataItemIndex);
                    tree.insert(itemToInsert); // Insert data item into AVL tree
    
                    insertComparisons += tree.getInsertComparisons(); // Track insert comparisons
    
                    // ... (code to search for itemToSearch remains unchanged)
                    DataItem itemToSearch = entireDataset.get(dataItemIndex);
                    tree.find(itemToSearch);
    
                    searchComparisons += tree.getSearchComparisons(); // Track search comparisons
                }
    
                insertCounts[i].add(insertComparisons); // Store comparison counts
                searchCounts[i].add(searchComparisons);
            }
    
            // Calculate minimum, maximum, and average for the current sample size
            int minInsertCount = Integer.MAX_VALUE;
            int maxInsertCount = Integer.MIN_VALUE;
            int minSearchCount = Integer.MAX_VALUE;
            int maxSearchCount = Integer.MIN_VALUE;
            double avgInsertCount = 0.0;
            double avgSearchCount = 0.0;
    
            // ... (calculate min, max, avg using stored counts in insertCounts[i] and searchCounts[i])
    
            System.out.println("Sample Size: " + sampleSizes[i]);
            System.out.println("- Insert Comparisons:");
            System.out.println("  Minimum: " + minInsertCount);
            System.out.println("  Maximum: " + maxInsertCount);
            System.out.println("  Average: " + avgInsertCount);
            System.out.println("- Search Comparisons:");
            System.out.println("  Minimum: " + minSearchCount);
            System.out.println("  Maximum: " + maxSearchCount);
            System.out.println("  Average: " + avgSearchCount);
        }
}}