/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package synchro;

public class SynchronizedBuffer implements Buffer {
    private String buffer = "NIL";
    private boolean occupied = false;

    private int putWait = 0;
    private int getWait = 0;

    @Override
    public synchronized void put(String value) throws InterruptedException {
        while (occupied) {
            displayState("Producer wait # " + (++putWait));
            wait();
        }

        buffer = value;
        occupied = true;

        displayState("Producer writes " + buffer);
        notifyAll();
    }

    @Override
    public synchronized String get() throws InterruptedException {
        while(!occupied) {
            displayState("Consumer wait # " + (++getWait));
            wait();
        }

        occupied = false;
        displayState("Consumer reads " + buffer);
        notifyAll();
        return buffer;
    }

    private synchronized void displayState(String operation) {
        System.out.printf("%-40s%s\t\t%b%n%n", operation, buffer, occupied);
    }
}
