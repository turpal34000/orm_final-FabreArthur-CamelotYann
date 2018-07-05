package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="COMPTEBANCAIRE")
public class CompteBancaire implements Serializable {

    //Variables
    @Id
    private String iban ;
    
    @ManyToOne
    @JoinColumn(name = "proprietaire", referencedColumnName = "idClient")  
    private Client proprietaire;
    
    private String bic ;

    //Constructors
    public CompteBancaire() {
		
    }
    
    public CompteBancaire(String iban, String bic) {
	this.iban=iban;
        this.bic=bic;
    }
    
    //Getters and Setters
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

}
