/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Entities.beneficiaire;
import Entities.virement;
import java.util.List;

/**
 *
 * @author sahra
 */
    public interface IServiceVirement<Y> {
    public void AjouterVir(virement v) ;
    public List<virement> afficherVir();
    public void SupprimerVir(int id_virement);
    public void modifierVir(virement v) ;
    
}
