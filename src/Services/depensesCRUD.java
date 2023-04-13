/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.depenses;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public void modifierDepense(depenses dep) {
  
    try {
        String requete = "UPDATE depenses SET idbudget_id =?,title=?,prenom_destinataire=?,rib_destinataire=?, montant=?,backgroundcolor=?, datedebut=?, categorie_depense=?,type_depense=? WHERE id_depense=?";
        PreparedStatement ps = cnx2.prepareStatement(requete);
        ps.setInt(1, dep.getId_depense());
        ps.setString(2,dep.getTitle());
        ps.setString(3,dep.getPrenom_destinataire());
        ps.setString(4,dep.getRib_destinataire());
        ps.setInt(5,dep.getMontant());
        ps.setString(6,dep.getBackgroundcolor());
        ps.setTimestamp(7,new Timestamp(dep.getDatedebut().getTime()));
        ps.setString(8,dep.getCategorie_depense());
        ps.setString(9,dep.getType_depense());
        ps.setInt(10, dep.getIdbudget_id());
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
    
}
