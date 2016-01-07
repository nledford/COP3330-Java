/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package synchro;

public interface Buffer {
    //void put(int value) throws InterruptedException;
    void put(String value) throws InterruptedException;
    String get() throws InterruptedException;
}
