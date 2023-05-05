/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.virement;
import Services.VirementService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML
    private Button ben_btn;
    @FXML
    private TextField montant;
    @FXML
    private TextField titre;
    @FXML
    private Button ba;
    @FXML
    private TextField beneficiaire_id;
    @FXML
    private TextField rib_benef;
    @FXML
    private DatePicker date_virement;
    @FXML
    private TextField prenom_benef;

    virement v = new virement();
    @FXML
    private Button back;

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
    public void save(ActionEvent event) {

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
    public void modifier(ActionEvent event) throws IOException {
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
    public void supprimer(ActionEvent event) {
        VirementService vr = new VirementService();
        vr.SupprimerVir(listv.getSelectionModel().getSelectedItem().getId_virement());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Virement supprimer avec succes");
        alert.showAndWait();
    }

    @FXML
    public void refresh(ActionEvent event) {
        VirementService vr = new VirementService();
        ObservableList<virement> listtr = FXCollections.observableList(vr.afficherVir());
        listv.setItems(listtr);
    }

    @FXML
    void ben_btn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterB.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
