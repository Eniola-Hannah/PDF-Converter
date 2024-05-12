/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
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
                    messageLabel.setText("...................");
//                    File outputDocxFile = new File("C:\\Users\\user\\Downloads"); // Specify the output file path
//                    try {
//                        pdftodocx.convertPdfToDocx(inputFile, outputDocxFile);
//                        messageLabel.setText("PDF to DOCX conversion successful!");
//                    } catch (IOException e) {
//                        messageLabel.setText("Error during PDF to DOCX conversion: " + e.getMessage());
//                        e.printStackTrace();
//                    }
                    break;
                    
                case "Txt File":
                    messageLabel.setText("...................");
                    String extractedText = extractTextFromPDF(inputFile);
                    if (extractedText != null) {
                        // Save the extracted text to a TXT file
                        String txtFilePath = "C:\\\\Users\\\\user\\\\Downloads"; // Specify the desired output TXT file path
                        try (FileWriter writer = new FileWriter(txtFilePath)) {
                            writer.write(extractedText);
                            messageLabel.setText("Text extracted and saved to " + txtFilePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                            messageLabel.setText("Error saving text to TXT file.");
                        }
                    } else {
                        messageLabel.setText("Error extracting text from PDF.");
                    }
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
    
    
    
    void convertToDocx(File inputFile) {
 

    messageLabel.setText("Converting to .docx format: " + inputFile.getAbsolutePath()+ "\n.....");

}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        myComboBox.setItems(options);
    }    
    
    
}
