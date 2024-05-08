/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class pdfConverts extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            //        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction((ActionEvent event) -> {
//            System.out.println("Hello World!");
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);

            Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));


            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(pdfConverts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
