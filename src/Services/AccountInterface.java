/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Entities.Account;
import javafx.collections.ObservableList;

/**
 *
 * @author Koussay
 */
public interface AccountInterface {
    public void ajouterAccount(Account a);
     public void supprimerAccount(Account a);
     public ObservableList<Account> afficherAccounts();
}
