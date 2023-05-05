/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnectionDB.MyConnection;
import Entities.Account;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Koussay
 */
public class AccountServices implements Services.AccountInterface {
	Connection myConn = MyConnection.getInstance().getConnection();

	@Override
	public void ajouterAccount(Account a) {

		String sql = "INSERT INTO `Account`(`id`,`nom_complet`, `num_tel`, `email`, `sexe`, `date_naiss`, `adresse`, `ville`, `brochure_filename`, `solde`)VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ste = myConn.prepareStatement(sql);
                        ste.setInt(1, a.getId_a());
			ste.setString(2, a.getNomComplet());
			ste.setInt(3, a.getNumTel());
			ste.setString(4, a.getEmail());
			ste.setString(5, a.getSexe());
			ste.setString(6, a.getDateNaiss());
			ste.setString(7, a.getAdresse());
			ste.setString(8, a.getVille());
			ste.setString(9, a.getBrochure_filename());
			ste.setInt(10, a.getSolde());
			ste.executeUpdate();

			System.out.println("A new account was inserted successfully!");
			

		}

		catch (SQLException ex) {
			System.out.println(ex);
		}
	}


	@Override
	public void supprimerAccount(Account a) {
		try {

			String sql = "DELETE FROM `Account` WHERE id=?";
			PreparedStatement statement = myConn.prepareStatement(sql);
			statement.setInt(1,a.getId_a());
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Une account a été supprimé avec succès !");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	@Override
	public ObservableList<Account> afficherAccounts() {
		ObservableList<Account> listeB = FXCollections.observableArrayList();
		try {
			String sql = "SELECT * FROM account";
			Statement statement = myConn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			
			while (result.next()) {
				int id_a = result.getInt("id");
				String NomComplet = result.getString("nom_complet");
                                int num_tel = result.getInt("num_tel");
				String Email = result.getString("email");
				String Sexe = result.getString("sexe");
				String DateNaiss = result.getString("date_naiss");
				String Adresse = result.getString("adresse");
				String Ville = result.getString("ville");
				String brochure_filename = result.getString("brochure_filename");
				int Solde = result.getInt("solde");
				Account a1 = new Account(NomComplet, num_tel, Email, Sexe, DateNaiss, Adresse, Ville, brochure_filename, Solde);
				System.out.println(a1.toString());
				listeB.add(a1);
				
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return listeB;

	}
        

    public void modifierEtat(int etat ) {
        try {
			String sql = "UPDATE account set etat=? WHERE id=?";
			PreparedStatement ste = myConn.prepareStatement(sql);
			ste.setInt(1,etat);
                        ste.setInt(2,47);
			int rowsUpdated = ste.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("changed");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
    }
}


