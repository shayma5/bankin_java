/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Entities.Account;
import Services.AbonnementServices;
import Services.AccountServices;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class DashbordAbonnementController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TableView<Abonnement> table_ab;
    @FXML
    private TableColumn<Abonnement, String> column_nom;
    @FXML
    private TableColumn<Abonnement, Integer> column_prix;
    @FXML
    private Button btn_delete;
   
    
    /**
     * Initializes the controller class.
     */ @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        // TODO
    }  
    
    
     public void show(){
        ObservableList<Abonnement> listeAb = FXCollections.observableArrayList();
        AbonnementServices abs = new AbonnementServices();
        listeAb=abs.afficherAbonnements();
        column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        table_ab.setItems(listeAb);
        table_ab.refresh();
        
    }
     

    @FXML
    private void Modifier(ActionEvent event) {
    Abonnement selectedAbonnement = table_ab.getSelectionModel().getSelectedItem();
if (selectedAbonnement == null) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setContentText("Veuillez sélectionner un abonnement à modifier !");
    alert.showAndWait();
    return;
}

AbonnementServices as = new AbonnementServices();

TextInputDialog dialogNom = new TextInputDialog(selectedAbonnement.getNom());
dialogNom.setTitle("Modification d'abonnement");
dialogNom.setHeaderText("Entrez le nouveau nom de l'abonnement");
dialogNom.setContentText("Nom : ");
Optional<String> resultNom = dialogNom.showAndWait();

TextInputDialog dialogPrix = new TextInputDialog(String.valueOf(selectedAbonnement.getPrix()));
dialogPrix.setTitle("Modification d'abonnement");
dialogPrix.setHeaderText("Entrez le nouveau prix de l'abonnement");
dialogPrix.setContentText("Prix : ");
Optional<String> resultPrix = dialogPrix.showAndWait();

resultNom.ifPresent(nouveauNom -> {
    selectedAbonnement.setNom(nouveauNom);
});

resultPrix.ifPresent(nouveauPrix -> {
    try {
        int prix = Integer.parseInt(nouveauPrix);
        selectedAbonnement.setPrix(prix);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Le prix doit être un nombre !");
        alert.showAndWait();
        return;
    }
});

as.modifierAbonnement(selectedAbonnement);

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setContentText("Abonnement modifié !");
alert.showAndWait();

// Rafraîchir la table
table_ab.refresh();
    }

    @FXML
    private void supprimer(ActionEvent event) {
         Abonnement selectedAbonnement = table_ab.getSelectionModel().getSelectedItem();
        if (selectedAbonnement == null) {
       // Afficher un message d'erreur
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText("Impossible de supprimer l'abonnement ");
       alert.setContentText("Veuillez sélectionner un abonnement à supprimer !");
       alert.showAndWait();
       return;
    }
        
        AbonnementServices as = new AbonnementServices();
        as.supprimerAbonnement(selectedAbonnement);
         show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information");
        alert.setHeaderText(null);
        alert.setContentText("abonnement suprimé!");
        alert.showAndWait();
        table_ab.refresh();
}
}

    
   
    
    

