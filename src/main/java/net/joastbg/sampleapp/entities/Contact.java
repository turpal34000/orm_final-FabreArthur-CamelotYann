package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CONTACT")
public class Contact implements Serializable{
    
    @Id
    /*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTACT_SEQUENCE")
    @SequenceGenerator(
        name="CONTACT_SEQUENCE",
	sequenceName="CONTACT_SEQ"
    ) */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idContact;
    
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "idClient")
    private Client client;
    
    @Enumerated(EnumType.STRING)
    private TypeContact cType;
    
    private String valeur;
    
    public Contact(){
        
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client idClient) {
        this.client = idClient;
    }

    public TypeContact getcType() {
        return cType;
    }

    public void setcType(TypeContact cType) {
        this.cType = cType;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
    
    public Contact (Client client, TypeContact cType, String valeur) throws IllegalArgumentException{
        if (client == null || cType==null || valeur.isEmpty() || valeur == null) {
            throw new IllegalArgumentException("[Contacts] One of the parameter is null or empty");
	}
        this.setClient(client);
        this.setValeur(valeur);
        this.setcType(cType);
    }
}
