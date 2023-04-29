/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Account;
import Entities.Sexe;
import Services.AccountServices;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Utils.ListSexe;
import com.itextpdf.text.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;


/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class AccountCrudController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtnum;
    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtdate;
    @FXML
    private TextField txtad;
    @FXML
    private TextField txtville;
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtsolde;
    @FXML
    private ComboBox<Sexe> Sx;
    private Stage stage;
    private File file;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Button btn_importer;
    @FXML
    private Button btn_ajouter;
    @FXML 
    private ImageView image;
   
   
      

    @FXML
    private void ajouter(ActionEvent event) {
        boolean test = true;
        if (txtnom.getText().isEmpty() || txtnum.getText().isEmpty() || txtmail.getText().isEmpty()
				|| txtdate.getText().isEmpty() || txtad.getText().isEmpty() || txtville.getText().isEmpty()
				|| txtcin.getText().isEmpty() || txtsolde.getText().isEmpty()) {
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
            
                        int Solde = Integer.parseInt(txtsolde.getText());

    if(Solde < 0 || Solde > 1000000000){
        test=false;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le solde doit être compris entre 0 et un entier positif");
    alert.showAndWait();
    return;
}
            String NomComplet = txtnom.getText();
            int  NumTel = Integer.parseInt(txtnum.getText());
            String Email = txtmail.getText();
            String DateNaiss = txtdate.getText();
            String Adresse = txtad.getText();
            String Ville = txtville.getText();
            String brochure_filename = txtcin.getText();
            
    
    if(test==true){ 
    Account nouveauAccount = new Account();
			nouveauAccount.setNomComplet(txtnom.getText());
			String[] split_list = txtcin.getText().split("\\\\");
			String image = "" + split_list[split_list.length - 1];
                        nouveauAccount.setBrochure_filename(file.getPath());
			nouveauAccount.setNumTel(Integer.parseInt(txtnum.getText()));
			nouveauAccount.setEmail(txtmail.getText());
			nouveauAccount.setDateNaiss(txtdate.getText());
			nouveauAccount.setAdresse(txtad.getText());
			nouveauAccount.setVille(txtville.getText());
                        nouveauAccount.setSolde(Integer.parseInt(txtsolde.getText()));
			String sexe = Sx.getSelectionModel().getSelectedItem().getNom();
			nouveauAccount.setSexe(sexe);
			AccountServices ac = new AccountServices();
                        ac.ajouterAccount(nouveauAccount);
			Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
			alert2.setTitle("Information Dialog");
			alert2.setHeaderText(null);
			alert2.setContentText("A new account was inserted successfully!");

			Optional<ButtonType> result = alert2.showAndWait();
    
   
   }

			
			 
		}
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListSexe listesexe = new ListSexe();

		Sx.setConverter(new StringConverter<Sexe>() {
            @Override
            public String toString(Sexe object) {
                if (object.getNom() != null) {
                    return object.getNom();
                }else return "";
            }

            @Override
            public Sexe fromString(String string) {
                return Sx.getSelectionModel().getSelectedItem();
            }
        });
	
       Sx.setItems(ListSexe.getListSexe());
        // TODO
    }  


    @FXML
    private void importer(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez un fichier PNG");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
        	txtcin.setText(fichierSelectionne.getName());
            file = fichierSelectionne;
        }
    }
    

    
}    
