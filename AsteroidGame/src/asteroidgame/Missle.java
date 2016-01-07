/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidgame;

import blobmx.Blob;
import blobmx.BlobProximity;

/**
 *
 * @author nate
 */
public class Missle extends Blob implements BlobProximity {

    private final int speed = 5;
    private final int size = 5;

    public Missle(int x, int y, double theta) {
        super(0,0);
        this.setSize(size);
        this.setLoc(x,y);       
        
        int dx = (int) Math.round( speed * Math.cos(theta));
        int dy = (int) Math.round( speed * Math.sin(theta));
        
        this.setDelta(dx, dy);
    }
}
