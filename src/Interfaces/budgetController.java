/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entities.budget;
import Entities.depenses;
import Services.budgetCRUD;
import Services.depensesCRUD;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author montassar
 */
public class budgetController implements Initializable {

    @FXML
    private AnchorPane main_page;
    private AnchorPane budget_form;
    private TableView<budget> budget_table;
    private TableColumn<budget, String> idbudget_col;
    private TableColumn<budget, String> montantbudget_col;
    private TableColumn<budget, String> DDebutB_col;
    private TableColumn<budget, String> DFinB_col;
    private Button add_dp_inbd;
    private TextField montant_saisi;
    private DatePicker DB_saisi;
    private DatePicker DF_saisi;
    private TextField compte_id_saisi;
    private AnchorPane depense_form;
    private TextField titre_saisi;
    private TextField rib_saisi;
    private TextField prenom_saisi;
    private TextField montant_dep_saisi;
    private ColorPicker backgC_saisi;
    private DatePicker date_dep_saisi;
    private ComboBox<String> cat_dep_saisi;
    private ComboBox<String> typ_dep_saisi;
    private TableView<depenses> tabview_depense;
    private TableColumn<depenses, String> id_col;
    private TableColumn<depenses, String> bd_id_col;
    private TableColumn<depenses, String> titre_col;
    private TableColumn<depenses, String> prenom_col;
    private TableColumn<depenses, String> rib_col;
    private TableColumn<depenses, String> montant_dep_col;
    private TableColumn<depenses, String> date_col;
    private TableColumn<depenses, String> categ_col;
    private TableColumn<depenses, String> type_col;
    @FXML
    private Button nav_btn_addBD;
    private Label budget_lab;
    private Label DF_BD_lab;
    @FXML
    private AnchorPane main_page1;
    @FXML
    private AnchorPane budget_form1;
    @FXML
    private TableView<budget> budget_table1;
    @FXML
    private TableColumn<budget, String> idbudget_col1;
    @FXML
    private TableColumn<budget, String> montantbudget_col1;
    @FXML
    private TableColumn<budget, String> DDebutB_col1;
    @FXML
    private TableColumn<budget, String> DFinB_col1;
    @FXML
    private TextField addbudget_search;
    private Button add_dp_inbd1;
    @FXML
    private Button ajout_bd_btn1;
    @FXML
    private Button modf_bd_btn1;
    @FXML
    private Button sup_bd_btn1;
    @FXML
    private TextField montant_saisi1;
    @FXML
    private DatePicker DB_saisi1;
    @FXML
    private DatePicker DF_saisi1;
    @FXML
    private TextField compte_id_saisi1;
    @FXML
    private Button nav_btn_depense;
    /*public void switchForm(ActionEvent event) {

        if (event.getSource() == nav_btn_addBD) {
            
            budget_form.setVisible(true);
            depense_form.setVisible(false);
            add_dp_inbd.setVisible(false);
            

            nav_btn_addBD.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            
            
        } 
    }*/
    @FXML
    private Button ajout_depense;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBudgetShowListData();
        // TODO
    }
    private void home2(MouseEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("homeinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    @FXML
    private void budget2(MouseEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("budgetinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void depense2(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("depenseinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    

    private void BudgetSelect(MouseEvent event) {
       
        budget bd = budget_table1.getSelectionModel().getSelectedItem();
        int num = budget_table1.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        
        montant_saisi1.setText(String.valueOf(bd.getMontant()));
        compte_id_saisi1.setText(String.valueOf(bd.getId_account_id()));
        Date date = bd.getDatedebut();
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate1 = zonedDateTime.toLocalDate();
        DB_saisi1.setValue(localDate1);
        Date datef = bd.getDatefin();
        Instant instant2 = datef.toInstant();
        ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate localDatefin = zonedDateTime2.toLocalDate();
        DF_saisi1.setValue(localDatefin);
        
    }

    @FXML
    private void BudgetSelect(TouchEvent event) {
           add_dp_inbd1.setVisible(true);   
        budget bd = budget_table1.getSelectionModel().getSelectedItem();
        int num = budget_table1.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        
        montant_saisi1.setText(String.valueOf(bd.getMontant()));
        compte_id_saisi1.setText(String.valueOf(bd.getId_account_id()));
        Date date = bd.getDatedebut();
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate1 = zonedDateTime.toLocalDate();
        DB_saisi1.setValue(localDate1);
        Date datef = bd.getDatefin();
        Instant instant2 = datef.toInstant();
        ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate localDatefin = zonedDateTime2.toLocalDate();
        DF_saisi1.setValue(localDatefin);
    }
    @FXML
    private void BudgetSelect1(MouseEvent event) {
          
        budget bd = budget_table1.getSelectionModel().getSelectedItem();
        int num = budget_table1.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        
        montant_saisi1.setText(String.valueOf(bd.getMontant()));
        compte_id_saisi1.setText(String.valueOf(bd.getId_account_id()));
        Date date = bd.getDatedebut();
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate1 = zonedDateTime.toLocalDate();
        DB_saisi1.setValue(localDate1);
        Date datef = bd.getDatefin();
        Instant instant2 = datef.toInstant();
        ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate localDatefin = zonedDateTime2.toLocalDate();
        DF_saisi1.setValue(localDatefin);
    }
    private boolean validateString(TextField string) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(string.getText());
        if ((string.getText().length() == 0)
                || (!m.find() && m.group().equals(string.getText()))) {
            
            return false;
        } else {
            string.setEffect(null);
            return true;
        }
}
   
    

    private ObservableList<budget> addBudgetList;
    public void addBudgetShowListData() {
        budgetCRUD BC = new budgetCRUD();
        addBudgetList = BC.afficherlesbudgets();

        idbudget_col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantbudget_col1.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DDebutB_col1.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DFinB_col1.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        

        budget_table1.setItems(addBudgetList);

    }
    public void BudgetReset() {
        montant_saisi1.setText("");
        compte_id_saisi1.setText("");
        DB_saisi1.setValue(LocalDate.now());
        DF_saisi1.setValue(LocalDate.now());
        
    }
    @FXML
    private void addBudgetAdd(ActionEvent event) {
        try{
            Alert alert;
            if (montant_saisi1.getText().isEmpty()
                    || (DB_saisi1.getValue()==null)
                    || (DF_saisi1.getValue()==null)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                double mont= Double.parseDouble( montant_saisi1.getText());
                int id_account=Integer.parseInt(compte_id_saisi1.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date datedebut = dateFormat.parse(""+DB_saisi1.getValue());
                java.util.Date datefin = dateFormat.parse(""+DF_saisi1.getValue()); 
                budget bd= new budget(id_account,mont,datedebut,datefin);
                if (DB_saisi1.getValue().isBefore(LocalDate.now())) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("La date de début doit être supérieure ou égale à la date actuelle");
                        alert.showAndWait();
                        return; // sortir de la méthode si la date est invalide
                         }

                // Vérification de la date de fin
                if (DF_saisi1.getValue().isBefore(LocalDate.now())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("La date de fin doit être supérieure ou égale à la date actuelle");
                    alert.showAndWait();
                    return; // sortir de la méthode si la date est invalide
                }
                budgetCRUD BC = new budgetCRUD();
                BC.ajouterbudget2(bd);
                addBudgetShowListData();
                BudgetReset();
                
                
            }
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addBudgetupdate(ActionEvent event) {
         try{
            Alert alert;
            if (montant_saisi1.getText().isEmpty()
                    || (DB_saisi1.getValue()==null)
                    || (DF_saisi1.getValue()==null)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                budget bd_tab = budget_table1.getSelectionModel().getSelectedItem();
                int id=bd_tab.getId();
                double mont= Double.parseDouble( montant_saisi1.getText());
                int id_account=Integer.parseInt(compte_id_saisi1.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date datedebut = dateFormat.parse(""+DB_saisi1.getValue());
                java.util.Date datefin = dateFormat.parse(""+DF_saisi1.getValue()); 
                budget bd= new budget(id,id_account,mont,datedebut,datefin);
                  if (DB_saisi1.getValue().isBefore(LocalDate.now())) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("La date de début doit être supérieure ou égale à la date actuelle");
                        alert.showAndWait();
                        return; // sortir de la méthode si la date est invalide
                         }

                // Vérification de la date de fin
                if (DF_saisi1.getValue().isBefore(LocalDate.now())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("La date de fin doit être supérieure ou égale à la date actuelle");
                    alert.showAndWait();
                    return; // sortir de la méthode si la date est invalide
                }
                budgetCRUD BC = new budgetCRUD();
                BC.modifierDubget(bd);
                addBudgetShowListData();
                
                
            }
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
  



    @FXML
    private void addEmployeeSearch(KeyEvent event) {
        FilteredList<budget> filter = new FilteredList<>(addBudgetList, e -> true);

        addbudget_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(( predicatebudgetData) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicatebudgetData.getId()).contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicatebudgetData.getMontant()).toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatebudgetData.getDatedebut().toString().contains(searchKey)) {
                    return true;
                } 
                 else if (predicatebudgetData.getDatefin().toString().contains(searchKey)) {
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<budget> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(budget_table1.comparatorProperty());
        budget_table1.setItems(sortList);
    }

    @FXML
    private void addBudgetDelete(ActionEvent event) {
                    try{
            Alert alert;
           
                budget bd_tab = budget_table1.getSelectionModel().getSelectedItem();
                int id=bd_tab.getId();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Budget ID: " + id  + "?");
                Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                budgetCRUD BC = new budgetCRUD();
                BC.supprimerbudget(id);
                addBudgetShowListData();
                BudgetReset();
                
                
            }
            
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goDepense(MouseEvent event) throws IOException {
           budget bd_tab = budget_table1.getSelectionModel().getSelectedItem();
           int id_BD=bd_tab.getId();
           Parent root = FXMLLoader.load(getClass().getResource("depenseinterface.fxml"));
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
    }

    @FXML
    private void profile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
