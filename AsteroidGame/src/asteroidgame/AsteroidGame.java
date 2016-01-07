/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package asteroidgame;

import blobmx.BlobGUI;
import blobmx.SandBox;
import blobmx.SandBoxMode;
import java.util.Random;

public class AsteroidGame implements BlobGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AsteroidGame game = new AsteroidGame();
    }

    private final SandBox sb;
    private final Random random = new Random();
    
    public AsteroidGame(){
        sb = new SandBox();
        sb.setSandBoxMode(SandBoxMode.FLOW);
        sb.setFrameRate(66);
        sb.init(this);
    }

    @Override
    public void generate() {
        Rocket rocket = new Rocket(0,0, sb);
        sb.addBlob(rocket);
        
        for (int i = 0; i < 10; i++) {
            int ranDelx = 0;
            while (ranDelx == 0){
                ranDelx = -3 + random.nextInt(7);
            }
            int ranDely = 0;
            while (ranDely == 0){
                ranDely = -3 + random.nextInt(7);
            }
            
            double rot = .1;
            int b = random.nextInt(2);
            if (b == 0){
                rot = -rot;
            }
            
            Asteroid asteroid = new Asteroid(ranDelx, ranDely, rot);
            sb.addBlob(asteroid);
        }
        
    }

    
}
