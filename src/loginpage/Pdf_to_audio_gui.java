/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.gtranslate.Audio;
import com.gtranslate.Language;

public class Pdf_to_audio_gui {
    private JFrame frame;
    private JButton selectButton, convertButton,backtButton;
    //private JTextField textField;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File selectedFile;
    public String text;
    public Pdf_to_audio_gui() {
    
        frame = new JFrame("File Converter");
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());
        
  
        //textField = new JTextField();
        textArea=new JTextArea();
        frame.add(textArea, BorderLayout.CENTER);
        selectButton = new JButton("Select File");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
         {
      JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "PDF Files", "pdf");
        fileChooser.setFileFilter(filter);
        
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        
        
        PDDocument doc = PDDocument.load(selectedFile);
        PDFTextStripper reader = new PDFTextStripper();
         text = reader.getText(doc);
           //textField.setText(text);
           textArea.setText(text);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));
         textArea.setLineWrap(true);
       textArea.setWrapStyleWord(true);
       // JScrollPane scrollPane = new JScrollPane(textArea);
        //this.add(scrollPane, BorderLayout.CENTER);
        
        System.out.println(text);
         
        
        //show(text);
        }
        }
        catch(Exception ex)
        {
        }
            }
        });
        frame.add(selectButton, BorderLayout.NORTH);
        
        
        
        
        convertButton = new JButton("Speak");
        convertButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {  
          System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice("kevin16");
        voice.allocate();
       
        voice.speak(text);
        }
        });
        frame.add(convertButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Pdf_to_audio_gui();
    }
}

