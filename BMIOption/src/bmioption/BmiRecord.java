/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */
package bmioption;

import java.text.DecimalFormat;

/**
 *
 * @author nate
 */
public class BmiRecord {
    private String name;
    private int weight;
    private int height;
    private static final DecimalFormat df = new DecimalFormat("0.0");
    
    public BmiRecord(String name, int height, int weight){
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
    
    private double bmiValue(){
        return (weight * 703.0)/(height * height);
    }
    
    private String bmiStatus(){
        double bmi = bmiValue();
        
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        }
        return "Obese";
    }
    
    public String report(){
        return (name + "\t" + height + " inches\t" + weight + " pounds\tBMI: " + df.format(bmiValue()) + "\t" + bmiStatus());
    }
}
