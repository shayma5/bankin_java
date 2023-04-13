/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Pret;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public interface IPretCrud {
    public void ajouterPret(Pret p);
    public void supprimePret(Pret p);
     public ObservableList<Pret> afficherPret();
    
}
