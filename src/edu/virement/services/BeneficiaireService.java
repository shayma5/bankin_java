/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virement.services;

import edu.virement.entities.beneficiaire;
import edu.virement.utls.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author sahra
 */
public class BeneficiaireService implements IServiceBeneficiaire{

       Connection cnx ;
    PreparedStatement ste; 
   
     public void AjouterBen(beneficiaire b){
            
         
         try {
            
            String requete = "INSERT INTO `beneficiaire`(`rib_benef`,`nom_benef`,`prenom_benef`,`email_benef`) VALUES ('"+b.getRib_benef()+"','"+b.getNom_benef()+"','"+b.getPrenom_benef()+"','"+b.getEmail_benef()+"')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Beneficiaire ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public List<beneficiaire> afficher(){
         
         List<beneficiaire> myList = new ArrayList();

        try {
            String req = "SELECT * FROM beneficiaire";
           Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                myList.add(new beneficiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myList;
    }
     
      public void Supprimer(int id_benef){
            
         String requete="DELETE FROM beneficiaire WHERE id_benef = ?" ;
         try {
             PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.setInt(1, id_benef);
             pst.executeUpdate();
             System.out.println("Beneficiaire supprimée !");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());          }
       }
      
    
       public void modifierBen( beneficiaire b) {
        try {
            String requete = "UPDATE beneficiaire SET id_benef = ?, rib_benef = ?, nom_benef = ? ,prenom_benef = ? ,email_benef = ?  WHERE id_benef = ?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, b.getId_benef());
            ps.setString(2, b.getRib_benef().toString());
            ps.setString(3, b.getNom_benef().toString());
            ps.setString(4,b.getPrenom_benef().toString());
            ps.setString(5,b.getEmail_benef().toString());
            ps.setInt(6, b.getId_benef());
            ps.executeUpdate();
            System.out.println("Beneficiaire modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
    }
        /*public List<beneficiaire> rechercherid(beneficiaire v) {
        List<beneficiaire> myList = new ArrayList();
        return beneficiaire.stream().filter(b -> b.getId_virement()==b.getId_virement()).collect(Collectors.toList());
}*/

}


