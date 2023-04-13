/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.budget;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class budgetCRUD {
    Connection cnx2;
    public budgetCRUD(){
        cnx2 = MyConnection.getInstance().getConnection();
    }
    public void ajouterbudget() throws ParseException{
        try {
             
            String requete ="INSERT INTO budget(id_account_id,montant,datedebut,datefin)"+ "VALUES (1, 5000, ?, ?)";
            PreparedStatement ps = cnx2.prepareStatement(requete);
            

        // parse the date strings and set the date parameters
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date datedebut = dateFormat.parse("31/03/2023");
            java.util.Date datefin = dateFormat.parse("30/04/2023"); 

             ps.setTimestamp(1, new Timestamp(datedebut.getTime()));
             ps.setTimestamp(2, new Timestamp(datefin.getTime()));
            
            //Statement st= cnx2.createStatement();
            ps.executeUpdate();
            System.out.println("budget ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void ajouterbudget2(budget b) throws ParseException{
        try {
             
            String requete2 ="INSERT INTO budget(id_account_id,montant,datedebut,datefin)"+ "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx2.prepareStatement(requete2);
            ps.setInt(1, b.getId_account_id()); 
            ps.setDouble(2, b.getMontant());
            

        
             ps.setTimestamp(3, new Timestamp(b.getDatedebut().getTime()));
             ps.setTimestamp(4, new Timestamp(b.getDatefin().getTime()));
             //ps.setTimestamp(3, new Timestamp(datedebut.getTime()));
             //ps.setTimestamp(4, new Timestamp(datefin.getTime()));
            
            //Statement st= cnx2.createStatement();
            ps.executeUpdate();
            System.out.println("budget ajoutée 2 ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ObservableList<budget> afficherlesbudgets(){
        ObservableList<budget> mylist = FXCollections.observableArrayList();
        try {
            
            String rq="SELECT * FROM budget";
            Statement st =cnx2.createStatement();
            ResultSet rs= st.executeQuery(rq);
            while(rs.next()){
                budget bd =new budget();
                bd.setId(rs.getInt(1));
                bd.setId_account_id(rs.getInt(2));
                bd.setMontant(rs.getDouble(3));
                bd.setDatedebut(rs.getDate(4));
                bd.setDatefin(rs.getDate(5));
                mylist.add(bd);
                
            }
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        return mylist;
        
        
    }
    public void modifierDubget(budget b) {
    

   
    try {
        String requete = "UPDATE budget SET id_account_id=?, montant=?, datedebut=?, datefin=? WHERE id=?";
        PreparedStatement ps = cnx2.prepareStatement(requete);
        ps.setInt(1, b.getId_account_id());
        ps.setDouble(2,b.getMontant());
        ps.setTimestamp(3, new Timestamp(b.getDatedebut().getTime()));
        ps.setTimestamp(4,new Timestamp(b.getDatefin().getTime()));
        
        ps.setInt(5, b.getId());
        ps.executeUpdate();
        System.out.println("Le Dubget a été modifié avec succès !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
public void supprimerbudget(int id){
    
    try{
        String requete = "DELETE FROM budget WHERE id=?";
        PreparedStatement ps = cnx2.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Le Dubget a été supprimé avec succès !");
        
    }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }

}    
}
    
    
