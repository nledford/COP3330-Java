/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package bmi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


public class Bmi {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        
        try {            
            int numberOfPatients = Integer.parseInt(br.readLine());

            System.out.println("BMI Report:");

            for (int i = 0; i < numberOfPatients; i++) {
                String line = br.readLine();
                
                String[] newPatient = line.split("\\s+");
                Patient patient = new Patient();
                
                for (int j = 2; j < newPatient.length; j++) {
                    if (patient.Name == null) {
                        patient.Name = newPatient[j];
                    } else {
                        patient.Name += newPatient[j];
                    }
                    patient.Name += " ";
                }
                
                patient.Height = Integer.parseInt(newPatient[0]);
                patient.Weight = Integer.parseInt(newPatient[1]);
                
                // Use weight and height to calculate BMI
                BmiResult bmiResult = CalculateBmi(patient.Height, patient.Weight);
                
                DecimalFormat df = new DecimalFormat("#.0");
                
                System.out.print(patient.Name + "\t");
                System.out.print(patient.Height + " inches\t");
                System.out.print(patient.Weight + " pounds\t");
                System.out.print("BMI: " + df.format(bmiResult.score) + "\t");
                System.out.print(bmiResult.classification + "\n");
            }           
        } finally {
            br.close();
        }
    }
    
    /**
     * Calculates the BMI based on a given weight and height
     * 
     * @param height    A patient's height
     * @param weight    A patient's weight
     * @return          A BmiResult object which contains calculated BMI and classification
     */
    public static BmiResult CalculateBmi(double height, double weight) {
        BmiResult bmiResult = new BmiResult();
        
        double bmiScore = (weight * 703)/(height * height);
        String classification = null;
        
        if (bmiScore < 18.5) {
            classification = "Underweight";
        } else if (bmiScore >= 18.5 && bmiScore < 25) {
            classification = "Normal";
        } else if (bmiScore >= 25 && bmiScore < 30) {
            classification = "Overweight";
        } else if (bmiScore >= 30) {
            classification = "Obese";
        } else {
            classification = "undefined";
        }
        
        bmiResult.score = bmiScore;
        bmiResult.classification = classification;
        
        return bmiResult;
    }
}
