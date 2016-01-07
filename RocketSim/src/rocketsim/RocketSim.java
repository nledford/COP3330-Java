/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package rocketsim;

import blobfx.SandBox;
import blobfx.SandBoxMode;

public class RocketSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SandBox sandBox = new SandBox();
        sandBox.setSandBoxMode(SandBoxMode.FLOW);
        sandBox.setFrameRate(66);
        
        Rocket rocket = new Rocket(0,0,0);
        
        sandBox.addBlob(rocket);
        
        sandBox.run();
    }
    
}
