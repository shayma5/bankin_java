/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Sexe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Koussay
 */
/*public class ListSexe {

public class ListGouvernorat {

	ObservableList<Sexe> listSexe = FXCollections.observableArrayList();

	public ListGouvernorat() {
		listSexe.add(new Sexe("Homme","Femme"));
		
	}

	public ObservableList<Sexe> getListGouvernorats() {
		return listSexe;
               
	}
        
    }

}*/
public class ListSexe {
    public static ObservableList<Sexe> getListSexe() {
        ObservableList<Sexe> sexes = FXCollections.observableArrayList();
        sexes.add(new Sexe("Homme"));
        sexes.add(new Sexe("Femme"));
        return sexes;
    }
}
