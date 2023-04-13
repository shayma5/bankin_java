/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankin;

import ConnectionDB.MyConnection;
import Entities.budget;
import Entities.depenses;


import Services.budgetCRUD;
import Services.depensesCRUD;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Koussay
 */
public class Bankin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //MyConnection mc = new MyConnection(); 
        // TODO code application logic here
        budgetCRUD pcd = new budgetCRUD();
        depensesCRUD dcd= new depensesCRUD() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date datedebut = dateFormat.parse("31/03/2023");
            java.util.Date datefin = dateFormat.parse("30/04/2023"); 
        budget bd = new budget(12,1,600.0,datedebut,datefin) ;
        depenses dp1 = new depenses(12, "esprit", "esprit", "123333", 500, "#00000", datedebut, "credit", "virement");
        //dcd.ajouterDepense(dp1);
        //System.out.println(dcd.afficherlesDepenses());
        //dcd.modifierDepense();
        //dcd.supprimerDepense();
        //pcd.ajouterbudget2(bd);
        //pcd.modifierDubget();
        //pcd.supprimerbudget();
        //System.out.println(pcd.afficherlesbudgets());
    }
    
    
}
