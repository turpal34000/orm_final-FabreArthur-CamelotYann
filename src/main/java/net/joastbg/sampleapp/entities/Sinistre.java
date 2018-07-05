package net.joastbg.sampleapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="SINISTRE")
public class Sinistre implements Serializable {

    //Variables
    @Id
    /*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SINISTRE_SEQUENCE")
    @SequenceGenerator(
        name="SINISTRE_SEQUENCE",
	sequenceName="ECHEANCE_SEQ"
    ) */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSinistre;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDuSinistre;
    private String titre;
    private String description; 
    private String photoTitre; 
    
    @ManyToOne
    @JoinColumn(name = "sAssuranceLiee", referencedColumnName = "idAssurance")
    private Assurance sAssuranceLiee;

    //Getters ans setters

    public Integer getIdSinistre() {
        return idSinistre;
    }

    public void setIdSinistre(Integer idSinistre) {
        this.idSinistre = idSinistre;
    }

    public Date getDateDuSinistre() {
        return dateDuSinistre;
    }

    public void setDateDuSinistre(Date dateDuSinistre) {
        this.dateDuSinistre = dateDuSinistre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoTitre() {
        return photoTitre;
    }

    public void setPhotoTitre(String photoTitre) {
        this.photoTitre = photoTitre;
    }

    public Assurance getsAssuranceLiee() {
        return sAssuranceLiee;
    }

    public void setsAssuranceLiee(Assurance sAssuranceLiee) {
        this.sAssuranceLiee = sAssuranceLiee;
    }
    
    
    //Constructors
    public Sinistre(){}
    
    public Sinistre(Date dateDuSinistre,String titre,String description,String photoTitre,Assurance sAssuranceLiee)throws IllegalArgumentException{
        if (dateDuSinistre==null || sAssuranceLiee==null || titre==null || titre.isEmpty() || description==null || description.isEmpty() || photoTitre==null || photoTitre.isEmpty()) {
            throw new IllegalArgumentException("[Sinistre] One of the parameter is null or empty");
	}
        this.setDateDuSinistre(dateDuSinistre);
        this.setDescription(description);
        this.setPhotoTitre(photoTitre);
        this.setTitre(titre);
        this.setsAssuranceLiee(sAssuranceLiee);
    }
    
    public Sinistre(Date dateDuSinistre,String titre,String description,Assurance sAssuranceLiee)throws IllegalArgumentException{
        if (dateDuSinistre==null || sAssuranceLiee==null || titre==null || titre.isEmpty() || description==null || description.isEmpty()) {
            throw new IllegalArgumentException("[Sinistre] One of the parameter is null or empty");
	}
        this.setDateDuSinistre(dateDuSinistre);
        this.setDescription(description);
        this.setTitre(titre);
        this.setsAssuranceLiee(sAssuranceLiee);
    }
}
