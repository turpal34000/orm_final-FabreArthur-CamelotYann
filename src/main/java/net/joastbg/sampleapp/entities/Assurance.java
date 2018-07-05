package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import static org.hibernate.Hibernate.any;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeAssurance")
@Table(name="ASSURANCE")
public class Assurance implements Serializable {
    //Variables
    @Id
    /*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASSURANCE_SEQUENCE")
    @SequenceGenerator(
        name="ASSURANCE_SEQUENCE",
	sequenceName="ASSURANCE_SEQ"
    ) */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAssurance;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSouscription;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAnniversaire;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePrelevement;
    
    @ManyToOne
    @JoinColumn(name = "idAssure", referencedColumnName = "idClient")   
    private Client idAssure;
    
    @ManyToMany(mappedBy = "assurances")
    private Collection<Client> assures;

    @OneToMany(mappedBy="assuranceLiee",fetch = FetchType.EAGER)
    private Collection<Echeances> echeances;   
    
    public Collection<Echeances> getEcheances() {
        return echeances;
    }

    public void setEcheances(Collection<Echeances> echeances) {
        this.echeances = echeances;
    }
  
    public Collection<Client> getAssures() {
        return assures;
    }

    public void setAssures(Collection<Client> assures) {
        this.assures = assures;
    }
    
    //Constructeurs
    public Assurance(){}
    
    //Getter et Setters
    public Integer getIdAssurance() {
        return idAssurance;
    }

    
    public void setIdAssurance(Integer idAssurance) {
        this.idAssurance = idAssurance;
    }

    
    public Date getDateSouscription() {
        return dateSouscription;
    }

    
    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    
    public Date getDateAnniversaire() {
        return dateAnniversaire;
    }

    
    public void setDateAnniversaire(Date dateAnniversaire) {
        this.dateAnniversaire = dateAnniversaire;
    }

    
    public Date getDatePrelevement() {
        return datePrelevement;
    }

    
    public void setDatePrelevement(Date datePrelevement) {
        this.datePrelevement = datePrelevement;
    }

    public Client getIdAssure() {
        return idAssure;
    }

    public void setIdAssure(Client idAssure) {
        this.idAssure = idAssure;
    }
}
