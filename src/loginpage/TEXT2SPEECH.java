/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
/**
 *
 * @author hp123
 */
public class TEXT2SPEECH extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
     Label ltext, lresult, caption;
  TextField txtfd, txtfd2;
  Button b1,b2,b3,b4,b5;
  
    TEXT2SPEECH()
    {
    caption = new Label("AUDIO BOOK");
    add(caption);
    caption.setBounds(130, 40, 400, 100);
    caption.setForeground(new java.awt.Color(51, 51, 255));
    caption.setFont(new java.awt.Font("Times New Roman", 1, 50));

    
    
    b1 = new Button("Text To Audio");
    add(b1);
    b1.setBackground(new java.awt.Color(0, 0, 204));
    b1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    b1.setBounds(75, 170, 150, 30);
    b1.setForeground(new java.awt.Color(255, 255, 255));
    
    b2 = new Button("PDF To AUDIO");
    b2.setBackground(new java.awt.Color(0, 0, 204));
    b2.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    b2.setBounds(75, 170, 150, 30);
    b2.setForeground(new java.awt.Color(255, 255, 255));
    add(b2);
    b2.setBounds(360, 170, 150, 30);
    
    
    b4 = new Button("Speech To Text");
    b4.setBackground(new java.awt.Color(0, 0, 204));
    b4.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    b4.setBounds(75, 170, 150, 30);
    b4.setForeground(new java.awt.Color(255, 255, 255));
    add(b4);
    b4.setBounds(75, 320, 150, 30);
    
    
    b3 = new Button("Audio To Pdf ");
    b3.setBackground(new java.awt.Color(0, 0, 204));
    b3.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    b3.setBounds(75, 170, 150, 30);
    b3.setForeground(new java.awt.Color(255, 255, 255));
    add(b3);
    b3.setBounds(360, 320, 150, 30);
    
    b5 = new Button("Exit");
    b5.setBackground(new java.awt.Color(0, 0, 204));
    b5.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    b5.setBounds(75, 170, 150, 30);
    b5.setForeground(new java.awt.Color(255, 255, 255));
    add(b5);
    b5.setBounds(230, 420, 150, 30);
   
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Display Text");
    
    setSize(600, 600);
    //setBackground(Color.RED);
    setBackground(new java.awt.Color(255, 255, 255));
    setLayout(null);
    setLocationRelativeTo(null);
    setVisible(true);
    
  }
   @Override
  public void actionPerformed(ActionEvent e) {
      //AudioConverter  a = new AudioConverter();
      if (e.getSource() == b1) {
            new AudioConverter();
        } else if(e.getSource() == b2) {
            //new PdfToAudioConverter();
            new Pdf_to_audio_gui();
        } else if(e.getSource() == b3) {
            try {
              //new SpeechToPDF();
              String url = "F:\\java\\LoginPage\\src\\loginpage\\newindex.html";
              File htmlFile = new File(url);
              Desktop.getDesktop().browse(htmlFile.toURI());
          } catch (IOException ex) {
              Logger.getLogger(TEXT2SPEECH.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      else if(e.getSource() == b4) {
          try {
              //new SpeechToPDF();
              String url = "F:\\java\\LoginPage\\src\\loginpage\\index.html";
              File htmlFile = new File(url);
              Desktop.getDesktop().browse(htmlFile.toURI());
          } catch (IOException ex) {
              Logger.getLogger(TEXT2SPEECH.class.getName()).log(Level.SEVERE, null, ex);
          }

             
        }else if(e.getSource() == b5) {
           System.exit(0);
        }
      
    }
    public static void main(String[] args)
    {
       TEXT2SPEECH v = new TEXT2SPEECH();
        
    }
}
    


