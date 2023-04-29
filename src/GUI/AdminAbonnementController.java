package GUI;

import Entities.Abonnement;
import Services.AbonnementServices;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AdminAbonnementController {

   

    @FXML
    private Button btn_ajouter;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane main_form;
    
    @FXML
    private TextField txtnomA;

    @FXML
    private TextField txtnprix;
    @FXML
    private Label username;
    @FXML
    private AnchorPane addEmployee_form;
    @FXML
    private TextField addEmployee_employeeID;
    @FXML
    private TextField addEmployee_firstName;
    @FXML
    private TextField addEmployee_lastName;
    @FXML
    private TextField addEmployee_phoneNum;
    @FXML
    private ImageView addEmployee_image;
    @FXML
    private AnchorPane salary_form;
    @FXML
    private TextField salary_employeeID;
    @FXML
    private Label salary_firstName;
    @FXML
    private Label salary_lastName;
    @FXML
    private Label salary_position;
    @FXML
    private TextField salary_salary;
    @FXML
    private Button retourner;

   
    @FXML
    void ajouter(ActionEvent event) {
        boolean test = true;
        
        if (txtnomA.getText().isEmpty() || txtnprix.getText().isEmpty() ) {
			{
				test=false;
				Alert alert1 = new Alert(Alert.AlertType.WARNING);
				alert1.setTitle("oops");
				alert1.setHeaderText(null);
				alert1.setContentText("remplir tous les champs");
				alert1.showAndWait();
				return;

			}
		}
        if (txtnomA.getText().length() < 4) {
     test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le nom de l'abonnement doit avoir au moins 4 caractères !");
    alert.showAndWait();
    return;
    }
        if (!txtnprix.getText().matches("^\\d+(\\.\\d+)?$")) {
        test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le prix doit être un nombre décimal positif !");
    alert.showAndWait();
    //test=false;
    return;
    }
        if(test==true){ 
    int prix = Integer.parseInt(txtnprix.getText());
         String NomAbonnement = txtnomA.getText();
         Abonnement nouvelleAbonnement = new Abonnement();
         nouvelleAbonnement.setNom(NomAbonnement);
         nouvelleAbonnement.setPrix(prix);
         AbonnementServices abs = new AbonnementServices();
         abs.ajouterAbonnement(nouvelleAbonnement);
         
         
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
	alert2.setTitle("Information Dialog");
	alert2.setHeaderText(null);
        alert2.setContentText("A new account was inserted successfully!");
    
   
   }
         
        
    }

    @FXML
    private void retourner(ActionEvent event) {
          try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
