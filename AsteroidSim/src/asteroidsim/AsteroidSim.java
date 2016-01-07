/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidsim;

import blobfx.SandBox;
import blobfx.SandBoxMode;
import java.util.Random;

/**
 *
 * @author nate
 */
public class AsteroidSim {

    private static final Random random = new Random();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SandBox sandbox = new SandBox();
        sandbox.setSandBoxMode(SandBoxMode.FLOW);
        sandbox.setFrameRate(66);
        
        for (int i = 0; i < 20; i++){
            int xVelocity = randomInt(-3,3);
            int yVelocity = randomInt(-3,3);
            double rotation = random.nextInt() % 2 == 0 ? .1 : -.1;
            
            Asteroid asteroid = new Asteroid(xVelocity, yVelocity, rotation);
            sandbox.addBlob(asteroid);
        }
        
        sandbox.run();
        
    }
    
    private static int randomInt(int min, int max) {
        int result = 0;
        
        while (result <= 0) {
            result = random.nextInt((max - min) + 1) + min;
        }
        
        return result;
    }
}
