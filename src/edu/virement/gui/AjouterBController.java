/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virement.gui;

import edu.virement.entities.beneficiaire;
import edu.virement.services.BeneficiaireService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AjouterBController implements Initializable {

    @FXML
    private Label prenom_benef;
    @FXML
    private Label nom_benef;
    @FXML
    private TextField id_benef;
    @FXML
    private TextField titre;
    @FXML
    private Button ba;
    @FXML
    private Label email_benef;
    @FXML
    private TextField rib_benef;
    @FXML
    private ListView<beneficiaire> listb;
    @FXML
    private Button bm;
    @FXML
    private Button bs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BeneficiaireService br = new BeneficiaireService();
        ObservableList<beneficiaire> listt = FXCollections.observableList(br.afficher());
        listb.setItems(listt);

    }

    @FXML
    private void saveBen(ActionEvent event) {
        StringBuilder errors = new StringBuilder();

        if (rib_benef.getText().length() < 3 || nom_benef.getText().length() < 3 || prenom_benef.getText().length() < 3) {

            if (rib_benef.getText().length() < 3) {
                errors.append("Votre RIB doit comporter 3 chiffres \n");

            }
            if (nom_benef.getText().length() < 3) {
                errors.append("Votre nom doit comporter au moins 3 caractères \n");

            }
            if (prenom_benef.getText().length() < 3) {
                errors.append("Votre Prenom doit comporter au moins 3 caractères \n");

            }

        }

        if (rib_benef.getText().trim().isEmpty()) {
            errors.append("Le montant du virement est obligatoire\n");
        }

        if (nom_benef.getText().trim().isEmpty()) {
            errors.append("Le titre du bénéficiaire est obligatoire\n");
        }
        if (prenom_benef.getText().trim().isEmpty()) {
            errors.append("Le prénom du bénéficiaire est obligatoire\n");
        }
        if (email_benef.getText().trim().isEmpty()) {
            errors.append("Le RIB du bénéficiaire est obligatoire\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            beneficiaire b = new beneficiaire();
            BeneficiaireService br = new BeneficiaireService();

            /*v.setMontant(Double.parseDouble(montant.getText()));
            v.setDate_virement(date_virement.getDate());
            v.setDate_virement(Date.valueOf(date_virementt.getValue()));
            v.setTitre(titre.getText());
            v.setPrenom_benef(prenom_benef.getText());
            v.setRib_benef(rib_benef.getText());
            v.setBeneficiaire_id(Integer.parseInt(beneficiaire_id.getText()));
           
           vr.AjouterVir(v);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Virement Ajouter avec succes");
                alert.showAndWait();*/
            br.AjouterBen(new beneficiaire(rib_benef.getText(), nom_benef.getText(), prenom_benef.getText(), email_benef.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Beneficiaire Ajouté avec succes");
            alert.showAndWait();
        }
    }

    @FXML
    private void modifierr(ActionEvent event) {
        BeneficiaireService vr = new BeneficiaireService();
        beneficiaire b = listb.getSelectionModel().getSelectedItem();

        b.setRib_benef(titre.getText());
        b.setNom_benef(nom_benef.getText());
        b.setPrenom_benef(prenom_benef.getText());
        b.setEmail_benef(email_benef.getText());

        vr.modifierBen(b);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Modification éffectuée");
        alert.showAndWait();
    }

    @FXML
    private void supprimerr(ActionEvent event) {

        BeneficiaireService br = new BeneficiaireService();
        br.Supprimer(listb.getSelectionModel().getSelectedItem().getId_benef());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Beneficiaire supprimer avec succes");
        alert.showAndWait();
    }

    @FXML
    private void refreshh(ActionEvent event) {
        BeneficiaireService br = new BeneficiaireService();
        ObservableList<beneficiaire> listt = FXCollections.observableList(br.afficher());
        listb.setItems(listt);

    }

}
