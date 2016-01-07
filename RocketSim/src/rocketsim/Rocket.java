/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package rocketsim;

import blobfx.BlobAction;
import blobfx.BlobUtils;
import blobfx.PolyBlob;

import java.awt.*;

import java.awt.event.KeyEvent;

public class Rocket extends PolyBlob implements BlobAction {

    private final int[] polygonx = new int[]{10, -10, -5, -10};
    private final int[] polygony = new int[]{0, -7, 0, 7};
    
    private int[] referenceX;
    private int[] referenceY;
    
    private double angle = 0.0;
    private final double delta = 0.15;
    private final double speed = 5.0;
    
    private double twoTimesPi = 2 * Math.PI;
    
    public Rocket(int x, int y, double r) {
        
        super(300, 300, 0);
        
        referenceX = polygonx;
        referenceY = polygony;

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
}
