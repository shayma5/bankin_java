/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Account;
import Services.AccountServices;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class AdminController implements Initializable {

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
    private TableView<Account> table_compte;
    @FXML
    private TableColumn<Account, String> column_nom;
    @FXML
    private TableColumn<Account, Integer> column_num;
    @FXML
    private TableColumn<Account, String> column_email;
    @FXML
    private TableColumn<Account, String> column_sexe;
    @FXML
    private TableColumn<Account, Date> column_date;
    @FXML
    private TableColumn<Account, String> column_adr;
    @FXML
    private TableColumn<Account, String> column_ville;
    @FXML
    private TableColumn<Account, String> column_cin;
    @FXML
    private TableColumn<Account, Integer> column_solde;
   
    
    /**
     * Initializes the controller class.
     */
    
    
    
    public void show(){
        ObservableList<Account> listeA = FXCollections.observableArrayList();
        AccountServices as = new AccountServices();
        listeA=as.afficherAccounts();
        column_nom.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        column_num.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_sexe.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));
        column_adr.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        column_ville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        column_cin.setCellValueFactory(new PropertyValueFactory<>("brochure_filename"));
        column_solde.setCellValueFactory(new PropertyValueFactory<>("Solde"));

        table_compte.setItems(listeA);
        table_compte.refresh();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    

    
}
