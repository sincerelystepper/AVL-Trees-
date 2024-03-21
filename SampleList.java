import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleList {
    public SampleList (){}

    public void loadFromFile (String filename, List<String> SampleIndexes) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String> sampleData = new ArrayList<>(); // Temporary list for sample lines 

            int lineCount = 0; // Keep track of line number during reading 
            while ((line = reader.readLine()) != null) {
                if (SampleIndexes.contains(String.valueOf(lineCount))) {
                    sampleData.add(line); // Add line if its index is in the sample
                }
                
            } lineCount++;
        } System.out.println("The list is loaded successfully.");

    }
/*
public static void main (String [] args) {
    AVLTree<DataItem> tree = new AVLTree<>();
    //tree.loadFromFile("GenericsKB.txt", "10");
}
*/
}