/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.depenses;
import Entities.virement;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



/**
 *
 * @author montassar
 */
public class depensesCRUD {
    Connection cnx2;
    public depensesCRUD(){
        cnx2 = MyConnection.getInstance().getConnection();
    }
    public void ajouterDepense(depenses d) throws ParseException{
        try {
             
            String requete2 ="INSERT INTO depenses(idbudget_id ,title,prenom_destinataire,rib_destinataire,montant,backgroundcolor,datedebut,categorie_depense,type_depense)"+ "VALUES (?, ?, ?, ?,?, ?, ?, ?,?)";
            PreparedStatement ps = cnx2.prepareStatement(requete2);
            ps.setInt(1, d.getIdbudget_id()); 
            ps.setString(2,d.getTitle());
            ps.setString(3,d.getPrenom_destinataire());
            ps.setString(4,d.getRib_destinataire());
            ps.setInt(5, d.getMontant());
            ps.setString(6,d.getBackgroundcolor());
            ps.setTimestamp(7, new Timestamp(d.getDatedebut().getTime()));
            ps.setString(8,d.getCategorie_depense());   
            ps.setString(9,d.getType_depense());
          
            ps.executeUpdate();
            System.out.println("dépense ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ObservableList<depenses> afficherlesDepenses(){
        ObservableList<depenses> mylist =FXCollections.observableArrayList();
        try {
            
            String rq="SELECT * FROM depenses";
            Statement st =cnx2.createStatement();
            ResultSet rs= st.executeQuery(rq);
            while(rs.next()){
                depenses d =new depenses();
                d.setId_depense(rs.getInt(1));
                d.setIdbudget_id(rs.getInt(2));
                d.setTitle(rs.getString(3));
                d.setPrenom_destinataire(rs.getString(4));
                d.setRib_destinataire(rs.getString(5));
                d.setMontant(rs.getInt(6));
                d.setBackgroundcolor(rs.getString(7));
                d.setDatedebut(rs.getDate(8));
                d.setCategorie_depense(rs.getString(9));
                d.setType_depense(rs.getString(10));
                
                mylist.add(d);
                
            }
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        return mylist;
        
        
    }
        public List<depenses> afficherlesDepensesCalendar(){
        List<depenses> mylist = new ArrayList();
        try {
            
            String rq="SELECT * FROM depenses";
            Statement st =cnx2.createStatement();
            ResultSet rs= st.executeQuery(rq);
            while(rs.next()){
                depenses d =new depenses();
                d.setId_depense(rs.getInt(1));
                d.setIdbudget_id(rs.getInt(2));
                d.setTitle(rs.getString(3));
                d.setPrenom_destinataire(rs.getString(4));
                d.setRib_destinataire(rs.getString(5));
                d.setMontant(rs.getInt(6));
                d.setBackgroundcolor(rs.getString(7));
                d.setDatedebut(rs.getDate(8));
                d.setCategorie_depense(rs.getString(9));
                d.setType_depense(rs.getString(10));
                
                mylist.add(d);
                
            }
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        return mylist;
        
        
    }
    public void modifierDepense(depenses dep) {
  
    try {
        String requete = "UPDATE depenses SET idbudget_id =?,title=?,prenom_destinataire=?,rib_destinataire=?, montant=?,backgroundcolor=?, datedebut=?, categorie_depense=?,type_depense=? WHERE id_depense=?";
        PreparedStatement ps = cnx2.prepareStatement(requete);
        ps.setInt(1, dep.getIdbudget_id());
        ps.setString(2,dep.getTitle());
        ps.setString(3,dep.getPrenom_destinataire());
        ps.setString(4,dep.getRib_destinataire());
        ps.setInt(5,dep.getMontant());
        ps.setString(6,dep.getBackgroundcolor());
        ps.setTimestamp(7,new Timestamp(dep.getDatedebut().getTime()));
        ps.setString(8,dep.getCategorie_depense());
        ps.setString(9,dep.getType_depense());
        ps.setInt(10, dep.getId_depense());
        ps.executeUpdate();
        System.out.println("Le dépense a été modifié avec succès !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
public void supprimerDepense(int id){
 
    try{
        String requete = "DELETE FROM depenses WHERE id_depense =?";
        PreparedStatement ps = cnx2.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Le dépense a été supprimé avec succès !");
        
    }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }

} 
public int sumDepense(int id) {
    int countData = 0;
    try {
        String rq = "SELECT SUM(montant) FROM depenses WHERE id_depense = ?";
        PreparedStatement st = cnx2.prepareStatement(rq);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            countData = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return countData;
}

public Map<String, Double> getSumByCategory() {
    Map<String, Double> sumByCategory = new HashMap<>();
    try {
        String rq = "SELECT categorie_depense, SUM(montant) FROM depenses GROUP BY categorie_depense";
        PreparedStatement st = cnx2.prepareStatement(rq);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String category = rs.getString("categorie_depense");
            Double sum = rs.getDouble(2);
            sumByCategory.put(category, sum);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return sumByCategory;
}
public Map<String, Double> getPercentageByTypeDepense() {
    Map<String, Double> percentageByTypeDepense = new HashMap<>();
    double total = 0.0;
    String rq = "SELECT type_depense, SUM(montant) AS total FROM depenses GROUP BY type_depense";
    try  {
        PreparedStatement st = cnx2.prepareStatement(rq);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String typeDepense = rs.getString("type_depense");
            double montant = rs.getDouble("total");
            percentageByTypeDepense.put(typeDepense, montant);
            total += montant;
        }
        for (Map.Entry<String, Double> entry : percentageByTypeDepense.entrySet()) {
            String typeDepense = entry.getKey();
            double montant = entry.getValue();
            double percentage = (montant / total) * 100.0;
            percentageByTypeDepense.put(typeDepense, percentage);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return percentageByTypeDepense;
}
public void sendMail(String recipient, String subject, String message) {
    final String username = "healthified.consultation.module@gmail.com"; // votre adresse e-mail
    final String password = "cqdebkknidkqytzj"; // votre mot de passe

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com"); // ou autre serveur SMTP
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.ssl.protocols","TLSv1.2");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
        System.out.println("Le message a été envoyé avec succès.");
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
public void transferDepenses() throws ParseException {
    LocalDate systemDate = LocalDate.now();

    List<Integer> depensesToRemove = new ArrayList<>();
     
    try  {
         String rq = "SELECT * FROM depenses WHERE DATE(datedebut) = ? ";
         PreparedStatement st = cnx2.prepareStatement(rq);
         st.setString(1, systemDate.toString());
        ResultSet rs = st.executeQuery();
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
            depenses depense = new depenses();
            depense.setId_depense(rs.getInt("id_depense"));
            depense.setTitle(rs.getString("title"));
            depense.setMontant(rs.getInt("montant"));
            depense.setRib_destinataire(rs.getString("rib_destinataire"));
            depense.setPrenom_destinataire(rs.getString("prenom_destinataire"));
            depense.setDatedebut(rs.getTimestamp("datedebut"));

            virement vir = new virement();
            vir.setMontant(depense.getMontant());
            vir.setDate_virement(depense.getDatedebut());
            vir.setTitre(depense.getTitle());
            vir.setPrenom_benef(depense.getPrenom_destinataire());
            vir.setRib_benef(depense.getRib_destinataire());
            VirementService VC = new VirementService();
            VC.AjouterVir(vir);
            depensesToRemove.add(depense.getId_depense());
            String to = "montassar.mchabet@esprit.tn";
            String subject = "BANK-IN";
            String body = "Votre dépense d'id :"+depense.getId_depense()+" et de montant :"+depense.getMontant()+"à etait transmettre avec succées  ";
            sendMail(to, subject, body);

            
        }
     
    } catch (SQLException e) {
        e.printStackTrace();

    }

    for (int depense : depensesToRemove) {
        System.out.println(""+depense);
         supprimerDepense(depense);
    }
}

}