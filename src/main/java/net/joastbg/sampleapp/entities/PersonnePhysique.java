package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "PersonnePhysique")
@DiscriminatorValue("PP")
public class PersonnePhysique extends Client implements Serializable{
    //Variables
    private String dateNaissance;
    
    private String prenom;
    
    //Constructeurs
    public PersonnePhysique(){}
    
    public PersonnePhysique(String nom,String prenom,String dateNaissance) throws IllegalArgumentException{
        super();
        if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || dateNaissance == null || dateNaissance.isEmpty()) {
            throw new IllegalArgumentException("[Client] One of the parameter is null or empty");
	}
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }
    
    //Getters et Setters
    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }    
}
