/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.Abonnement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Koussay
 */
public class AbonnementServices implements Services.AbonnementInterface {
    	Connection myConn = MyConnection.getInstance().getConnection();

        @Override
        public void ajouterAbonnement(Abonnement ab) {

		String sql = "INSERT INTO `Abonnement`(`id`,`nom`,`prix`) VALUES (?, ?, ?)";
		try {
			PreparedStatement ste = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ste.setInt(1, ab.getId_b());
			ste.setString(2, ab.getNom());
			ste.setInt(3, ab.getPrix());
			ste.executeUpdate();

			//System.out.println("Nouvelle Abonnement ajoutée avec succès!");

	
        System.out.println("A new Abonnement was inserted successfully!");
                }
                

		catch (SQLException ex) {
			System.out.println(ex);
		}
	}
        
        @Override
	public void supprimerAbonnement(Abonnement ab) {
		try {

			String sql = "DELETE FROM `Abonnement` WHERE id=?";
			PreparedStatement statement = myConn.prepareStatement(sql);
			statement.setInt(1,ab.getId_b());
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Une Abonnement a été supprimé avec succès !");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	@Override
	public ObservableList<Abonnement> afficherAbonnements() {
		ObservableList<Abonnement> listeB = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM Abonnement";
			Statement statement = myConn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			int count = 0;
			while (result.next()) {
				int id_b = result.getInt("id");
				String Nom = result.getString("nom");
				int Prix = result.getInt("prix");
				Abonnement b1 = new Abonnement(id_b, Nom, Prix);
				System.out.println(b1.toString());
				listeB.add(b1);
				
			}

		} 
                
                catch (SQLException ex) {
			System.out.println(ex);
		}

		return listeB;

	}

    @Override
    public void modifierAbonnement(Abonnement ab) {
                try {
        String sql="UPDATE abonnement SET nom=?, prix=? WHERE id = ?";
        PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setString(1,ab.getNom());
            ste.setFloat(2,ab.getPrix());
            ste.setInt(3,ab.getId_b());
            
            int rowsUpdated = ste.executeUpdate();
         if (rowsUpdated > 0) {
    System.out.println("Une abonnement existante a été mis à jour avec succès !");
}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
