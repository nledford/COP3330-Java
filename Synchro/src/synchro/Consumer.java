/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package synchro;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    private final Buffer sharedLocation;
    private final SecureRandom generator = new SecureRandom();
    private final StringBuilder stringBuilder = new StringBuilder();
    private int waitTime = 0;
    private String input = "";

    public Consumer(Buffer sharedLocation, String input){

        this.sharedLocation = sharedLocation;
        this.input = input + " @@@";
    }

    @Override
    public void run() {
        String[] splitInput = input.split(" ");

        for (int i = 0; i < splitInput.length; i++) {
            int wait;
            try {
                wait = generator.nextInt(3000);
                Thread.sleep(wait);
                String n = sharedLocation.get();
                stringBuilder.append(n + " ");
                System.out.println("\t\t\t\t" + stringBuilder.toString());

                waitTime += wait;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

        System.out.println("\nConsumer summary: " + stringBuilder.toString());
        System.out.println("Total wait time: " + waitTime + " milliseconds");
    }
}
