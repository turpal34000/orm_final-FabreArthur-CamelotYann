package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "AssuranceAuto")
@DiscriminatorValue("AA")
public class AssuranceAuto extends Assurance implements Serializable{
    //Variables
    private String immatriculation;
    private Integer bonusMalus;
    
    //Getter et Setters
    public String getImmatriculation() {
        return immatriculation;
    }
    
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    
    public Integer getBonusMalus() {
        return bonusMalus;
    }
    
    public void setBonusMalus(Integer bonusMalus) {
        this.bonusMalus = bonusMalus;
    }
    
    //Contructeur
    public AssuranceAuto(){}
    public AssuranceAuto(Date dateSouscription,Date dateAnniversaire,Date datePrelevement, Client idAssure, String immatriculation, Integer bonusMalus) throws IllegalArgumentException{
        if (dateSouscription==null || dateAnniversaire==null || datePrelevement==null || idAssure==null || immatriculation.isEmpty() || immatriculation==null || bonusMalus==null) {
            throw new IllegalArgumentException("[Assurance] One of the parameter is null or empty");
	}
        this.setBonusMalus(bonusMalus);
        this.setDateAnniversaire(dateAnniversaire);
        this.setDatePrelevement(datePrelevement);
        this.setDateSouscription(dateSouscription);
        this.setIdAssure(idAssure);
        this.setImmatriculation(immatriculation);
    }
}
