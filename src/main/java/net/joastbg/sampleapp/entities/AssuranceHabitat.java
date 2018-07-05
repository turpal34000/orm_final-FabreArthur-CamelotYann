package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "AssuranceHabitat")
@DiscriminatorValue("AH")
public class AssuranceHabitat extends Assurance implements Serializable{
    //Variables
    private String adresseAssuree;
    private Integer valeurCouverture;
    
    //Getter et Setters
    public String getAdresseAssuree() {
        return adresseAssuree;
    }

    public void setAdresseAssuree(String adresseAssuree) {
        this.adresseAssuree = adresseAssuree;
    }

    public Integer getValeurCouverture() {
        return valeurCouverture;
    }

    public void setValeurCouverture(Integer valeurCouverture) {
        this.valeurCouverture = valeurCouverture;
    }
    
   //Contructeur
    public AssuranceHabitat(){}
    public AssuranceHabitat(Date dateSouscription,Date dateAnniversaire,Date datePrelevement, Client idAssure, String adresseAssuree, Integer valeurCouverture) throws IllegalArgumentException{
        if (dateSouscription==null || dateAnniversaire==null || datePrelevement==null || idAssure==null || adresseAssuree.isEmpty() || adresseAssuree==null || valeurCouverture==null) {
            throw new IllegalArgumentException("[Assurance] One of the parameter is null or empty");
	}
        this.setDateAnniversaire(dateAnniversaire);
        this.setDatePrelevement(datePrelevement);
        this.setDateSouscription(dateSouscription);
        this.setIdAssure(idAssure);
        this.setAdresseAssuree(adresseAssuree);
        this.setValeurCouverture(valeurCouverture);
    }    
}
