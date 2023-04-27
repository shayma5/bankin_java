package GUI;

import Entities.Abonnement;
import Services.AbonnementServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void ajouter(ActionEvent event) {
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
