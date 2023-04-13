/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.Etatpret;
import Interfaces.IEtatpret;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class EtatpretServices implements IEtatpret{
Connection myConn = MyConnection.getInstance().getConnection();
    @Override
    public void modifierEtat(Etatpret e) {
        try {
			String sql = "UPDATE etatpret JOIN pret ON etatpret.pret_id = pret.id set etat=? WHERE etatpret.id=?";
			PreparedStatement ste = myConn.prepareStatement(sql);
			ste.setString(1, e.getEtat());
                        ste.setInt(2,e.getId());
			int rowsUpdated = ste.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("changed");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
        
      
        
    }

    @Override
    public ObservableList<Etatpret> afficherEtatPret() {
        ObservableList<Etatpret> listeB = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM etatpret";
			Statement statement = myConn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			int count = 0;
			while (result.next()) {
				int id = result.getInt("id");
				int pret_id = result.getInt("pret_id");
				String etat = result.getString("etat");

				Etatpret e1 = new Etatpret(id,pret_id,etat);
				System.out.println(e1.toString());
				
 
				//listeB.add(e1);
				// String output = "boutique %d : %s | %s | %s | %s | %d | %d | %s | %s ";
				// System.out.println(String.format(output, ++count, nom, email,
				// lien,description,num_telephone,num_fixe,governerat,ville));
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return listeB;

	}
    public Etatpret getEtatPretByPretId(int pretId) {
    Etatpret etat = null;
    try {
        String sql = "SELECT * FROM etatpret WHERE pret_id=?";
        PreparedStatement ste = myConn.prepareStatement(sql);
        ste.setInt(1, pretId);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            etat = new Etatpret();
            etat.setId(rs.getInt("id"));
            etat.setPret_id(rs.getInt("pret_id"));
            etat.setEtat(rs.getString("etat"));
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return etat;
}

    
    
    
    
   
}

        
       
    
    
