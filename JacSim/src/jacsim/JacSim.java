/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package jacsim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class JacSim {

    // Static variables used to track most similar sentences
    private static int sentence1 = 0;
    private static int sentence2 = 0;
    private static double coefficient = 0;
    
    /**
     * Reads sentences from a given document and calculates the Jaccard 
     * Similarity Index
     * 
     * @param args  The command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Create file object from command line argument
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        
        // List will store sentences read from file using scanner object
        List<String> sentences = new ArrayList<>();
        
        // Loop through document and add sentences to list
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            sentences.add(line);
        }
        
        // Output sentences to user
        System.out.println("\nInput Sentences:");
        
        for (int i = 0; i < sentences.size(); i++) {
            System.out.println(i + ": " + sentences.get(i));
        }
        
        // 2-D array will store sorted shingles constructed from sentences
        String[][] SortedShingleArrays = new String[sentences.size()][];
        
        // Loop through sentences list
        // Use method to create shingle sets and store the resulting array
        for (int i = 0; i < sentences.size(); i++) {
            SortedShingleArrays[i] = CreateShingleSet(sentences.get(i));
        }
        
        // Output sorted shingle sets to user
        System.out.println("\nSorted Shingle Arrays:");
        for (int i = 0; i < sentences.size(); i++) {
            DisplayShingleSet(SortedShingleArrays[i], i);
        }       
        
        // 2-D array will store results of Jaccard calculations
        double[][] jaccard = new double[sentences.size()][sentences.size()];
        
        // Use nested for loops to loop through ShortedSingleArray and calculate 
        // Jaccard similarity index for each sentence
        for (int i = 0; i < sentences.size(); i++){
            for (int j = 0; j < sentences.size(); j++) {
                double result = CalculateJaccard(SortedShingleArrays[i], SortedShingleArrays[j]);
                
                // If current indexes do not equal each other and the result is greater 
                // than the current coefficent, assign a new set of similar sentences to static
                // variables
                if (result > coefficient && i != j) {
                    coefficient = result;
                    sentence1 = i;
                    sentence2 = j;
                }
                
                jaccard[i][j] = result;
            }
        }
        
        // Display similarity matrix to user
        System.out.println("\nJaccard Similarity Matrix:");
        
        // Use nested for loop to assist in displaying matrix properly
        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.size(); j++) {
                System.out.print(String.format("%.4f ", jaccard[i][j]));
            }
            System.out.print("\n");
        }
        
        // Output most similar sentences to user
        System.out.print("\nMost similar sentences: " 
                + sentence1 
                + " and " 
                + sentence2 
                + " with Jaccard value = " 
                + String.format("%.4f", coefficient) 
                + "\n");        
    }
    
    /**
     * Builds a shingle set from a given sentence
     * 
     * @param sentence  The sentence from which to construct a shingle set
     * @return          An array containing sets of shingles
     */
    private static String[] CreateShingleSet(String sentence) {     
        // Resulting shingles will be stored in hashset
        HashSet<String> hs = new HashSet<>();
        
        for (int i = 0; i < sentence.length(); i++) {
            
            // Only construct shingles if index plus shingle size has not exceeded 
            // the sentence length.  Prevents program from throwing index out of bounds
            // error.
            if ((i + 2) <= sentence.length()) {
                hs.add(sentence.substring(i, (i + 2)));
            }
        }
        
        // Create string array from hashset...
        String[] shingles = hs.toArray(new String[hs.size()]);
        
        // ...sort it...
        Arrays.sort(shingles);
        
        // ...and return it
        return (String[]) shingles;
    }
    
    /**
     * Outputs a given shingle set to the terminal/command line
     * 
     * @param shingleSet    An array containing a given set of shingles
     * @param currentIndex  The current index from a for loop
     */
    private static void DisplayShingleSet(String[] shingleSet, int currentIndex) {
        // String will be used to display all individual shingles on a single line
        String outputSet = "";
        
        // Use enhanced for loop to iterate through array
        for (String set : shingleSet) {
            outputSet += set + " ";
        }
        
        // Display current index from for loop plus constructed line
        System.out.println(currentIndex + ": " + outputSet);
    }
    
    /**
     * Calculates a Jaccard Similarity Index from a given set of arrays.
     * Combines both intersection and union calculation into one method.
     * 
     * 
     * @param shinglesA     First shingle set used to calculate index
     * @param shinglesB     Second shingle set used to calculate index
     * @return              The Jaccard Similarity Index for the given set of arrays
     */
    private static double CalculateJaccard(String[] shinglesA, String[] shinglesB) {
        // Convert both arrays to sets
        Set shinglesASet = new HashSet(Arrays.asList(shinglesA));
        Set shinglesBSet = new HashSet(Arrays.asList(shinglesB));
        
        // Create two new Sets, one for intersection and one for union, 
        // from the first shingle set
        Set intersection = new HashSet(shinglesASet);
        Set union = new HashSet(shinglesASet);
        
        // Use Set methods to perform intersection and union operations on shingle sets
        intersection.retainAll(shinglesBSet);
        union.addAll(shinglesBSet);
        
        // Calculate the index and return result
        return intersection.size() / (double) union.size();        
    }
}
