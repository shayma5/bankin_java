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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author montassar
 */
public class AddbudgetController implements Initializable {
  @FXML
    private AnchorPane main_page;

    @FXML
    private Button btnhome;

    @FXML
    private Button nav_btn_addBD;

    @FXML
    private Button nav_btn_depense;

    @FXML
    private AnchorPane budget_form;

     @FXML
    private TableView<budget> budget_table;

    @FXML
    private TableColumn<budget, String> idbudget_col;

    @FXML
    private TableColumn<budget, String> montantbudget_col;

    @FXML
    private TableColumn<budget, String> DDebutB_col;

    @FXML
    private TableColumn<budget, String> DFinB_col;
    @FXML
    private TextField montant_saisi;

    @FXML
    private DatePicker DB_saisi;

    @FXML
    private DatePicker DF_saisi;

    @FXML
    private Button ajout_bd_btn;

    @FXML
    private Button modf_bd_btn;

    @FXML
    private Button sup_bd_btn;

    @FXML
    private TextField compte_id_saisi;

    @FXML
    private AnchorPane home_page;

    @FXML
    private AnchorPane depense_form;

    @FXML
    private TextField titre_saisi;

    @FXML
    private TextField rib_saisi;

    @FXML
    private TextField prenom_saisi;

    @FXML
    private TextField montant_dep_saisi;

    @FXML
    private ColorPicker backgC_saisi;

    @FXML
    private DatePicker date_dep_saisi;

    @FXML
    private ComboBox<String> cat_dep_saisi;

    @FXML
    private ComboBox<String> typ_dep_saisi;

    @FXML
    private Button btn_modifier_dep;

    @FXML
    private Button btn_sup_dep;

    @FXML
    private TableView<depenses> tabview_depense;

    @FXML
    private TableColumn<depenses, String> id_col;

    @FXML
    private TableColumn<depenses, String> bd_id_col;

    @FXML
    private TableColumn<depenses, String> titre_col;

    @FXML
    private TableColumn<depenses, String> prenom_col;

    @FXML
    private TableColumn<depenses, String> rib_col;

    @FXML
    private TableColumn<depenses, String> montant_dep_col;

    @FXML
    private TableColumn<depenses, String> date_col;

    @FXML
    private TableColumn<depenses, String> categ_col;

    @FXML
    private TableColumn<depenses, String> type_col;
    @FXML
    private Button add_dp_inbd;
    @FXML
    private Button btn_add_dep1;

