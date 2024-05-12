/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private Button choosefile;
    
    @FXML
    private TextField filePathTextField;
    
    @FXML
    private ComboBox<String> myComboBox;
    private ObservableList<String> options = FXCollections.observableArrayList("Docx", "Pptx", "Txt File", "HTML File");

    @FXML
    private Button convertButton;
    
    @FXML
    private Label messageLabel;

    
    @FXML
    void handleOpenFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getAbsolutePath());
        }
    }
    
    @FXML
    void handleComboBoxSelection(ActionEvent event) {
        String selectedOption = myComboBox.getValue();
        File inputFile = new File(filePathTextField.getText());

        if (selectedOption != null && inputFile.exists()) {
            // method handleConvertButtonAction
        } else {
            messageLabel.setText("No input file selected");
        }
    }
    
    @FXML
    private void handleConvertButtonAction(ActionEvent event) {
        String selectedOption = myComboBox.getValue();
        File inputFile = new File(filePathTextField.getText());

        if (selectedOption != null && inputFile.exists()) {
            switch (selectedOption) {
                case "Docx":
//                    convertToDocx(inputFile);
                    break;    
                case "Txt File":
                    convertToText(inputFile);
                    break;    
                case "HTML File":
//                    convertToHTML(document, outputPath);
                    break; 
                default:
                    messageLabel.setText("Invalid option selected");
                    break;
            }
        } else {
            messageLabel.setText("No input file selected");
        }
    }
    
    private String extractTextFromPDF(File pdfFile) {
        try {
            PDDocument document = PDDocument.load(pdfFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
//    private void convertToWord(PDDocument document, String outputPath) throws IOException {
//        messageLabel.setText("...................");
//        File outputDocxFile = new File("C:\\Users\\user\\Downloads"); // Specify the output file path
//        try {
//        pdftodocx.convertPdfToDocx(inputFile, outputDocxFile);
//        messageLabel.setText("PDF to DOCX conversion successful!");
//        } catch (IOException e) {
//        messageLabel.setText("Error during PDF to DOCX conversion: " + e.getMessage());
//        e.printStackTrace();
//  }
    
    private void convertToText(File inputFile) {
        messageLabel.setText("Converting to Text...");
        String extractedText = extractTextFromPDF(inputFile);
        if (extractedText != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Text File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File outputFile = fileChooser.showSaveDialog(null);

            if (outputFile != null) {
                try (FileWriter writer = new FileWriter(outputFile)) {
                    writer.write(extractedText);
                    messageLabel.setText("Text extracted and saved to " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                    messageLabel.setText("Error saving text to TXT file.");
                }
            } else {
                messageLabel.setText("Output file not selected.");
            }
        } else {
            messageLabel.setText("Error extracting text from PDF.");
        }
    }
    
    
//    private void convertToHTML(PDDocument document, String outputPath) throws IOException {
//        PDFTextStripper stripper = new PDFTextStripper();
//        String html = "<html><body>" + stripper.getText(document) + "</body></html>";
//        FileOutputStream out = new FileOutputStream(outputPath);
//        out.write(html.getBytes());
//        out.close();
//    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        myComboBox.setItems(options);
    }    
    
    
}
