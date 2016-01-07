/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package bmioption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BMIOption {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File selectedFile = null;
        
        
        StringBuilder sb = new StringBuilder();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        Boolean optionYes = true;
        
        while(optionYes) {
            // reset string builder
            sb.setLength(0);
            
            int result = fileChooser.showOpenDialog(null);
        
            if (result == JFileChooser.APPROVE_OPTION){
                selectedFile = fileChooser.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(null, "Session ended.");
                optionYes = false;
                System.exit(0);
            }

            Scanner scanner = new Scanner(new FileInputStream(selectedFile));
            int lim = scanner.nextInt();

            for (int i = 0; i < lim; i++){
                int height = scanner.nextInt();
                int weight = scanner.nextInt();
                String name = scanner.nextLine();

                BmiRecord rec = new BmiRecord(name, height, weight);

                sb.append(rec.report());
                sb.append("\n");
            }

            sb.append("\nWould you like to try another file?");

            int response = JOptionPane.showConfirmDialog(null, sb.toString(),"BMI Calculations by Nathaniel Ledford", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION){
                optionYes = true;
            } else {
                optionYes = false;
                JOptionPane.showMessageDialog(null, "Session ended.");
                System.exit(0);
            }
        }
        
    }
    
}
