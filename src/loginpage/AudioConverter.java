/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author hp123
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.lang.Object;
import javax.sound.sampled.*;
import javax.sound.sampled.Mixer.Info;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.sound.sampled.AudioFileFormat;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.JComboBox;

public class AudioConverter extends JFrame implements ActionListener, ChangeListener {

    private static final long serialVersionUID = 1L;
    private JTextArea inputTextArea;
    private JProgressBar progressBar;
    private JButton convertButton;
    private JButton playButton;
    private JButton saveButton;
    private JSlider speedSlider;
    private JComboBox<Voice> voiceComboBox;
    private TargetDataLine targetLine;
    private AudioFormat audioFormat;
    private AudioInputStream audioInputStream;
    private File audioFile;

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new AudioConverter();
    }

    public AudioConverter() {
        // Initialize Swing components
        inputTextArea = new JTextArea(20, 30);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setEnabled(false);
        saveButton = new JButton("BACK");
        saveButton.addActionListener(this);
        saveButton.setEnabled(false);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(convertButton);
        buttonPanel.add(playButton);
        buttonPanel.add(saveButton);

        speedSlider = new JSlider(0, 20, 10);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        voiceComboBox = new JComboBox<>(voices);
        voiceComboBox.setSelectedIndex(0);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Add Swing components to frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
               add(buttonPanel, BorderLayout.SOUTH);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        JPanel speedPanel = new JPanel(new FlowLayout());
        speedPanel.add(new JLabel("Speed:"));
        speedPanel.add(speedSlider);

        JPanel voicePanel = new JPanel(new FlowLayout());
        voicePanel.add(new JLabel("Voice:"));
        voicePanel.add(voiceComboBox);

        controlPanel.add(speedPanel);
        controlPanel.add(voicePanel);
        controlPanel.add(progressBar);

        add(controlPanel, BorderLayout.NORTH);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Audio Converter");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == convertButton) {
            String text = inputTextArea.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter some text to convert.");
                return;
            }
            new AudioConversionWorker(text).execute();
        } else if (source == playButton)
        {
            String s = inputTextArea.getText();
            try {
            // Set property as Kevin Dictionary
            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");
  
            // Register Engine
            Central.registerEngineCentral(
                "com.sun.speech.freetts"
                + ".jsapi.FreeTTSEngineCentral");
  
            // Create a Synthesizer
            Synthesizer synthesizer
                = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));
  
            // Allocate synthesizer
            synthesizer.allocate();
  
            // Resume Synthesizer
            synthesizer.resume();
  
            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                s, null);
            //synthesizer.waitEngineState(
                //Synthesizer.QUEUE_EMPTY);
  
            // Deallocate the Synthesizer.
            //synthesizer.deallocate();
        }
  
        catch (Exception ek) {
            ek.printStackTrace();
        }
    
        } else if (source == saveButton) {
           /* JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File outputFile = fileChooser.getSelectedFile();
                try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                    outputStream.write(audioInputStream.readAllBytes());
                    JOptionPane.showMessageDialog(this, "File saved successfully.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An error occurred while saving the file.");
                }
            }*/
           dispose();
           new TEXT2SPEECH();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == speedSlider) {
            float speed = (float) speedSlider.getValue() / 10.0f;
            Voice voice = (Voice) voiceComboBox.getSelectedItem();
            voice.setRate(speed);
        }
    }

    private class AudioConversionWorker extends SwingWorker<Void, Void> {

        private String text;

        public AudioConversionWorker(String text) {
            this.text = text;
        }

        @Override
        protected Void doInBackground() throws Exception {
            // Set up FreeTTS voice
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = (Voice) voiceComboBox.getSelectedItem();
            voice.allocate();
            
            // Convert text to audio
            audioFormat = new AudioFormat(16000, 16, 1, true, false);
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
            for (Info mixerInfo : mixerInfos) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                TargetDataLine line = null;
                try {
                    line = (TargetDataLine) mixer.getLine(dataLineInfo);
                    line.open(audioFormat);
                    line.start();
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = line.read(buffer, 0, buffer.length)) != -1) {
                        voice.getAudioPlayer().write(buffer, 0, bytesRead);
                    }
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } finally {
                    if (line != null) {
                        line.stop();
                        line.close();
                    }
                }
            }
            voice.deallocate();
            return null;
        }

        @Override
        protected void done() {
            progressBar.setIndeterminate(false);
            progressBar.setValue(progressBar.getMaximum());
            playButton.setEnabled(true);
            saveButton.setEnabled(true);
        }

        @Override
        protected void process(List<Void> chunks) {
            progressBar.setIndeterminate(true);
        }
    }

    private class AudioPlaybackWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            //AudioInputStream audioInputStream = new AudioInputStream( (TargetDataLine) new ByteArrayInputStream(voice.getAudioPlayer().getOuput().toArray()));
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("output.wav"));
            return null;
        }

        @Override
        protected void done() {
            try {
             //   AudioInputStream audioInputStream = new AudioInputStream((TargetDataLine) new ByteArrayInputStream(voice.getAudioPlayer().getOuput().toArray()));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }
}