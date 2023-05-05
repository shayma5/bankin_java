/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Koussay
 */
public class Abonnement {

    public Abonnement(String Nom, int Prix) {
        this.Nom = Nom;
        this.Prix = Prix;
    }
    public Abonnement() {
    }
    private int id_b;
    private String Nom;
    private int Prix;

    public Abonnement(int id_b, String Nom, int Prix) {
        this.id_b = id_b;
        this.Nom = Nom;
        this.Prix = Prix;
    }
    

    
    
    public int getId_b() {
        return id_b;
    }

    public String getNom() {
        return Nom;
    }

    public int getPrix() {
        return Prix;
    }

   

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.Nom);
        hash = 23 * hash + this.Prix;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Abonnement other = (Abonnement) obj;
        if (this.Prix != other.Prix) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "Nom=" + Nom + ", Prix=" + Prix + '}';
    }
    
}
