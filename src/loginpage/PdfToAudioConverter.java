/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument.*;
import org.apache.pdfbox.text.PDFTextStripper.*;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocument.*;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToAudioConverter extends JFrame {
    
    public PdfToAudioConverter()
    {
        //public static void main(String[] args) {
        
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "PDF Files", "pdf");
        fileChooser.setFileFilter(filter);
        
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Read the contents of the PDF file
            String pdfContent = readPdf(selectedFile);
            
            // Convert the PDF content to an audio file
            String audioFilePath = convertToAudio(pdfContent);
            
            // Play the audio file
            playAudio(audioFilePath);
        }
    }

    public static String readPdf(File file) {
        try  {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return text;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("errror2");
            return "";
        }
    }
    
    public static String convertToAudio(String text) {
        try {
            Audio audio = Audio.getInstance();
            InputStream stream = audio.getAudio(text, Language.ENGLISH);
            File file = new File("output.mp3");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = stream.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            stream.close();
            return file.getAbsolutePath();
        } catch (Exception ex) {
           ex.printStackTrace();
            //JOptionPane.showMessageDialog(this, "An error occurred while saving the file.");
            System.out.println("errror");
            return "";
        }
    }
    
    public static void playAudio(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("errror3");
        }
    }
}