      @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == btnhome) {
            home_page.setVisible(true);
            budget_form.setVisible(false);
            depense_form.setVisible(false);

            btnhome.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            nav_btn_addBD.setStyle("-fx-background-color:transparent");
            nav_btn_depense.setStyle("-fx-background-color:transparent");
          

            

        } else if (event.getSource() == nav_btn_addBD) {
            home_page.setVisible(false);
            budget_form.setVisible(true);
            depense_form.setVisible(false);
            add_dp_inbd.setVisible(false);
            

            nav_btn_addBD.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            btnhome.setStyle("-fx-background-color:transparent");
            nav_btn_depense.setStyle("-fx-background-color:transparent");
            
        } else if (event.getSource() == nav_btn_depense) {
            home_page.setVisible(false);
            budget_form.setVisible(false);
            depense_form.setVisible(true);
            btn_add_dep1.setVisible(false);
            
            

            nav_btn_depense.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            btnhome.setStyle("-fx-background-color:transparent");
            nav_btn_addBD.setStyle("-fx-background-color:transparent");
            
        } 

    }
       public void BudgetSelect() {
        add_dp_inbd.setVisible(true);   
        budget bd = budget_table.getSelectionModel().getSelectedItem();
        int num = budget_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        
        montant_saisi.setText(String.valueOf(bd.getMontant()));
        compte_id_saisi.setText(String.valueOf(bd.getId_account_id()));
        Date date = bd.getDatedebut();
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate1 = zonedDateTime.toLocalDate();
        DB_saisi.setValue(localDate1);
        Date datef = bd.getDatefin();
        Instant instant2 = datef.toInstant();
        ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate localDatefin = zonedDateTime2.toLocalDate();
        DF_saisi.setValue(localDatefin);
        
       

        
    }

    private ObservableList<budget> addBudgetList;
    public void addBudgetShowListData() {
        budgetCRUD BC = new budgetCRUD();
        addBudgetList = BC.afficherlesbudgets();

        idbudget_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantbudget_col.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DDebutB_col.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        DFinB_col.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        

        budget_table.setItems(addBudgetList);

    }
   @FXML
    public void addBudgetAdd() {
        try{
            Alert alert;
            if (montant_saisi.getText().isEmpty()
                    || (DB_saisi.getValue()==null)
                    || (DF_saisi.getValue()==null)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                double mont= Double.parseDouble( montant_saisi.getText());
                int id_account=Integer.parseInt(compte_id_saisi.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date datedebut = dateFormat.parse(""+DB_saisi.getValue());
                java.util.Date datefin = dateFormat.parse(""+DF_saisi.getValue()); 
                budget bd= new budget(id_account,mont,datedebut,datefin);
                if (DB_saisi.getValue().isBefore(LocalDate.now())) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("La date de début doit être supérieure ou égale à la date actuelle");
                        alert.showAndWait();
                        return; // sortir de la méthode si la date est invalide
                         }

                // Vérification de la date de fin
                if (DF_saisi.getValue().isBefore(LocalDate.now())) {
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
    public void addBudgetupdate() {
        try{
            Alert alert;
            if (montant_saisi.getText().isEmpty()
                    || (DB_saisi.getValue()==null)
                    || (DF_saisi.getValue()==null)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                budget bd_tab = budget_table.getSelectionModel().getSelectedItem();
                int id=bd_tab.getId();
                double mont= Double.parseDouble( montant_saisi.getText());
                int id_account=Integer.parseInt(compte_id_saisi.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date datedebut = dateFormat.parse(""+DB_saisi.getValue());
                java.util.Date datefin = dateFormat.parse(""+DF_saisi.getValue()); 
                budget bd= new budget(id,id_account,mont,datedebut,datefin);
                  if (DB_saisi.getValue().isBefore(LocalDate.now())) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("La date de début doit être supérieure ou égale à la date actuelle");
                        alert.showAndWait();
                        return; // sortir de la méthode si la date est invalide
                         }

                // Vérification de la date de fin
                if (DF_saisi.getValue().isBefore(LocalDate.now())) {
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
       public void BudgetReset() {
        montant_saisi.setText("");
        compte_id_saisi.setText("");
        DB_saisi.setValue(LocalDate.now());
        DF_saisi.setValue(LocalDate.now());
        
    }
    public void addBudgetDelete() {
        try{
            Alert alert;
           
                budget bd_tab = budget_table.getSelectionModel().getSelectedItem();
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
    private String[] listOFType = {"cash", "virement"};

    @FXML
    public void ListOfDepensesType() {
        List<String> listT = new ArrayList<>();

        for (String data : listOFType) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        typ_dep_saisi.setItems(listData);
    }    
       private String[] listOFCategorie = {"credit", "famille","loisirs et culture","vie quotidienne","autres"};

    @FXML
    public void ListOfDepensesCategorie() {
        List<String> listC = new ArrayList<>();

        for (String data : listOFCategorie) {
            listC.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listC);
        cat_dep_saisi.setItems(listData);
    }   
    private ObservableList<depenses> addDepensesList;
    public void DepensesShowListData() {
        depensesCRUD DC = new depensesCRUD();
        addDepensesList = DC.afficherlesDepenses();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id_depense"));
        bd_id_col.setCellValueFactory(new PropertyValueFactory<>("idbudget_id"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("prenom_destinataire"));
        rib_col.setCellValueFactory(new PropertyValueFactory<>("rib_destinataire"));
        montant_dep_col.setCellValueFactory(new PropertyValueFactory<>("montant"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        categ_col.setCellValueFactory(new PropertyValueFactory<>("categorie_depense"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type_depense"));
        
        tabview_depense.setItems(addDepensesList);

    }  
    public void DepensesReset() {
        titre_saisi.setText("");
        prenom_saisi.setText("");
        rib_saisi.setText("");
        montant_dep_saisi.setText("");
        backgC_saisi.setCache(true);
        date_dep_saisi.setValue(LocalDate.now());
        typ_dep_saisi.getSelectionModel().clearSelection();
        cat_dep_saisi.getSelectionModel().clearSelection();
        
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
       public void addDepenses() {
           budget bd_tab = budget_table.getSelectionModel().getSelectedItem();
           int id_BD=bd_tab.getId();
           budget_form.setVisible(false);
           depense_form.setVisible(true);
           btn_add_dep1.setVisible(true);
            try{
            Alert alert;
             String titre= titre_saisi.getText();
             String prenom_des= prenom_saisi.getText();
             String rib_des= rib_saisi.getText();
            int montant=Integer.parseInt(montant_dep_saisi.getText());
             String backgroundcolor= ""+(backgC_saisi.getValue()).toString();
            System.err.println(backgroundcolor);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(""+date_dep_saisi.getValue());
            String cat=(cat_dep_saisi.getSelectionModel().getSelectedItem()).toString();
            String type=(typ_dep_saisi.getSelectionModel().getSelectedItem()).toString();
            depenses d= new depenses(id_BD,titre,prenom_des,rib_des,montant,backgroundcolor,date,cat,type);
             if (!validateString(titre_saisi) || !validateString(prenom_saisi) || !validateString(rib_saisi)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer des chaînes de caractères valides dans les champs de texte.");
            alert.showAndWait();
               } 
             else if (montant_dep_saisi.getText().isEmpty()
                || (date_dep_saisi.getValue()==null)
                || typ_dep_saisi.getSelectionModel().getSelectedItem() == null
                || cat_dep_saisi.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs vides.");
            alert.showAndWait();
            } 
             else if (date.toInstant().isBefore(Instant.now())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("La date de dépense doit être postérieure à aujourd'hui.");
            alert.showAndWait();
        } else {
            depensesCRUD DC = new depensesCRUD();
            DC.ajouterDepense(d);
            DepensesShowListData();
            DepensesReset();
        }        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
           
       
    
    }  
     public void DepenseSelect() {   
        depenses d = tabview_depense.getSelectionModel().getSelectedItem();
        int num = tabview_depense.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        
        titre_saisi.setText(String.valueOf(d.getTitle()));
        prenom_saisi.setText(String.valueOf(d.getPrenom_destinataire()));
        rib_saisi.setText(String.valueOf(d.getRib_destinataire()));
        montant_dep_saisi.setText(String.valueOf(d.getMontant()));
        cat_dep_saisi.setValue(d.getCategorie_depense());
        typ_dep_saisi.setValue(d.getType_depense());
        backgC_saisi.setValue(Color.valueOf(d.getBackgroundcolor()));
        Date date = d.getDatedebut();
        LocalDate localDate = date.toInstant().atZone(ZoneId.of("Europe/Paris")).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date_dep_saisi.setPromptText("jj/mm/aaaa");
       date_dep_saisi.setConverter(new LocalDateStringConverter(formatter, null));
       date_dep_saisi.setValue(localDate);
        
        

        
    } 
        public void DepensesUpdate() {
        try{
            Alert alert;
            if (titre_saisi.getText().isEmpty()
                    ||prenom_saisi.getText().isEmpty()
                    ||rib_saisi.getText().isEmpty()
                    ||montant_dep_saisi.getText().isEmpty()
                    || (date_dep_saisi.getValue()==null)
                    || typ_dep_saisi.getSelectionModel().getSelectedItem() == null
                    || cat_dep_saisi.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                depenses bd_tab = tabview_depense.getSelectionModel().getSelectedItem();
                int id=bd_tab.getId_depense();
                int id_bud=bd_tab.getIdbudget_id();
                String titre= titre_saisi.getText();
                String prenom_des= prenom_saisi.getText();
                String rib_des= rib_saisi.getText();
                int montant=Integer.parseInt(montant_dep_saisi.getText());
                String backgroundcolor= ""+(backgC_saisi.getValue()).toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = dateFormat.parse(""+date_dep_saisi.getValue());
                String cat=(cat_dep_saisi.getSelectionModel().getSelectedItem());
                String type=(typ_dep_saisi.getSelectionModel().getSelectedItem());
                depenses bd= new depenses(id,id_bud,titre,prenom_des,rib_des,montant,backgroundcolor,date,cat,type);
                depensesCRUD BC = new depensesCRUD();
                BC.modifierDepense(bd);
                DepensesShowListData();
                
                
            }
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public void DepensesDelete() {
        try{
            Alert alert;
           
                depenses bd_tab = tabview_depense.getSelectionModel().getSelectedItem();
                int id=bd_tab.getId_depense();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Dépense ID: " + id  + "?");
                Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                depensesCRUD BC = new depensesCRUD();
                BC.supprimerDepense(id);
                DepensesShowListData();
                DepensesReset();
                
                
            }
            
        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBudgetShowListData();
        DepensesShowListData();
        ListOfDepensesType();
        ListOfDepensesCategorie();
        // TODO
    }    
    
}
