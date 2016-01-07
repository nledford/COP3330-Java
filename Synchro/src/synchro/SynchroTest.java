/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package synchro;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchroTest {
    private static File selectedFile;
    StringBuilder stringBuilder = new StringBuilder();
    private static Scanner scanner;
    private static String input = "";

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            scanner = new Scanner(new FileInputStream(selectedFile));

            input = scanner.nextLine();

            JOptionPane.showMessageDialog(null, "Input Sentence:\n" + input);
        } else {
            JOptionPane.showMessageDialog(null, "Session ended.");
            System.exit(0);
        }

        System.out.println("COP3330 Program 10 output for Nathaniel Ledford\n");

        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t------");


        ExecutorService executorService = Executors.newCachedThreadPool();
        Buffer sharedLocation = new SynchronizedBuffer();

        executorService.execute(new Producer(sharedLocation, input));
        executorService.execute(new Consumer(sharedLocation, input));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
