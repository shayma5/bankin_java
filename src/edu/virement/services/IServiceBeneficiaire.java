/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virement.services;
import edu.virement.entities.virement;
import edu.virement.entities.beneficiaire;
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
