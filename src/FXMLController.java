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
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

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
    private ObservableList<String> options = FXCollections.observableArrayList("Txt File", "Docx", "Pptx", "HTML File");

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
                    convertToDocx(inputFile);
                    break;    
                case "Txt File":
                    convertToText(inputFile);
                    break;    
                case "HTML File":
                    convertToHTML(inputFile);
                    break; 
                case "Pptx":
                    convertToPptx(inputFile);
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
    
    
    private void convertToDocx(File inputFile) {
        messageLabel.setText("Converting to DOCX...");
        try {
            PDDocument document = PDDocument.load(inputFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save DOCX File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("DOCX Files", "*.docx")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File outputFile = fileChooser.showSaveDialog(null);

            if (outputFile != null) {
                XWPFDocument docxDocument = new XWPFDocument();
                XWPFParagraph paragraph = docxDocument.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(text);

                FileOutputStream out = new FileOutputStream(outputFile);
                docxDocument.write(out);
                out.close();

                messageLabel.setText("DOCX file saved to " + outputFile.getAbsolutePath());
            } else {
                messageLabel.setText("Output file not selected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error converting PDF to DOCX.");
        }
    }
    
    
    private void convertToHTML(File inputFile) {
        messageLabel.setText("Converting to HTML...");
        try {
            PDDocument document = PDDocument.load(inputFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String html = "<html><body>" + stripper.getText(document) + "</body></html>";
            document.close();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save HTML File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("HTML Files", "*.html")
            );
            
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File outputFile = fileChooser.showSaveDialog(null);

            if (outputFile != null) {
                try (FileWriter writer = new FileWriter(outputFile)) {
                    writer.write(html);
                    messageLabel.setText("HTML file saved to " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                    messageLabel.setText("Error saving HTML file.");
                }
            } else {
                messageLabel.setText("Output file not selected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error converting PDF to HTML.");
        }
    }
    
    
    private void convertToPptx(File inputFile) {
        messageLabel.setText("Converting to PPTX...");
        try {
            PDDocument document = PDDocument.load(inputFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PPTX File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PPTX Files", "*.pptx")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File outputFile = fileChooser.showSaveDialog(null);

            if (outputFile != null) {
                XMLSlideShow pptx = new XMLSlideShow();
                XSLFSlide slide = pptx.createSlide();
                XSLFSlideLayout layout = slide.getSlideLayout();

                // Get the title placeholder (usually at index 0)
                XSLFTextShape titleShape = slide.getPlaceholder(0);
                XSLFTextParagraph titleParagraph = titleShape.getTextParagraphs().get(0);
                XSLFTextRun titleRun = titleParagraph.getTextRuns().get(0);
                titleRun.setText("Title Text"); // Set your desired title text

                // Get the content placeholder (usually at index 1)
                XSLFTextShape contentShape = slide.getPlaceholder(1);
                XSLFTextParagraph contentParagraph = contentShape.getTextParagraphs().get(0);
                XSLFTextRun contentRun = contentParagraph.getTextRuns().get(0);
                contentRun.setText(text); // Set your extracted PDF text

                FileOutputStream out = new FileOutputStream(outputFile);
                pptx.write(out);
                out.close();

                messageLabel.setText("PPTX file saved to " + outputFile.getAbsolutePath());
            } else {
                messageLabel.setText("Output file not selected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error converting PDF to PPTX.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        myComboBox.setItems(options);
    }    
    
}
