/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Etatpret;
import Entities.Pret;
import Services.EtatpretServices;
import Services.PretServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
 * @author HP
 */
public class AdminController implements Initializable {

    @FXML
    private TableColumn<Pret, Double> montantcolumn;
    @FXML
    private TableColumn<Pret, String> raisoncolumn;
    @FXML
    private TableColumn<Pret, String> postecolumn;
    @FXML
    private TableColumn<Pret, Date> debutcolumn;
    @FXML
    private TableColumn<Pret, Double> revenucolumn;
    @FXML
    private TableColumn<Pret, Integer> dureecolumn;
    @FXML
    private TableColumn<Pret,Double> tauxcolumn;
    @FXML
    private TableView<Pret> pret_table;
    @FXML
    private TableColumn<Pret, String> etat;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private AnchorPane home_form;
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
    private Button btnmodifier;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnmodifier1;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        // TODO
    }  
    
 public void show(){    
    ObservableList<Pret> listeP = FXCollections.observableArrayList();
        PretServices ps = new PretServices();
        listeP=ps.afficherPret();
        montantcolumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        raisoncolumn.setCellValueFactory(new PropertyValueFactory<>("raison"));
        postecolumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        debutcolumn.setCellValueFactory(new PropertyValueFactory<>("debut_travail"));
        revenucolumn.setCellValueFactory(new PropertyValueFactory<>("revenu"));
        dureecolumn.setCellValueFactory(new PropertyValueFactory<>("durée"));
        tauxcolumn.setCellValueFactory(new PropertyValueFactory<>("taux"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        pret_table.setItems(listeP);
        pret_table.refresh();
    
}

    @FXML
    private void modifierEtat(ActionEvent event) {
        
    Pret selectedPret = pret_table.getSelectionModel().getSelectedItem();
    if (selectedPret == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner un etat à modifier !");
        alert.showAndWait();
        return;
    }
    //PretServices ps = new PretServices();
    EtatpretServices es = new EtatpretServices();
    Etatpret etatCourant = es.getEtatPretByPretId(selectedPret.getId());
    
    TextInputDialog dialog = new TextInputDialog(etatCourant.getEtat());
    dialog.setTitle("Modification d'état de prêt");
    dialog.setHeaderText("Entrez le nouvel état du prêt");
    dialog.setContentText("Etat:");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(newEtat -> {
        etatCourant.setEtat(newEtat);
        es.modifierEtat(etatCourant);
        //ps.afficherPret();
       //pret_table.refresh();
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Etat modifié !");
        alert.showAndWait();
        show();
    });
   
}

    @FXML
    private void SupprimerPret(ActionEvent event) {
          Pret selectedLN =  pret_table.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
       // Afficher un message d'erreur
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText("Impossible de supprimer le pret ");
       alert.setContentText("Veuillez sélectionner un pret à supprimer !");
       alert.showAndWait();
       return;
   }
       
      //System.out.println(selectedLN.getId_b());
      PretServices ps = new PretServices();
       ps.supprimePret(selectedLN);
       show();
        
        
    }

    @FXML
    private void Stat(ActionEvent event) {
        try {
            // Charger la nouvelle scène FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chart.fxml"));
            Parent root = loader.load();
            // Définir la nouvelle racine de la scène actuelle
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        
        

    }
