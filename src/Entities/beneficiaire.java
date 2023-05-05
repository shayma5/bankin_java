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
public class beneficiaire {

    public static Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id_benef;
    private String rib_benef;
    private String nom_benef;
    private String prenom_benef;
    private String email_benef;

    public beneficiaire() {
    }

    public beneficiaire(String rib_benef, String nom_benef, String prenom_benef, String email_benef) {
        this.rib_benef = rib_benef;
        this.nom_benef = nom_benef;
        this.prenom_benef = prenom_benef;
        this.email_benef = email_benef;
    }

    public beneficiaire(int id_benef, String rib_benef, String nom_benef, String prenom_benef, String email_benef) {
        this.id_benef = id_benef;
        this.rib_benef = rib_benef;
        this.nom_benef = nom_benef;
        this.prenom_benef = prenom_benef;
        this.email_benef = email_benef;
    }

    public beneficiaire(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_benef() {
        return id_benef;
    }

    public String getRib_benef() {
        return rib_benef;
    }

    public String getNom_benef() {
        return nom_benef;
    }

    public String getPrenom_benef() {
        return prenom_benef;
    }

    public String getEmail_benef() {
        return email_benef;
    }

    public void setId_benef(int id_benef) {
        this.id_benef = id_benef;
    }

    public void setRib_benef(String rib_benef) {
        this.rib_benef = rib_benef;
    }

    public void setNom_benef(String nom_benef) {
        this.nom_benef = nom_benef;
    }

    public void setPrenom_benef(String prenom_benef) {
        this.prenom_benef = prenom_benef;
    }

    public void setEmail_benef(String email_benef) {
        this.email_benef = email_benef;
    }

    public String toString() {
        //return "beneficiaire{" + "id_benef=" + id_benef + ", rib_benef=" + rib_benef + ", nom_benef=" + nom_benef + ", prenom_benef=" + prenom_benef + ", email_benef=" + email_benef + '}';
        return String.format("%-20s", id_benef)
                + String.format("%-20s", rib_benef)
                + String.format("%-20s", nom_benef)
                + String.format("%-20s", prenom_benef)
                + String.format("%-20s", email_benef);

    }
}
