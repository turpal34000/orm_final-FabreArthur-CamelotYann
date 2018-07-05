package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "PersonneMorale")
@DiscriminatorValue("PM")
public class PersonneMorale extends Client implements Serializable{
    //Variables
    private String siren;
    
    //Constructeurs
    public PersonneMorale(){}
    
    public PersonneMorale(String nom,String siren) throws IllegalArgumentException{
        if (nom == null || nom.isEmpty() || siren == null || siren.isEmpty()) {
            throw new IllegalArgumentException("[Client] One of the parameter is null or empty");
	}
        this.setNom(nom);
        this.setSiren(siren);
    }
    
    //Getters et Setters
    public String getSiren() {
        return siren;
    }
    
    public void setSiren(String siren) {
        this.siren = siren;
    }
}
