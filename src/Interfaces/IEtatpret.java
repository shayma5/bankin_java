/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Etatpret;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public interface IEtatpret {
     public void modifierEtat(Etatpret e);
     //public void supprimePret(Etatpret e);
     public ObservableList<Etatpret> afficherEtatPret();
    
}
