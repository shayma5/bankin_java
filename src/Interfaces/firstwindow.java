/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author montassar
 */
public class firstwindow extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("homeinterface.fxml"));
            
            
            Scene scene = new Scene(root);
            
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
