/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaces;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shayma
 */
public class AdminHomeController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private Button home_btn;
    @FXML
    private Button add_agent_btn;
    @FXML
    private Button salary_btn;
    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Label home_totalagent;
    @FXML
    private Label home_totalpresent;
    @FXML
    private Label home_totalclient;
    @FXML
    private BarChart<?, ?> home_chart;
    @FXML
    private AnchorPane home_form1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    




    @FXML
    private void logout(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void home(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void addagent(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AddAgent.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void showusers(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AdminDashbord.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void showclients(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AdminShowClients.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
}
