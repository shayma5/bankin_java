/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.util.Date;
/**
 *
 * @author sahra
 */
public class virement {
    private int id_virement  ;
    private double montant;
    private Date date_virement;
    private String titre;
    private String prenom_benef;
    private String rib_benef;
    private int beneficiaire_id;
   

    public virement() {
    }
    public virement(double montant) {
        this.montant = montant;
    }

    public virement(double montant, Date date_virement, String titre, String prenom_benef, String rib_benef, int beneficiaire_id) {
        this.montant = montant;
        this.date_virement = date_virement;
        this.titre = titre;
        this.prenom_benef = prenom_benef;
        this.rib_benef = rib_benef;
        this.beneficiaire_id = beneficiaire_id;
    }

    public virement(int id_virement, double montant, Date date_virement, String titre, String prenom_benef, String rib_benef, int beneficiaire_id) {
        this.id_virement = id_virement;
        this.montant = montant;
        this.date_virement = date_virement;
        this.titre = titre;
        this.prenom_benef = prenom_benef;
        this.rib_benef = rib_benef;
        this.beneficiaire_id = beneficiaire_id;
    }

    public int getId_virement() {
        return id_virement;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate_virement() {
        return date_virement;
    }

    public String getTitre() {
        return titre;
    }

    public String getPrenom_benef() {
        return prenom_benef;
    }

    public String getRib_benef() {
        return rib_benef;
    }

    public int getBeneficiaire_id() {
        return beneficiaire_id;
    }

    public void setId_virement(int id_virement) {
        this.id_virement = id_virement;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate_virement(Date date_virement) {
        this.date_virement = date_virement;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrenom_benef(String prenom_benef) {
        this.prenom_benef = prenom_benef;
    }

    public void setRib_benef(String rib_benef) {
        this.rib_benef = rib_benef;
    }

    public void setBeneficiaire_id(int beneficiaire_id) {
        this.beneficiaire_id = beneficiaire_id;
    }


    
    
    public String toString() {
        //return "virement{" + "id_virement=" + id_virement + ", montant=" + montant + ", date_virement=" + date_virement + ", titre=" + titre + ", prenom_benef=" + prenom_benef + ", rib_benef=" + rib_benef + ", beneficiaire_id="+beneficiaire_id+'}';
     return String.format( "%-20s",id_virement)
                + String.format( "%-20s",montant)
                + String.format( "%-20s",date_virement)
                + String.format( "%-20s",titre)
                + String.format( "%-20s",prenom_benef)
                + String.format( "%-20s",rib_benef)
                + String.format( "%-20s",beneficiaire_id);
    }
    
}
