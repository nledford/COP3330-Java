/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidsim;

import blobfx.PolyBlob;
import blobfx.BlobUtils;
import java.awt.Point;
import java.util.Random;

public class Asteroid extends PolyBlob {

    private static final Random random = new Random();
    
    // x = x-component velocity vector
    // y = y-component of velocty vector
    // r = angular rotation rate
    public Asteroid(int x, int y, double r) {
        super(-100, -100, r);        
        this.setDelta(x, y);
        
        int size = randomInt(10,30);
        this.setSize(size);
        
        int sides = randomInt(5,9);     
        int distance = randomInt(5,15);
        double region = (2 * Math.PI)/sides;
        
        double[] angle = new double[sides];
        int[] polygonX = new int[sides];
        int[] polygonY = new int[sides];
        
        for(int i = 0; i < sides; i++) {
            angle[i] = (i * region) + (Math.random() * region);
        }
        
        for (int i = 0; i < sides; i++) {
            Point p = BlobUtils.rotatePoint(distance, angle[i]);
            
            polygonX[i] = p.x;
            polygonY[i] = p.y;
        }
        
        this.setPolygon(polygonX, polygonY);
        
    }
    
    private static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
