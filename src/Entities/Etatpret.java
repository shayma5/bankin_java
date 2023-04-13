/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class Etatpret {
    int id;
    int pret_id;
    String etat;

    public Etatpret(int id, int pret_id,String etat) {
        this.id = id;
        this.pret_id = pret_id;
        this.etat=etat;
    }

    public Etatpret() {
         this.etat = "en cours";
    }

    public Etatpret(String etat) {
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    

    public int getId() {
        return id;
    }

    public int getPret_id() {
        return pret_id;
    }

    public String getEtat() {
        return etat;
    }

    public void setPret_id(int pret_id) {
        this.pret_id = pret_id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Etatpret{" + "id=" + id + ", pret_id=" + pret_id + ", etat=" + etat + '}';
    }
    
    
    
    
}
