/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
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
            switch (selectedOption) {
                case "Docx":
                    convertToDocx(inputFile);
                    break;
//                case "Pptx":
//                    convertToPptx(inputFile);
//                    break;
//                case "Txt File":
//                    convertToTxt(inputFile);
//                    break;
//                case "HTML File":
//                    convertToHtml(inputFile);
//                    break;
                default:
                    messageLabel.setText("Invalid option selected");
                    break;
            }
        } else {
            messageLabel.setText("No input file selected");
        }
    }
    
    void convertToDocx(File inputFile) {
    // Perform the conversion logic to convert the input file to a .docx format
    // You can use external libraries or write your own conversion code here

    messageLabel.setText("Converting to .docx format: " + inputFile.getAbsolutePath()+ "\n.....");

    // Save the converted file or perform any other necessary actions
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        myComboBox.setItems(options);
    }    
    
    
}
