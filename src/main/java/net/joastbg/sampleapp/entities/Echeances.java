package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity
@Table(name="ECHEANCES")
public class Echeances implements Serializable {

    //Variables
    @Id
    /*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ECHEANCE_SEQUENCE")
    @SequenceGenerator(
        name="ECHEANCE_SEQUENCE",
	sequenceName="ECHEANCE_SEQ"
    ) */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEcheance;
    
    private Integer prix;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmission;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePaiement;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmissionFacture;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "assuranceLiee", referencedColumnName = "idAssurance")
    private Assurance assuranceLiee;

    //Getters ans setters
    public Integer getIdEcheance() {
        return idEcheance;
    }

    public void setIdEcheance(Integer idEcheance) {
        this.idEcheance = idEcheance;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Date getDateEmissionFacture() {
        return dateEmissionFacture;
    }

    public void setDateEmissionFacture(Date dateEmissionFacture) {
        this.dateEmissionFacture = dateEmissionFacture;
    }

    public Assurance getAssuranceLiee() {
        return assuranceLiee;
    }

    public void setAssuranceLiee(Assurance assuranceLiee) {
        this.assuranceLiee = assuranceLiee;
    }
    
    //Constructors
    public Echeances(){}
    
    public Echeances(Integer prix,Date dateEmission,Assurance assuranceLiee)throws IllegalArgumentException{
        if (dateEmission==null || assuranceLiee==null || prix==null) {
            throw new IllegalArgumentException("[Echeances] One of the parameter is null or empty");
	}
        this.setAssuranceLiee(assuranceLiee);
        this.setDateEmission(dateEmission);
        this.setPrix(prix);
    }
}
