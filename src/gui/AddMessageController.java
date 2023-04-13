/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.io.IOException;
import pidev.utils.MyDB;
import pidev.entities.Message;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;


/**
 * FXML Controller class
 *
 * @author ofact
 */
public class AddMessageController implements Initializable {
    

    @FXML
    private TableView<Message> messagetable;

    @FXML
    private TableColumn<Message, String> datecol;

    @FXML
    private DatePicker datep;

    @FXML
    private TableColumn<Message, String> descriptioncol;

    @FXML
    private TextArea descriptiontxt;

    @FXML
    private TableColumn<Message, String> idcol;

    @FXML
    private TableColumn<Message, String> nomcol;

    @FXML
    private TextField nomtxt;

    @FXML
    private TableColumn<Message, String> prenomcol;
    
    @FXML
    private Button rdvbtn;
    
    @FXML
    private Button msgbtn;

    @FXML
    private TextField prenomtxt;
    Statement Ste;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedstatement = null;
    
    private boolean update;
    ResultSet resultSet = null;
    Message message = null;
    ObservableList<Message> MessageList = FXCollections.observableArrayList();
    @FXML
    private Button btnclose;
    @FXML
    private Button btndel;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnup;

    @FXML
    void Ajouter(ActionEvent event) {
        
        connection = MyDB.getConnect();
        String nom = nomtxt.getText();
        String prenom = prenomtxt.getText();
        String date = String.valueOf(datep.getValue());
        String description = descriptiontxt.getText();

        if (nom.isEmpty() || prenom.isEmpty() || date.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New message");
 
alert.setHeaderText("New message");
alert.setContentText("Message added!");
 
alert.showAndWait();
            getQuery();
            insert();
            messagetable();

        }

    }
    
    private void getQuery() {

        
           if(update == false){
            query = "INSERT INTO `message`( `nom`, `prenom`, `date`, `description`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `message` SET "
                    + "`name`=?,"
                    + "`birth`=?,"
                    + "`adress`=?,"
                    + "`email`= ? WHERE id = '"+message.getId()+"'";
        }
}

    
    private void insert() {

        try {

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, nomtxt.getText());
            preparedstatement.setString(2, prenomtxt.getText());
            preparedstatement.setString(3, String.valueOf(datep.getValue()));
            
            preparedstatement.setString(4, descriptiontxt.getText());
            preparedstatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Modifier(ActionEvent event){
         try { 
             message = messagetable.getSelectionModel().getSelectedItem();
         query = "UPDATE message SET nom = '"+ nomtxt.getText() + "', prenom = '"+ prenomtxt.getText() +"',description = '"+ descriptiontxt.getText() +"' WHERE id= '"+message.getId()+"' ";
         preparedstatement = connection.prepareStatement(query);
         preparedstatement.execute();
         messagetable();
         loadDate();
         } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                    
         
    
    }
        
         
    

    @FXML
    void Supprimer(ActionEvent event) {
        
         try {
                                message = messagetable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `message` WHERE id  ="+message.getId();
                                connection = MyDB.getConnect();
                                preparedstatement = connection.prepareStatement(query);
                                preparedstatement.execute();
                                
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte!");
 
alert.setHeaderText("");
alert.setContentText("Message supprimé!");
 
alert.showAndWait();
                                
                                messagetable();
                            } catch (SQLException ex) {
                                Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        
        
        

    }
    
    public void messagetable() {
        try {
            MessageList.clear();
            
            query = "SELECT * FROM `message`";
            preparedstatement = connection.prepareStatement(query);
            resultSet = preparedstatement.executeQuery();
            
            while (resultSet.next()){
                MessageList.add(new  Message(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date"),
                        resultSet.getString("description")));
                messagetable.setItems(MessageList);
                
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
    Parent parent = loader.load();
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    public void goToRdv(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addRdv.fxml"));
    Parent praent = loader.load();
    Scene scene = new Scene(praent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }



    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadDate();
    }    

    private void loadDate() {
        connection = MyDB.getConnect();
        messagetable();
        
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    }

    

    
    
}


