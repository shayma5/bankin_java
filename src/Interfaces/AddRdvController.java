/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import ConnectionDB.MyConnection;
import Entities.Rdv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Logger;
import javafx.scene.control.TableRow;
import java.util.logging.Level;
import javafx.scene.control.DatePicker;
import java.sql.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.scene.Node;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ofact
 */
public class AddRdvController implements Initializable {
    
    
    
    

     @FXML
    private Button btnclose;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnedit;

    @FXML
    private Button btnsub;

    @FXML
    private TableColumn<Rdv, String> coldate;

    @FXML
    private TableColumn<Rdv, String> coldes;

    @FXML
    private TableColumn<Rdv, String> colemail;

    @FXML
    private TableColumn<Rdv, String> colid;

    @FXML
    private TableColumn<Rdv, String> colnom;

    @FXML
    private TableColumn<Rdv, String> colprenom;

    @FXML
    private DatePicker dat;

    @FXML
    private Button msgbtn;

    @FXML
    private TextField prenomtxt;

    @FXML
    private Button rdvbtn;

    @FXML
    private TableView<Rdv> table;

    @FXML
    private TextArea txtdescription;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtnom;
    
    String emailText = null;
    
    Statement Ste;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedstatement = null;
    public boolean test = true;
    private boolean update;
    ResultSet resultSet = null;
    Rdv rendez = null;
    ObservableList<Rdv> RdvList = FXCollections.observableArrayList();
    @FXML
    private Button profile;
    
    
    
    
    @FXML
    void Delete(ActionEvent event) {

        
        try {
                                rendez = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `rendezvous` WHERE id  ="+rendez.getId();
                                connection = MyConnection.getInstance().getConnection();
                                preparedstatement = connection.prepareStatement(query);
                                preparedstatement.execute();
                                
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Alerte!");
                                alert.setHeaderText("");
                                alert.setContentText("Rendez-Vous supprimé!");
                                
                                rdvtable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            
                           
    }

    @FXML
    void Edit(ActionEvent event) {
        
       try { 
             rendez = table.getSelectionModel().getSelectedItem();
         query = "UPDATE rendezvous SET nom = '"+ txtnom.getText() + "', prenom = '"+ prenomtxt.getText() +"', email = '"+ txtemail.getText() +"' ,probleme = '"+ txtdescription.getText() +"' WHERE id= '"+rendez.getId()+"' ";
         preparedstatement = connection.prepareStatement(query);
         preparedstatement.execute();
         rdvtable();
         loadDate();
         } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
             

    }

    @FXML
    void Enregistrer(ActionEvent event) {
    connection = MyConnection.getInstance().getConnection();
    String nom = txtnom.getText();
    String prenom = prenomtxt.getText();
    String date = String.valueOf(dat.getValue());
    String email = txtemail.getText();
    String description = txtdescription.getText();
        
    if (nom.isEmpty() || prenom.isEmpty() || date.isEmpty() || email.isEmpty() || description.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Fill All DATA");
        alert.showAndWait();
    } else if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Adresse email invalide");
        alert.showAndWait();
    } else {
        try {
            
            LocalDate parsedDate = LocalDate.parse(date);
            LocalDate today = LocalDate.now();
            if (parsedDate.isBefore(today)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("La date selectionnée doit être supérieure ou égale à la date actuelle");
                alert.showAndWait();
                return;
                
            }
            
            
            
       } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid date format");
            alert.showAndWait();
            return;
        }
        

                  
//        


        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nouveau Rendez-Vous");
        alert.setHeaderText("Rendez-vous ajouté!");
        alert.showAndWait();
        
           
        getQuery();
        insert();
        rdvtable();
        
        
    
    }
}
    
     private void getQuery() {

        
           if(update == false){
            query = "INSERT INTO `rendezvous`( `nom`, `prenom`, `date`,`email`, `probleme`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `rendezvous` SET "
                    + "`name`=?,"
                    + "`birth`=?,"
                    + "`adress`=?,"
                    + "`email`= ? WHERE id = '"+rendez.getId()+"'";
        }
}
     
     private void insert() {

        try {

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, txtnom.getText());
            preparedstatement.setString(2, prenomtxt.getText());
            preparedstatement.setString(3, String.valueOf(dat.getValue()));
            preparedstatement.setString(4, txtemail.getText());
            preparedstatement.setString(5, txtdescription.getText());
            preparedstatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rdvtable() {
        try {
            RdvList.clear();
            
            query = "SELECT * FROM `rendezvous`";
            preparedstatement = connection.prepareStatement(query);
            resultSet = preparedstatement.executeQuery();
            
            while (resultSet.next()){
                RdvList.add(new  Rdv(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date"),
                        resultSet.getString("email"),
                        resultSet.getString("probleme")));
                table.setItems(RdvList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
   
     
     
    @FXML
    void close(ActionEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

     @FXML
    public void goToMessages(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addMessage.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    public void goToRdv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addRdv.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadDate(); 
     rdvtable();
    }
    private void loadDate() {    // TODO

connection = MyConnection.getInstance().getConnection();
        rdvtable();
        
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("probleme"));
    }    
    
    
     public boolean isValid(String email) {
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.find();
}

public void checkField(KeyEvent event) {
    
}

    @FXML
    private void profile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void checkField(InputMethodEvent event) {
    }

    @FXML
    private void checkField(ActionEvent event) {
    }




}

    
    
    

