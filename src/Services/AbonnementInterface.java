/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import javafx.collections.ObservableList;

/**
 *
 * @author Koussay
 */
public interface AbonnementInterface {
    public void ajouterAbonnement(Abonnement ab);
     public void supprimerAbonnement(Abonnement ab);
     public void modifierAbonnement(Abonnement ab);
     public ObservableList<Abonnement> afficherAbonnements();
    
}
