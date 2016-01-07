/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidgame;

import blobmx.BlobUtils;
import blobmx.PolyBlob;
import java.awt.Point;
import java.util.Random;

public class Asteroid extends PolyBlob {
    private static final Random random = new Random();

    public Asteroid(int idx, int jdx, double rot) {
        super(-100, -100, rot);
        setDelta(idx, jdx);
        
        int numSides = 5 + random.nextInt(5);
        
        int[] vertex = new int[numSides];
        for (int i = 0; i < numSides; i++){
            vertex[i] = 5 + random.nextInt(11);
        }
        
        double region = (2 * Math.PI)/numSides;
        double[] angle = new double[numSides];
        for (int i = 0; i < numSides; i++){
            angle[i] = (i * region) + (Math.random() * region);
        }
        
        int[] x = new int [numSides];
        int[] y = new int [numSides];
        for (int i = 0; i < numSides; i++) {
            Point p = BlobUtils.rotatePoint(vertex[i], angle[i]);
            x[i] = p.x;
            y[i] = p.y;
        }
        
        setPolygon(x,y);
    }
    
}
