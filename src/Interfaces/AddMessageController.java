/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import ConnectionDB.MyConnection;
import Entities.Message;

import java.net.URL;
import java.util.ResourceBundle;
;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.sql.*;

import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Logger;

import java.util.logging.Level;
import javafx.scene.control.DatePicker;
import java.sql.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.scene.Node;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

import static Entities.BadWords.checkWords;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.control.DatePicker;
import java.sql.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.scene.Node;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
import static Entities.BadWords.checkWords;

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
    private TextField _search;
       String path = "C:\\Users\\shayma\\Downloads\\The Weeknd - Call Out My Name (Official Audio).mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @FXML
    private Button btnplay;
    @FXML
    private Button btnpause;
    @FXML
    private Button profile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
        messagetable.setItems(MessageList);

    }

    private void getQuery() {

        query = "INSERT INTO `message`( `nom`, `prenom`, `date`, `description`) VALUES (?,?,?,?)";

    }

    private void insert() {

        try {
            String query = "INSERT INTO message (nom, prenom, date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomtxt.getText());
            preparedStatement.setString(2, prenomtxt.getText());
            preparedStatement.setDate(3, Date.valueOf(datep.getValue()));
            preparedStatement.setString(4, descriptiontxt.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Ajouter(ActionEvent event) {
        int attention = 0;
        connection = MyConnection.getInstance().getConnection();
        String nom = nomtxt.getText();
        String prenom = prenomtxt.getText();
        LocalDate date = datep.getValue();
        String description = descriptiontxt.getText();

        if (checkWords(nomtxt.getText()).equals("false") && (checkWords(prenomtxt.getText()).equals("false"))) {
                         if (nom.isEmpty() || prenom.isEmpty() || description.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();
            } else if (date.isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("La date selectionnée doit être supérieure ou égale à la date actuelle");
                alert.showAndWait();
            } else if (description.length() > 200) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("La description ne doit pas dépasser 200 caractères");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nouvelle message");

                alert.setContentText("Message ajoutée!");
                alert.showAndWait();
                getQuery();
                insert();
                messagetable();
            }
            
        } else {
            attention++;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !! ");
            alert.setContentText("vous ne pouvez pas ajouter un messgage avec ces mots ! ");
            alert.show();
            if (attention > 2) {
                System.out.println(attention);
            }

        }

        
           
        
    }

    @FXML
    void Modifier(ActionEvent event) {
        try {
            message = messagetable.getSelectionModel().getSelectedItem();
            query = "UPDATE message SET nom = '" + nomtxt.getText() + "', prenom = '" + prenomtxt.getText() + "',description = '" + descriptiontxt.getText() + "' WHERE id= '" + message.getId() + "' ";
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
            query = "DELETE FROM `message` WHERE id  =" + message.getId();
            connection = MyConnection.getInstance().getConnection();
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
            query = "SELECT * FROM message";
            preparedstatement = connection.prepareStatement(query);
            resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                MessageList.add(new Message(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getDate("date"), resultSet.getString("description")));
            }

            messagetable.setItems(MessageList);
        } catch (SQLException ex) {
            Logger.getLogger(AddMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    Message p = new Message();

    private void loadDate() {
        connection = MyConnection.getInstance().getConnection();
        messagetable.setItems(MessageList);
        messagetable();

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    void pdfEvent(ActionEvent event) throws IOException {
        // evenement tab_Recselected = evenementTv.getSelectionModel().getSelectedItem();
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les Produit . Pour chaque événement, nous fournissons des informations telles que la date d'aujourdhui :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("Nom");
            table.addCell("Prenom");
            table.addCell("Date");
            table.addCell("Description");

            MessageList.forEach(e -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getNom()));
                table.addCell(String.valueOf(e.getPrenom()));
                table.addCell(String.valueOf(e.getDate()));
                table.addCell(String.valueOf(e.getDescription()));
            });

            document.add(ph1);
            document.add(ph2);
            document.add(table);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }
    }

    public List<Message> afficher() {

        List<Message> myList = new ArrayList();

        try {
            query = "SELECT * FROM message";
            preparedstatement = connection.prepareStatement(query);
            resultSet = preparedstatement.executeQuery();
            while (resultSet.next()) {
                myList.add(new Message(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getDate("date"), resultSet.getString("description")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myList;
    }

    @FXML
    public void chhhhh(ActionEvent event) {

    }

    public void chercher(KeyEvent event) {

    }

    @FXML
    public void cchhherr(KeyEvent event) {
        Message br = new Message();
        List<Message> myList = afficher().stream().filter(benif -> {
            return benif.getNom().toLowerCase().contains(_search.getText().toLowerCase()) || String.valueOf(benif.getDate()).toLowerCase().contains(_search.getText().toLowerCase())
                    || benif.getDescription().toLowerCase().contains(_search.getText().toLowerCase()
                    );
        }).collect(Collectors.toList());

        ObservableList<Message> listt = FXCollections.observableList(
                myList
        );
        messagetable.setItems(listt);
    }

    @FXML
    public void playMusic(ActionEvent event) {

        mediaPlayer.play();
      //  Image img = new Image("C:\\xampp\\htdocs\\chy.png");
  Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Music On");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    public void pauseMusic(ActionEvent event) {

        mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Music Out");
        notificationBuilder.darkStyle();
       notificationBuilder.show();
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
