/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author montassar
 */
public class depenses {
    private int id_depense ;
    private int idbudget_id ;
    private String title;
    private String prenom_destinataire;
    private String rib_destinataire;
    private int montant;
    private String backgroundcolor;
    private Date datedebut;
    private String categorie_depense;
    private String type_depense;

    public depenses() {
    }

    public depenses(int idbudget_id, String title, String prenom_destinataire, String rib_destinataire, int montant, String backgroundcolor, Date datedebut, String categorie_depense, String type_depense) {
        this.idbudget_id = idbudget_id;
        this.title = title;
        this.prenom_destinataire = prenom_destinataire;
        this.rib_destinataire = rib_destinataire;
        this.montant = montant;
        this.backgroundcolor = backgroundcolor;
        this.datedebut = datedebut;
        this.categorie_depense = categorie_depense;
        this.type_depense = type_depense;
    }

    public depenses(int id_depense, int idbudget_id, String title, String prenom_destinataire, String rib_destinataire, int montant, String backgroundcolor, Date datedebut, String categorie_depense, String type_depense) {
        this.id_depense = id_depense;
        this.idbudget_id = idbudget_id;
        this.title = title;
        this.prenom_destinataire = prenom_destinataire;
        this.rib_destinataire = rib_destinataire;
        this.montant = montant;
        this.backgroundcolor = backgroundcolor;
        this.datedebut = datedebut;
        this.categorie_depense = categorie_depense;
        this.type_depense = type_depense;
    }

    public int getId_depense() {
        return id_depense;
    }

    public void setId_depense(int id_depense) {
        this.id_depense = id_depense;
    }

    public int getIdbudget_id() {
        return idbudget_id;
    }

    public void setIdbudget_id(int idbudget_id) {
        this.idbudget_id = idbudget_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrenom_destinataire() {
        return prenom_destinataire;
    }

    public void setPrenom_destinataire(String prenom_destinataire) {
        this.prenom_destinataire = prenom_destinataire;
    }

    public String getRib_destinataire() {
        return rib_destinataire;
    }

    public void setRib_destinataire(String rib_destinataire) {
        this.rib_destinataire = rib_destinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public String getCategorie_depense() {
        return categorie_depense;
    }

    public void setCategorie_depense(String categorie_depense) {
        this.categorie_depense = categorie_depense;
    }

    public String getType_depense() {
        return type_depense;
    }

    public void setType_depense(String type_depense) {
        this.type_depense = type_depense;
    }

    @Override
    public String toString() {
        return "depenses{" + "id_depense=" + id_depense + ", idbudget_id=" + idbudget_id + ", title=" + title + ", prenom_destinataire=" + prenom_destinataire + ", rib_destinataire=" + rib_destinataire + ", montant=" + montant + ", backgroundcolor=" + backgroundcolor + ", datedebut=" + datedebut + ", categorie_depense=" + categorie_depense + ", type_depense=" + type_depense + '}';
    }
    
    
    
    
    
    
}
