/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package blobsim;

import blob.Blob;
import blob.SandBox;
import java.util.Random;

public class BlobSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        SandBox sandbox = new SandBox();
        
        for (int i = 0; i < 20; i++) {
            // Construct blob from method
            Blob blob = BuildBlob();
            
            // Add constructed blob to sandbox
            sandbox.addBlob(blob);
        }
        
        // Run sandbox animation
        sandbox.run();
    }
    
    /**
     * Generates a random number within a given range
     * 
     * @param min   The lower boundary of the range
     * @param max   The upper boundary of the range
     * @return      An integer containing the randomly generated number
     */
    private static int GenerateRandomNumber(int min, int max) {
        Random generator = new Random();
        
        int randomNumber = generator.nextInt((max - min) + 1) + min;
        
        return randomNumber;
    }
    
    /**
     * Constructs a single blob that will be displayed on screen
     * 
     * @return  A fully constructed blob 
     */
    private static Blob BuildBlob(){
        // 30 is the minimum starting boundary
        // 370 is the max
        // Provides "cushion" for edges
        int x = GenerateRandomNumber(30,370);
        int y = GenerateRandomNumber(30,370);

        // -4 is the minimum value for motion
        // 4 is the maximum value for motion
        int idx = GenerateRandomNumber(-4, 4);
        int idy = GenerateRandomNumber(-4, 4);

        // 10 is the minimum size
        // 40 is the maximum size
        int s = GenerateRandomNumber(10, 40);

        // Create new blob object using randomly generated values
        Blob blob = new Blob(x,y,idx,idy, s);

        return blob;
    }
}
