/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Pret {
    int id;
    double montant;
    String raison;
    String poste;
    Date debut_travail;
    double revenu;
    int durée;
    double taux;
    String etat;

    public Pret(int id, double montant, String raison, String poste, Date debut_travail, double revenu, int durée, double taux, String etat) {
        this.id = id;
        this.montant = montant;
        this.raison = raison;
        this.poste = poste;
        this.debut_travail = debut_travail;
        this.revenu = revenu;
        this.durée = durée;
        this.taux = taux;
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

    

    public Pret(int id, double montant, String raison, String poste, Date debut_travail, double revenu, int durée, double taux) {
        this.id = id;
        this.montant = montant;
        this.raison = raison;
        this.poste = poste;
        this.debut_travail = debut_travail;
        this.revenu = revenu;
        this.durée = durée;
        this.taux = taux;
    }

    public Pret(double montant, String raison, String poste, Date debut_travail, double revenu, int durée, double taux) {
        this.montant = montant;
        this.raison = raison;
        this.poste = poste;
        this.debut_travail = debut_travail;
        this.revenu = revenu;
        this.durée = durée;
        this.taux = taux;
    }

    public Pret() {
    }
    


    

    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public String getRaison() {
        return raison;
    }

    public String getPoste() {
        return poste;
    }

    public Date getDebut_travail() {
        return debut_travail;
    }

    public double getRevenu() {
        return revenu;
    }

    public int getDurée() {
        return durée;
    }

    public double getTaux() {
        return taux;
    }


    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setDebut_travail(Date debut_travail) {
        this.debut_travail = debut_travail;
    }

    public void setRevenu(double revenu) {
        this.revenu = revenu;
    }

    public void setDurée(int durée) {
        this.durée = durée;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "Pret{" + "id=" + id + ", montant=" + montant + ", raison=" + raison + ", poste=" + poste + ", debut_travail=" + debut_travail + ", revenu=" + revenu + ", dur\u00e9e=" + durée + ", taux=" + taux + ", etat=" + etat + '}';
    }


    

   

   

    
    
    
    
    
    
    
    
    
}
