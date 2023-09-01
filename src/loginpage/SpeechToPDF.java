/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpeechToPDF extends JFrame {

    private JLabel title;
    private JButton speakButton, pauseButton, stopButton, convertButton;
    private JTextArea textArea;

    public SpeechToPDF() {
        super("Speech to PDF Converter");

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create the GUI components
        title = new JLabel("Speech to PDF Converter");
        speakButton = new JButton("Speak");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        convertButton = new JButton("Convert");
        textArea = new JTextArea(10, 30);

        // Add the components to the JFrame
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(speakButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(convertButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set the size of the JFrame
        setSize(400, 300);

        // Set the default close operation of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the JFrame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create a new instance of the SpeechToPDFGUI class
        SpeechToPDF gui = new SpeechToPDF();
    }
}


