/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.beneficiaire;
import services.BeneficiaireService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AjouterBController implements Initializable {

    @FXML
    private TextField prenom_benef;
    @FXML
    private TextField nom_benef;
    private TextField titre;
    @FXML
    private Button ba;
    @FXML
    private TextField email_benef;
    @FXML
    private TextField rib_benef;
    @FXML
    private ListView<beneficiaire> listb;
    @FXML
    private Button bm;
    @FXML
    private Button bs;
    @FXML
    private AnchorPane btt;
    @FXML
    private Text chyyyy;
    String path = "C:\\Users\\shayma\\Downloads\\The Weeknd - Call Out My Name (Official Audio).mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BeneficiaireService br = new BeneficiaireService();
        ObservableList<beneficiaire> listt = FXCollections.observableList(br.afficher());
        listb.setItems(listt);

    }

    @FXML
    public void saveBen(ActionEvent event) {
        StringBuilder errors = new StringBuilder();

        if (rib_benef.getText().length() < 3 || nom_benef.getText().length() < 3 || prenom_benef.getText().length() < 3) {

            if (rib_benef.getText().length() < 3) {
                errors.append("Votre RIB doit comporter 3 chiffres \n");

            }
            if (nom_benef.getText().length() < 3) {
                errors.append("Votre nom doit comporter au moins 3 caractères \n");

            }
            if (prenom_benef.getText().length() < 3) {
                errors.append("Votre Prenom doit comporter au moins 3 caractères \n");

            }

        }

        if (rib_benef.getText().trim().isEmpty()) {
            errors.append("Le montant du virement est obligatoire\n");
        }

        if (nom_benef.getText().trim().isEmpty()) {
            errors.append("Le titre du bénéficiaire est obligatoire\n");
        }
        if (prenom_benef.getText().trim().isEmpty()) {
            errors.append("Le prénom du bénéficiaire est obligatoire\n");
        }
        if (email_benef.getText().trim().isEmpty()) {
            errors.append("Le RIB du bénéficiaire est obligatoire\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            beneficiaire b = new beneficiaire(rib_benef.getText(), nom_benef.getText(), prenom_benef.getText(), email_benef.getText());
            BeneficiaireService br = new BeneficiaireService();

            /*v.setMontant(Double.parseDouble(montant.getText()));
            v.setDate_virement(date_virement.getDate());
            v.setDate_virement(Date.valueOf(date_virementt.getValue()));
            v.setTitre(titre.getText());
            v.setPrenom_benef(prenom_benef.getText());
            v.setRib_benef(rib_benef.getText());
            v.setBeneficiaire_id(Integer.parseInt(beneficiaire_id.getText()));
           
           vr.AjouterVir(v);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Virement Ajouter avec succes");
                alert.showAndWait();*/
            br.AjouterBen(b);
            GenerateQrEvent(b);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Beneficiaire Ajouté avec succes");
            alert.showAndWait();
        }
    }

    @FXML
    private ImageView qr_CODE;

    @FXML
    void afficherQR(MouseEvent event) {
        try {
            qr_CODE.setImage(new Image(new FileInputStream("C:\\Users\\shayma\\Desktop\\imgQr" + listb.getSelectionModel().getSelectedItem().getNom_benef() + "_QrCode.jpg")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
    }

    @FXML
    public void modifierr(ActionEvent event) {
        BeneficiaireService br = new BeneficiaireService();
        beneficiaire b = listb.getSelectionModel().getSelectedItem();

        b.setRib_benef(rib_benef.getText());
        b.setNom_benef(nom_benef.getText());
        b.setPrenom_benef(prenom_benef.getText());
        b.setEmail_benef(email_benef.getText());
        br.modifierBen(b);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Modification éffectuée");
        alert.showAndWait();
    }

    @FXML
    public void supprimerr(ActionEvent event) {

        BeneficiaireService br = new BeneficiaireService();
        br.Supprimer(listb.getSelectionModel().getSelectedItem().getId_benef());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Beneficiaire supprimer avec succes");
        alert.showAndWait();
        refreshh();
    }

    @FXML
    public void refreshh() {
        BeneficiaireService br = new BeneficiaireService();
        ObservableList<beneficiaire> listt = FXCollections.observableList(br.afficher());
        listb.setItems(listt);

    }

    public String GenerateQrEvent(beneficiaire b) {
        try {
            String bi = "Beneficiaire rib: " + b.getRib_benef() + "\n" + "beneficiaire name: " + b.getNom_benef() + "\n" + "beneficiaire prenom: " + b.getPrenom_benef() + "\n" + "beneficiaire email: " + b.getEmail_benef() + "\n";
            ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(bi).to(net.glxn.qrgen.image.ImageType.JPG).stream();
            String filename = b.getNom_benef() + "_QrCode.jpg";
            //File f = new File("src\\utils\\img\\" + filename);
            File f = new File("C:\\Users\\shayma\\Desktop\\imgQr" + filename);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(out.toByteArray());
            fos.flush();

            System.out.println("QR code ajouté");
            return filename;
        } catch (Exception ignored) {
            System.out.println(ignored.getMessage());
            return null;
        }
    }
    @FXML
    private TextField _search;

    @FXML
    void seach(KeyEvent event) {
        BeneficiaireService br = new BeneficiaireService();
        List<beneficiaire> myList = br.afficher().stream().filter(benif -> {
            return benif.getEmail_benef().toLowerCase().contains(_search.getText().toLowerCase()) || String.valueOf(benif.getId_benef()).toLowerCase().contains(_search.getText().toLowerCase())
                    || benif.getNom_benef().toLowerCase().contains(_search.getText().toLowerCase()) || benif.getPrenom_benef().toLowerCase().contains(_search.getText().toLowerCase()
            );
        }).collect(Collectors.toList());

        ObservableList<beneficiaire> listt = FXCollections.observableList(
                myList
        );
        listb.setItems(listt);
    }

    @FXML
    private void Play(ActionEvent event) {
        mediaPlayer.play();
        //  Image img = new Image("C:\\xampp\\htdocs\\chy.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique demarreeerrr");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void Pause(ActionEvent event) {
        mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Arrêtée");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void zz(MouseEvent event) {

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
