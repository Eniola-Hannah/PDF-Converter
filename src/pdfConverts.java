/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
    public void start(Stage primaryStage) throws IOException {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        FXMLLoader fxmlLoader = new FXMLLoader(NewFXMain.class.getResource("FXML.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        
        primaryStage.setTitle("GROUP 4 - PDF CONVERTER!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
