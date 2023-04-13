/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virement.tests;

import edu.virement.entities.beneficiaire;
import edu.virement.services.BeneficiaireService;
import edu.virement.entities.virement;
import edu.virement.services.VirementService;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
/**
 *
 * @author sahra
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        java.sql.Date sqlDate3 = new java.sql.Date(df.parse("22-01-2024").getTime());
        
        BeneficiaireService b = new BeneficiaireService();
        VirementService v = new VirementService();
        //beneficiaire b1 = new beneficiaire("test","test","test","test");
        //b.AjouterBen(b1);
        //b.afficher();
        //System.out.println( b.afficher().toString());
        // b.Supprimer(10);
        // beneficiaire b2 = new beneficiaire(11,"test","test","test","test");
        //b.modifierBen(b2);
        //b.modifierBen(new beneficiaire(9,"testtt","ttestt","ttetst","tgtest"));
        //virement v2 = new virement(9595.22,sqlDate3,"test","test","test",11);
        //v.AjouterVir(v2);

        //v.afficherVir();
        //System.out.println( v.afficherVir().toString());
        //v.SupprimerVir(13);
        //v.afficherVir();
        //System.out.println(v.afficherVir().toString());

        //v.modifierVir(new virement(15,9595.22,sqlDate3,"tesssst","testttt","teeeest",11));
        
    }

}
