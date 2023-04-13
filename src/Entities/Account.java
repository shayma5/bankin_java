/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Koussay
 */
public class Account {

    public Account() {
    }
    
    private int id_a;
    private String NomComplet;
    private int NumTel;
    private String email;
    private String Sexe;
    private String DateNaiss;
    private String Adresse;
    private String Ville;
    private String brochure_filename ;
    private int Solde;

    public Account(int id_a, String NomComplet, int NumTel, String email, String Sexe, String DateNaiss, String Adresse, String Ville, String brochure_filename	, int Solde) {
        this.id_a = id_a;
        this.NomComplet = NomComplet;
        this.NumTel = NumTel;
        this.email = email;
        this.Sexe = Sexe;
        this.DateNaiss = DateNaiss;
        this.Adresse = Adresse;
        this.Ville = Ville;
        this.brochure_filename	 = brochure_filename	;
        this.Solde = Solde;
    }

    public Account(String NomComplet, int NumTel, String email, String Sexe, String DateNaiss, String Adresse, String Ville, String brochure_filename	, int Solde) {
        this.NomComplet = NomComplet;
        this.NumTel = NumTel;
        this.email = email;
        this.Sexe = Sexe;
        this.DateNaiss = DateNaiss;
        this.Adresse = Adresse;
        this.Ville = Ville;
        this.brochure_filename	 = brochure_filename	;
        this.Solde = Solde;
    }

   

   

    public int getId_a() {
        return id_a;
    }

    public String getNomComplet() {
        return NomComplet;
    }


    public void setNomComplet(String NomComplet) {
        this.NomComplet = NomComplet;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setDateNaiss(String DateNaiss) {
        this.DateNaiss = DateNaiss;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    

    public void setSolde(int Solde) {
        this.Solde = Solde;
    }

    public int getNumTel() {
        return NumTel;
    }

    public String getEmail() {
        return email;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getVille() {
        return Ville;
    }


    public int getSolde() {
        return Solde;
    }

    @Override
    public String toString() {
        return "Account{" + "id_a=" + id_a + ", NomComplet=" + NomComplet + ", NumTel=" + NumTel + ", email=" + email + ", Sexe=" + Sexe + ", DateNaiss=" + DateNaiss + ", Adresse=" + Adresse + ", Ville=" + Ville + ", brochure_filename=" + brochure_filename + ", Solde=" + Solde + '}';
    }

    public void setBrochure_filename(String brochure_filename) {
        this.brochure_filename = brochure_filename;
    }

    public String getBrochure_filename() {
        return brochure_filename;
    }


   
    
}
