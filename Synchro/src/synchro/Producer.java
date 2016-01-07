/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package synchro;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private final Buffer sharedLocation;
    private final SecureRandom generator = new SecureRandom();
    private final StringBuilder stringBuilder = new StringBuilder();
    private int waitTime = 0;
    private String input = "";

    public Producer(Buffer sharedLocation, String input) {
        this.sharedLocation = sharedLocation;
        this.input = input + " @@@";
    }

    @Override
    public void run() {
        String[] splitInput = input.split(" ");

        for (String aSplitInput : splitInput) {
            int wait;
            try {
                wait = generator.nextInt(3000);
                Thread.sleep(wait);
                sharedLocation.put(aSplitInput);
                stringBuilder.append(aSplitInput + " ");
                System.out.println("\t\t" + stringBuilder.toString());

                waitTime += wait;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nProducer summary: " + stringBuilder.toString());
        System.out.println("Total wait time: " + waitTime + " milliseconds");
    }
}
