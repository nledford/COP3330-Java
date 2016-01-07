/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidgame;

import blobmx.BlobAction;
import blobmx.BlobProximity;
import blobmx.BlobUtils;
import blobmx.PolyBlob;
import blobmx.SandBox;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Rocket extends PolyBlob implements BlobAction, BlobProximity {

    private final int[] polygonx = new int[]{10, -10, -5, -10};
    private final int[] polygony = new int[]{0, -7, 0, 7};
    
    private final int[] referenceX;
    private final int[] referenceY;
    
    private double angle = 0.0;
    private final double delta = 0.15;
    private final double speed = 5.0;
    
    private final double twoTimesPi = 2 * Math.PI;
    
    private final SandBox sandBox;
    
    public Rocket(int x, int y, SandBox sb) {
        
        super(300, 300, 0);
        
        referenceX = polygonx;
        referenceY = polygony;
        sandBox = sb;
        
        this.setPolygon(polygonx, polygony);
    }

    @Override
    public void keyAction(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        
        switch (keyCode){
            case KeyEvent.VK_UP:
                MoveForward();
                break;
            case KeyEvent.VK_LEFT:
                RotateLeft();
                break;
            case KeyEvent.VK_RIGHT:
                RotateRight();
                break;
            case KeyEvent.VK_SPACE:
                Launch(sandBox);
                BlobUtils.playSound();
                break;
        }
    }
    
    private void RotateRight(){
        this.angle += delta;
        
        if (angle > twoTimesPi){
            angle -= twoTimesPi;
        }
        
        turn();
    }

    private void RotateLeft(){
        this.angle -= delta;

        if (angle < twoTimesPi){
            angle += twoTimesPi;
        }

        turn();
    }
    
    private void turn(){        
        for (int i = 0; i < referenceX.length; i++) {
            Point p = BlobUtils.rotatePoint(referenceX[i], referenceY[i], angle);

            polygonx[i] = p.x;
            polygony[i] = p.y;
        }
    }
    
    public void MoveForward() {
        Point p = this.getLoc();
        
        int xloc = p.x;
        int yloc = p.y;
        
        xloc = xloc + (int) Math.round(speed * Math.cos(angle));
        yloc = yloc + (int) Math.round(speed * Math.sin(angle));
        
        this.setLoc(xloc, yloc);
        
    }
    
    public void Launch(SandBox sb) {
        int boundingRadius = this.getSize()/2;
       
        Point currentLocation = this.getLoc();
        
        Point launchPoint = BlobUtils.rotatePoint(boundingRadius + 5, angle);
        
        int distx = currentLocation.x + launchPoint.x;
        int disty = currentLocation.y + launchPoint.y;
        

        Missle missle = new Missle(distx, disty, angle);
        
        sb.addBlob(missle);
    }
}
