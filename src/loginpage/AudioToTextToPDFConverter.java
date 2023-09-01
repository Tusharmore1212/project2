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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AudioToTextToPDFConverter {
    public AudioToTextToPDFConverter() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an audio file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3", "wav"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File audioFile = fileChooser.getSelectedFile();
            String audioFilePath = audioFile.getAbsolutePath();
            String audioFileName = audioFile.getName();
            String textFilePath = audioFilePath.substring(0, audioFilePath.lastIndexOf('.')) + ".txt";
            String pdfFilePath = audioFilePath.substring(0, audioFilePath.lastIndexOf('.')) + ".pdf";
            try {
                ProcessBuilder pb = new ProcessBuilder("python", "audio_to_text.py", audioFilePath, textFilePath);
                pb.redirectErrorStream(true);
                Process p = pb.start();
                Scanner scanner = new Scanner(p.getInputStream());
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
                    document.open();
                    scanner = new Scanner(new File(textFilePath));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        Paragraph paragraph = new Paragraph(line);
                        document.add(paragraph);
                    }
                    scanner.close();
                    System.out.println("PDF file created successfully.");
                } catch (DocumentException | FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                File textFile = new File(textFilePath);
                if (textFile.exists()) {
                    textFile.delete();
                }
            }
        }
    }
}