/**
 * Name:    Nathaniel Ledford
 * Course:  COP3330 - Spring 2015
 * University of Central Florida
 */

package histo;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Histo extends javax.swing.JFrame {

    public Histo() {
        initComponents();
        this.setTitle("Letter Histograms by Nathaniel Ledford");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="GUI Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loadButton = new javax.swing.JButton();
        showButton = new javax.swing.JButton();
        idField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sourceArea = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        canvas = new java.awt.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 620));
        setMinimumSize(new java.awt.Dimension(1000, 620));
        setPreferredSize(new java.awt.Dimension(1000, 620));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 30));

        loadButton.setText("Load File");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loadButton);

        showButton.setText("Show Histo for Sentence");
        showButton.setEnabled(false);
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });
        jPanel1.add(showButton);

        idField.setColumns(5);
        idField.setEnabled(false);
        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });
        jPanel1.add(idField);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(400);

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 0));
        jPanel4.setLayout(new java.awt.BorderLayout());

        sourceArea.setColumns(20);
        sourceArea.setLineWrap(true);
        sourceArea.setRows(5);
        jScrollPane1.setViewportView(sourceArea);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane2.setLeftComponent(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        canvas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                canvasPropertyChange(evt);
            }
        });
        jPanel5.add(canvas, java.awt.BorderLayout.CENTER);

        jSplitPane2.setRightComponent(jPanel5);

        jPanel2.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>



    private int[] histo;
    private final int maxHeight = 500;

    private File file;
    private List<String> sentences;

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.showButton.setEnabled(true);
            this.idField.setEnabled(true);
            this.idField.setText("");

            file = fileChooser.getSelectedFile();
            Scanner sc = null;
            try {

                sc = new Scanner(file);
            } catch (FileNotFoundException fileNotFoundException) {
                // nom
            }

            sentences = new ArrayList<>();

            StringBuilder sb = new StringBuilder();

            int lineNumber = 1;
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                sb.append(lineNumber).append(". ").append(line).append(" \n\n");

                sentences.add(line);
                lineNumber++;
            }

            this.sourceArea.setText(sb.toString());
        }
    }

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.paint(canvas.getGraphics());
        histo = new int[26];

        int idFieldValue;
        String sentence = "";


        try {
            idFieldValue = Integer.parseInt(this.idField.getText());

            sentence = sentences.get(idFieldValue);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Text field is not an integer");
            return;
        } catch(IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Sentence index out of range");
            return;
        }

        //Make a map of all the characters you want to track.
        String indexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //Initialize an array to the size of the possible matches.
        int[] count = new int[indexes.length()];

        //Loop through the sentence looking for matches.
        for (int i = 0; i < sentence.length(); i++) {
            char ch = Character.toUpperCase(sentence.charAt(i));

            int index = indexes.indexOf(ch);

            if (index < 0)
                continue;

            histo[index]++;
        }

        scale(histo);

        paintComponent(canvas.getGraphics());

    }

    private void drawHisto(Graphics g) {
        for (int i = 0; i < histo.length; i++) {
            int x = 50 + 19 * i;
            int y = 40 + (maxHeight - histo[i]);

            g.drawRect(x,y,10,histo[i]);
        }
    }

    private void scale (int[] inp) {
        int max = findMax(inp);
        for (int i = 0; i < histo.length; i++) {
            double scaled = maxHeight * ( ((double) inp[i])/max);

            histo[i] = (int) Math.floor(scaled);
        }
    }

    private int findMax(int[] inp) {
        int max = inp[0];
        for (int i = 0; i < inp.length; i++) {
            if (inp[i] > max) {
                max = inp[i];
            }
        }
        return max;
    }

    private void drawLines(Graphics g) {
        int height = maxHeight + 40;

        g.setColor(Color.BLACK);
        g.drawLine(50,30,50,height);
        g.drawLine(50,height,550,height);
    }

    private void paintComponent(Graphics g) {
        drawLines(g);
        drawHisto(g);
    }

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {
        showButtonActionPerformed( evt );
    }

    private void canvasPropertyChange(java.beans.PropertyChangeEvent evt) {
        canvas.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
       * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
       */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Histo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Histo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Histo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Histo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Histo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private java.awt.Canvas canvas;
    private javax.swing.JTextField idField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton showButton;
    private javax.swing.JTextArea sourceArea;
    // End of variables declaration
}
