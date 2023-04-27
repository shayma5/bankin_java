/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pret;
import Services.PretServices;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import javafx.scene.control.Alert;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterpretController implements Initializable {
     String poste,raison;
    double montant,revenu,taux;
    Date debut;
    int duree;
  
    public static boolean isDateValid(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(false);
    try {
        dateFormat.parse(date.toString());
    } catch (ParseException e) {
        return false;
    }
    return true;
}
		
		
                

    @FXML
    private Label nom;
    @FXML
    private Button btnvalider;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField txtmontant;
    @FXML
    private TextField txtraison;
    @FXML
    private TextField txtposte;
    @FXML
    private TextField txtdebut;
    @FXML
    private TextField txtrevenu;
    @FXML
    private TextField txtduree;
    @FXML
    private TextField txt_taux;

    /**
     * Initializes the controller class.
     */
       

    @FXML
    private void valider(ActionEvent event) throws ParseException {
        boolean test = true;
        

    
    if (txtmontant.getText().isEmpty() || txtraison.getText().isEmpty() || txtposte.getText().isEmpty()
				|| txtdebut.getText().isEmpty() || txtrevenu.getText().isEmpty() || txtrevenu.getText().isEmpty()
				|| txt_taux.getText().isEmpty()) {
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
    if (txtposte.getText().length() < 4) {
     test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le poste doit avoir au moins 4 caractères !");
    alert.showAndWait();
    return;
    }
     if (txtraison.getText().length() < 5) {
     test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("La raison doit avoir au moins 5 caractères !");
    alert.showAndWait();
    return;
    }
    
     /*if (!isDateValid(debut, "dd/MM/yyyy")) {
     test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le champ début doit être une date au format dd/MM/yyyy !");
    alert.showAndWait();
    return;
    }*/
     
    
    
    
    
    
    
    if (!txtmontant.getText().matches("^\\d+(\\.\\d+)?$")) {
        test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le montant doit être un nombre décimal positif !");
    alert.showAndWait();
    //test=false;
    return;
    }
    if (!txtrevenu.getText().matches("^\\d+(\\.\\d+)?$")) {
        test=false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le revenu doit être un nombre décimal positif !");
        alert.showAndWait();
        return;
    }
    

    
    double revenu = Double.parseDouble(txtrevenu.getText());
    if (revenu < 500) {
        test=false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le revenu doit être supérieur ou égal à 500 !");
        alert.showAndWait();
        return;
    }
      double taux = Double.parseDouble(txt_taux.getText());
    if(taux < 0 || taux > 100){
        test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le taux doit être compris entre 0 et 100% !");
    alert.showAndWait();
    return;
}
    /* int duree = Integer.parseInt(txtduree.getText());
    if (duree > 12) {
        test=false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La duree doit être supérieur 12 mois !");
        alert.showAndWait();
        return;
    }
    */
    
    
   if(test==true){ 
    montant = Double.parseDouble(txtmontant.getText());
    raison = txtraison.getText();
    poste = txtposte.getText();
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    debut = format.parse(txtdebut.getText()); 
    revenu = Double.parseDouble(txtrevenu.getText());
    duree = Integer.parseInt(txtduree.getText());
    taux = Double.parseDouble(txt_taux.getText());
    Pret p = new Pret();
    p.setMontant(montant);
    p.setRaison(raison);
    p.setPoste(poste);
    p.setDebut_travail(new java.sql.Date(debut.getTime()));
    p.setRevenu(revenu);
    p.setDurée(duree);
    p.setTaux(taux);
    PretServices ps = new PretServices();
    ps.ajouterPret(p);
    
    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
    alert2.setTitle("Information Dialog");
    alert2.setHeaderText(null);
    alert2.setContentText("Prêt ajouté avec succès !");
    alert2.showAndWait();  
   }
    
   
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
   
}
     
    

