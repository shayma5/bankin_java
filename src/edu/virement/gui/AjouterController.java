/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virement.gui;

import edu.virement.entities.virement;
import edu.virement.services.VirementService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AjouterController implements Initializable {

    @FXML
    private ListView<virement> listv;
    @FXML
    private Button bm;
    @FXML
    private Button bs;
    private TextField montant;
    @FXML
    private TextField titre;
    @FXML
    private Button ba;
    private TextField beneficiaire_id;
    @FXML
    private TextField rib_benef;
    private DatePicker date_virement;
    @FXML
    private Label prenom_benef;

    virement v = new virement();
    @FXML
    private Label nom_benef;
    @FXML
    private TextField id_benef;
    @FXML
    private Label email_benef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VirementService vr = new VirementService();
        ObservableList<virement> listtr = FXCollections.observableList(vr.afficherVir());
        listv.setItems(listtr);
    }

    @FXML
    private void save(ActionEvent event) {

        StringBuilder errors = new StringBuilder();
        java.sql.Date date_virementt = java.sql.Date.valueOf(date_virement.getValue());

        if (titre.getText().length() < 3 || prenom_benef.getText().length() < 3 || rib_benef.getText().length() < 3) {

            if (rib_benef.getText().length() < 3) {
                errors.append("Votre RIB doit comporter 3 chiffres \n");

            }
            if (titre.getText().length() < 3) {
                errors.append("Votre Titre doit comporter au moins 3 caractères \n");

            }
            if (prenom_benef.getText().length() < 3) {
                errors.append("Votre Prenom doit comporter au moins 3 caractères \n");

            }

        }

        if (montant.getText().trim().isEmpty()) {
            errors.append("Le montant du virement est obligatoire\n");
        }

        if (titre.getText().trim().isEmpty()) {
            errors.append("Le titre du bénéficiaire est obligatoire\n");
        }
        if (prenom_benef.getText().trim().isEmpty()) {
            errors.append("Le prénom du bénéficiaire est obligatoire\n");
        }
        if (rib_benef.getText().trim().isEmpty()) {
            errors.append("Le RIB du bénéficiaire est obligatoire\n");
        }
        if (beneficiaire_id.getText().trim().isEmpty()) {
            errors.append("L'identifiant du bénéficiaire est obligatoire\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            virement v = new virement();
            VirementService vr = new VirementService();

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
            vr.AjouterVir(new virement(Integer.parseInt(montant.getText()), date_virementt, titre.getText(), prenom_benef.getText(), rib_benef.getText(), Integer.parseInt(beneficiaire_id.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Virement Ajouté avec succes");
            alert.showAndWait();

        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        VirementService vr = new VirementService();
        virement v = listv.getSelectionModel().getSelectedItem();

        v.setMontant(Double.parseDouble(montant.getText()));
        v.setDate_virement(java.sql.Date.valueOf(date_virement.getValue()));
        v.setTitre(titre.getText());
        v.setPrenom_benef(prenom_benef.getText());
        v.setRib_benef(rib_benef.getText());
        v.setBeneficiaire_id(Integer.parseInt(beneficiaire_id.getText()));

        vr.modifierVir(v);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("modification éffectuée");
        alert.showAndWait();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        VirementService vr = new VirementService();
        vr.SupprimerVir(listv.getSelectionModel().getSelectedItem().getId_virement());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Virement supprimer avec succes");
        alert.showAndWait();
    }

    @FXML
    private void refresh(ActionEvent event) {
        VirementService vr = new VirementService();
        ObservableList<virement> listtr = FXCollections.observableList(vr.afficherVir());
        listv.setItems(listtr);
    }

}
