/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    void handleOpenFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Process the selected file
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No need to set action for choosefile/button when there's already an handler for it up
//        choosefile.setOnAction(event -> {
//            System.out.println("Button clicked!");
//            // Additional button click handling code...
//        });
    }    
    
    
}
