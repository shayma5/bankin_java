/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Account;
import Services.AccountServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    @FXML
    private Button btn_importer1;
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
