/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Entities.virement;
import Entities.beneficiaire;
import java.util.List;

/**
 *
 * @author sahra
 */
public interface IServiceBeneficiaire<T> {
    public void AjouterBen(beneficiaire b) ;
    public List<beneficiaire> afficher();
    public void Supprimer(int id_benef);
    public void modifierBen(beneficiaire b) ;
    
}
