/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Services.depensesCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author montassar
 */
public class StatController implements Initializable {

    @FXML
    private AnchorPane main_page;
    @FXML
    private AnchorPane main_page1;
    @FXML
    private Button btnhome1;
    @FXML
    private Button nav_btn_addBD1;
    @FXML
    private Button nav_btn_depense1;
    @FXML
    private AnchorPane satitc_form1;
    @FXML
    private BarChart<String, Number> bar_chart1;
    @FXML
    private PieChart pie_chart1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeChart();
          updatePieChart();
        // TODO
    }    

    @FXML
    private void switchForm(ActionEvent event) {
    }

    @FXML
    private void home3(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("homeinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    
    }

    @FXML
    private void budget3(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("budgetinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    

    }

    @FXML
    private void depenses3(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("depenseinterface.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    public void homeChart() {
            // Efface les données précédentes du graphique à barres
             bar_chart1.getData().clear();   
            depensesCRUD dc =new depensesCRUD();
            // Récupère la somme des dépenses par catégorie
            Map<String, Double> sumByCategory = dc.getSumByCategory();

            // Crée une série de données pour le graphique à barres
            XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
            for (Map.Entry<String, Double> entry : sumByCategory.entrySet()) {
                String category = entry.getKey();
                Double sum = entry.getValue();
                dataSeries.getData().add(new XYChart.Data<>(category, sum));
            }
              // bar_chart.getData().add(dataSeries);
            // Ajoute la série de données au graphique à barres existant
          bar_chart1.setData(FXCollections.observableArrayList(dataSeries));
}
    private void updatePieChart() {
            pie_chart1.getData().clear();
            depensesCRUD dc =new depensesCRUD();
            Map<String, Double> percentageByTypeDepense = dc.getPercentageByTypeDepense();
            for (Map.Entry<String, Double> entry : percentageByTypeDepense.entrySet()) {
                String typeDepense = entry.getKey();
                Double percentage = entry.getValue();
                PieChart.Data data = new PieChart.Data(typeDepense, percentage);
                pie_chart1.getData().add(data);
            }
}
    
}
