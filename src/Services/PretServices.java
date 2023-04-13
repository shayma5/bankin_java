/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.Etatpret;
import Entities.Pret;
import Interfaces.IPretCrud;
import java.sql.Connection;
import java.sql.Date;
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
public class PretServices implements IPretCrud {
    	Connection myConn = MyConnection.getInstance().getConnection();


    @Override
    public void ajouterPret(Pret p) {
    String sql = "INSERT INTO pret (montant, raison, poste, debut_travail, revenu, duree, taux) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement statement = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //statement.setInt(1, p.getId());
        statement.setDouble(1, p.getMontant());
        statement.setString(2, p.getRaison());
        statement.setString(3, p.getPoste());
        statement.setDate(4, p.getDebut_travail());
        statement.setDouble(5, p.getRevenu());
        statement.setInt(6, p.getDurée());
        statement.setDouble(7, p.getTaux());
        statement.executeUpdate();
        
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idPret = generatedKeys.getInt(1);
            String sql2 = "INSERT INTO etatpret (pret_id, etat,admin,remarque) VALUES (?, ?, ?,?)";
            PreparedStatement statement2 = myConn.prepareStatement(sql2);
            statement2.setInt(1, idPret);
            statement2.setString(2, "en cours");
            statement2.setString(3, null);
            statement2.setString(4, null);
            statement2.executeUpdate();
        }
        
        System.out.println("A new pret was inserted successfully!");
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    

    @Override
   public ObservableList<Pret> afficherPret() {
    ObservableList<Pret> listeP = FXCollections.observableArrayList();
    try {
        String sql = "SELECT p.*, e.etat FROM pret p JOIN etatpret e ON p.id = e.pret_id";
        Statement statement = myConn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            Double montant = result.getDouble("montant");
            String raison = result.getString("raison");
            String poste = result.getString("poste");
            Date debut_travail = result.getDate("debut_travail");
            Double revenu = result.getDouble("revenu");
            int duree = result.getInt("duree");
            Double taux = result.getDouble("taux");
            String etat = result.getString("etat");
            Pret p1 = new Pret(id, montant, raison, poste, debut_travail, revenu, duree, taux, etat);
            listeP.add(p1);
            System.out.println(p1.toString());
            //System.out.println(etat);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return listeP;
}

    @Override
    public void supprimePret(Pret p) {
        
        try {

			String sql = "DELETE pret FROM pret JOIN etatpret ON pret.id=etatpret.pret_id WHERE pret.id=?";
			PreparedStatement statement = myConn.prepareStatement(sql);
			statement.setInt(1, p.getId());
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Un pret a été supprimé avec succès !");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
        
        
        
    }
       
    
    
}
