/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Logger;

/**
 *
 * @author Koussay
 */
public class Service {
    String Nom;
    String Description;

    public Service() {
    }

    public Service(String Nom, String Description) {
        this.Nom = Nom;
        this.Description = Description;
    }
    private static final Logger LOG = Logger.getLogger(Service.class.getName());

    public String getNom() {
        return Nom;
    }

    public String getDescription() {
        return Description;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Service{" + "Nom=" + Nom + ", Description=" + Description + '}';
    }
    
}
