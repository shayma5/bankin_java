/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author montassar
 */
public class budget {
    private int id;
    private int id_account_id ;
    private double montant;
    private Date datedebut;
    private Date datefin ;

    public budget() {
    }
    
    public budget(int id, int id_account_id, double montant, Date datedebut, Date datefin) {
        this.id = id;
        this.id_account_id = id_account_id;
        this.montant = montant;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public budget(int id_account_id, double montant, Date datedebut, Date datefin) {
        this.id_account_id = id_account_id;
        this.montant = montant;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_account_id() {
        return id_account_id;
    }

    public void setId_account_id(int id_account_id) {
        this.id_account_id = id_account_id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

   

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

  

    @Override
    public String toString() {
        return "budget{" + "id=" + id + ", id_account_id=" + id_account_id + ", montant=" + montant + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
    }
    


    
    
    
    
}
