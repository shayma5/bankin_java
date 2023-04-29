
import Entities.Abonnement;
import Services.AbonnementServices;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bankinfx extends Application {

    public static Stage stg;

    @Override
    public void start(Stage primaryStage) {
        try {
            Bankinfx.stg = primaryStage;
           
         // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AccountCrud.fxml"));
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Admin.fxml"));
          //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminAbonnement.fxml"));
         //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/DashbordAbonnement.fxml"));
              //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/chart.fxml"));

            
            
            


            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Bievennue");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Bankinfx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
        
      //  AbonnementServices abs = new AbonnementServices();
    //    abs.afficherAbonnements();
        

    }

}
