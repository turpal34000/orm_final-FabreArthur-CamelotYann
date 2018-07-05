package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeClient")
@Table(name="CLIENT")
public class Client implements Serializable {
    //Variables
    @Id
   /* @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_SEQUENCE")
    @SequenceGenerator(
        name="CLIENT_SEQUENCE",
	sequenceName="CLIENT_SEQ"
    ) */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClient;
    
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "ibanComptePrincipal", referencedColumnName = "iban")
    private CompteBancaire ibanComptePrincipal;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "CLIENT_ASSURANCE", 
        joinColumns = { @JoinColumn(name = "idClient") }, 
        inverseJoinColumns = { @JoinColumn(name = "idAssurance") }
    )
    Collection<Assurance> assurances;
    
    //Consturcteurs
    public Client(){}

    //Getter et Setters
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }   

    public CompteBancaire getIbanComptePrincipal() {
        return ibanComptePrincipal;
    }

    public void setIdComptePrincipal(CompteBancaire ibanComptePrincipal) {
        this.ibanComptePrincipal = ibanComptePrincipal;
    }
    
    //Procedures
    public void addComptes (ArrayList<CompteBancaire> listCompte){
        for(CompteBancaire compte : listCompte){
            compte.setProprietaire(this);
        }
    }
    
    public void addComptesPrincipal (CompteBancaire comptePrincipal){
        comptePrincipal.setProprietaire(this);
        this.setIdComptePrincipal(comptePrincipal);
    }

}



