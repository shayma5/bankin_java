/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.virement;
import ConnectionDB.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import services.IServiceVirement;

/**
 *
 * @author sahra
 */
public class VirementService implements IServiceVirement{

       Connection cnx ;
    PreparedStatement ste; 
   
     public void AjouterVir(virement v){
            
         
         try {
            
            String requete = "INSERT INTO `virement`(`montant`,`date_virement`,`titre`,`prenom_benef`,`rib_benef`,`beneficiaire_id`) VALUES ('"+v.getMontant()+"','"+v.getDate_virement()+"','"+v.getTitre()+"','"+v.getPrenom_benef()+"','"+v.getRib_benef()+"','"+v.getBeneficiaire_id()+"')";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("Virement ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
    
    
     public List<virement> afficherVir(){
         
         List<virement> myList = new ArrayList();

        try {
            String req = "SELECT * FROM virement";
           Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                myList.add(new virement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myList;
    }
     
      public void SupprimerVir(int id_virement){
            
         String requete="DELETE FROM virement WHERE id_virement = ?" ;
         try {
             PreparedStatement pst= MyConnection.getInstance().getConnection().prepareStatement(requete);
             pst.setInt(1, id_virement);
             pst.executeUpdate();
             System.out.println("Virement supprimé !");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());          }
       }
      
    
      public void modifierVir(virement v) {
    try {
        String requete = "UPDATE virement SET id_virement = ?, montant = ?, date_virement = ?, titre = ?, prenom_benef = ?, rib_benef = ?, beneficiaire_id = ? WHERE id_virement = ?";
        PreparedStatement ps = MyConnection.getInstance().getConnection().prepareStatement(requete);
        ps.setInt(1, v.getId_virement());
        ps.setDouble(2, v.getMontant());
        //ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(v.getDate_virement())); // convert date to string
        ps.setDate(3, (java.sql.Date) v.getDate_virement());
        ps.setString(4, v.getTitre());
        ps.setString(5, v.getPrenom_benef());
        ps.setString(6, v.getRib_benef());
        ps.setInt(7, v.getBeneficiaire_id());
        ps.setInt(8, v.getId_virement());
        ps.executeUpdate();
        System.out.println("Virement modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


     
}


