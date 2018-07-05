package net.joastbg.sampleapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import junit.framework.Assert;
import net.joastbg.sampleapp.dao.AssuranceDao;
import net.joastbg.sampleapp.dao.ClientDao;
import net.joastbg.sampleapp.dao.CompteBancaireDao;
import net.joastbg.sampleapp.dao.EcheancesDao;
import net.joastbg.sampleapp.dao.PersonnePhysiqueDao;
import net.joastbg.sampleapp.dao.SinistreDao;
import net.joastbg.sampleapp.entities.Assurance;
import net.joastbg.sampleapp.entities.AssuranceAuto;
import net.joastbg.sampleapp.entities.AssuranceHabitat;
import net.joastbg.sampleapp.entities.Client;
import net.joastbg.sampleapp.entities.CompteBancaire;
import net.joastbg.sampleapp.entities.Contact;
import net.joastbg.sampleapp.entities.Echeances;
import net.joastbg.sampleapp.entities.PersonneMorale;
import net.joastbg.sampleapp.entities.PersonnePhysique;
import net.joastbg.sampleapp.entities.Sinistre;
import net.joastbg.sampleapp.entities.TypeContact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/config/BeanLocations.xml")
public class ExerciceTest {

    @Autowired
    ClientDao clientDao;
    @Autowired
    AssuranceDao assuranceDao;
    @Autowired
    CompteBancaireDao comptebancaireDao;
    @Autowired
    PersonnePhysiqueDao personnePhysiqueDao;
    @Autowired
    EcheancesDao echeanceDao;
    @Autowired
    SinistreDao sinistreDao;

    @Before
    public void setUp() {
        Assert.assertTrue(true);
    }

    //1. Crï¿½er un client
    @Test //Creation PersonnePhysique
    public void createPersonnePhysique() {
        System.out.println("\n--[1]createPersonnePhysique--");
        System.out.println(clientDao.maj.save(new PersonnePhysique("nom","prenom","01/01/2001"), clientDao.sessionFactory));
    }  
    
    @Test //Creation PersonneMorale
    public void createPersonneMorale() {
        System.out.println("\n--[1]createPersonneMorale--");
        System.out.println(clientDao.maj.save(new PersonneMorale("nom","siren"), clientDao.sessionFactory));
    }
    //2. Ajouter au client une liste de compte lui appartenant
    @Test
    public void addListeCB() {
        System.out.println("\n--[2]addListeCB--");
	ArrayList<CompteBancaire> lcb = new ArrayList<CompteBancaire>();
        lcb.add(new CompteBancaire("iban1","bic1"));
        lcb.add(new CompteBancaire("iban2","bic2"));
        lcb.add(new CompteBancaire("iban3","bic3"));
        PersonnePhysique Pierre= new PersonnePhysique("Pierre","Karunin","08/12/1981");
        Pierre.addComptes(lcb);
        boolean test=true;
        for(int i=0;i<lcb.size();i++){
            System.out.println(lcb.get(i).getIban()+" "+ lcb.get(i).getProprietaire().getIdClient());
            if(lcb.get(i).getProprietaire().getIdClient()!=Pierre.getIdClient()) test=false;
        }
        Assert.assertEquals(test,true);
    }
    //3. Permetre de dï¿½finir le compte principal
    @Test
    public void addClientCp() {
        System.out.println("\n--[3]addClientCp--");
        PersonnePhysique Paul= new PersonnePhysique("Paul","Karunin","08/12/1981");
	CompteBancaire cp = new CompteBancaire("iban4","bic4");
        Paul.addComptesPrincipal(cp);
        System.out.println("Compte Principal : "+Paul.getIbanComptePrincipal().getIban());
        Assert.assertEquals(cp, Paul.getIbanComptePrincipal());
    }
    //4. Gerer liste de contact
    @Test //Creation d'un contact
    public void createContactList(){
        System.out.println("\n--[4]createContactList--");
        PersonnePhysique bob = new PersonnePhysique("Stetford","Bob","01/01/2001");
        Contact tel = new Contact(bob,TypeContact.FIXE,"0456826884");
        Assert.assertEquals(tel.getClient(), bob);
    }
    //5. Crï¿½er et lier une assurance
    @Test //Creation AssuranceAuto
    public void creatAssuranceAuto() {
        System.out.println("\n--[5]creatAssuranceAuto--");
        PersonnePhysique bob = new PersonnePhysique("Stetford","Bob","01/01/2001");
	new AssuranceAuto(new Date(2000,5,8),new Date(2000,5,8),new Date(2000,5,8), bob, "15-123-EZ", 2);
    }     
    
    @Test //Creation AssuranceHabitation
    public void creatAssuranceHabitat() {
        System.out.println("\n--[5]creatAssuranceHabitat--");
        PersonnePhysique bob = new PersonnePhysique("Stetford","Bob","01/01/2001");
	new AssuranceHabitat(new Date(2000,5,8),new Date(2000,5,8),new Date(2000,5,8), bob, "10 avenue montpellier", 2);
    } 
    
    @Test //Ajout Conducteur secondaire
    public void addOtherConductor() {
        System.out.println("\n--[5]addOtherConductor--");
        List<Client> client = clientDao.findPP();
        PersonnePhysique bob = new PersonnePhysique("Stetford","Bob","01/01/2001");
	AssuranceAuto AA = new AssuranceAuto(new Date(2000,5,8),new Date(2000,5,8),new Date(2000,5,8), bob, "15-123-EZ", 2);
        AA.setAssures(client);
        Collection<Client> cllistout=AA.getAssures();
        Assert.assertNotNull(cllistout);
        for(Client cl : cllistout){
            System.out.println(cl.getIdClient()+" "+cl.getNom());
        }
    }     
    //6. Créer et lier une écheance
    @Test //Creation d'une echéance
    public void createcheance() {
        System.out.println("\n--[6]createcheance--");
        PersonnePhysique bob = new PersonnePhysique("Stetford","Bob","01/01/2001");
	AssuranceHabitat AH = new AssuranceHabitat(new Date(2000,5,8),new Date(2000,5,8),new Date(2000,5,8), bob, "10 avenue montpellier", 2);
        new Echeances(300,new Date(2010,5,8),AH);
    } 
    //7. Permtre ï¿½ un client d'imprimer son ï¿½chï¿½ancier pour les 6 prochains moins
    @Test
    public void getEcheancierTest(){
        System.out.println("\n--[7]getEcheancier--");
        List<Client> client = clientDao.maj.findWithParameters("idClient = 1", clientDao.sessionFactory);
        Map<String, Assurance> assurances = this.assuranceDao.findSixMonth(client.get(0));
        System.out.println(assurances.size());
        Set<Entry<String, Assurance>> setAssurance = assurances.entrySet();
        Iterator<Entry<String, Assurance>> it2 = setAssurance.iterator();
        while(it2.hasNext()){
           Entry<String, Assurance> e = it2.next();
           System.out.println("Mois : "+e.getKey() + "\n\t[" + e.getValue().getIdAssurance()+"]");
           List<Echeances> echeances = this.echeanceDao.maj.findWithParameters("assuranceLiee="+e.getValue().getIdAssurance(), echeanceDao.sessionFactory);
           for(Echeances echeance : echeances){
               System.out.println("\t"+echeance.getPrix()+"€ Payé->"+echeance.getDatePaiement());
           }
        }
    }
    //8.
    @Test
    public void addSinistreAH(){
        System.out.println("\n--[8]addSinistreAH--");
        List<Assurance> assurance = assuranceDao.maj.findAll(assuranceDao.sessionFactory);
        Sinistre sah= new Sinistre(new Date(2018,06,30),"Inondation","Inondation",assurance.get(0));
        System.out.println(sinistreDao.maj.save(sah, sinistreDao.sessionFactory));
    }
    
    @Test
    public void addSinistreAA(){
        System.out.println("\n--[8]addSinistreAA--");
        List<Assurance> assurance = assuranceDao.maj.findAll(assuranceDao.sessionFactory);
        Sinistre sah= new Sinistre(new Date(2018,06,30),"Parchoc","Parchoc","Plaque",assurance.get(1));
    }
    
    //9.
    @Test
    public void deleteAssurance(){ // à revoir
        System.out.println("\n--[9]deleteAssurance--");
        List<Assurance> assurance = assuranceDao.findEndAssuance();
        System.out.println("as1 = "+assurance.size());
        int count=0;
        for(Assurance a: assurance){
            System.out.println(a.getDateAnniversaire()+" "+a.getIdAssurance());
            /*for(Echeances e: echeances){
                if(e.getAssuranceLiee().equals(a)){
                    echeanceDao.maj.delete(e, echeanceDao.sessionFactory);
                    count++;
                }
            }*/
            //List<Echeances> echeances3 = echeanceDao.maj.findAll("Echeances", echeanceDao.sessionFactory);
            //System.out.println(echeances3.size());
            assuranceDao.maj.delete(a, assuranceDao.sessionFactory); // <----------- ce delete marche pas il me dit qu'il y a un problème de clef étrangère sinon ça marche
            count++;
        }
        List<Assurance> assurance2 = assuranceDao.findEndAssuance();
        
        System.out.println("as2 = "+assurance2.size());
        System.out.println("count = "+count);
        Assert.assertEquals(assurance.size()-count, assurance2.size());
        
        
    }
    //10.
    @Test
    public void getMonthAssurance(){
        System.out.println("\n--[10]getMonthAssurance--");
        List lam = assuranceDao.findMonth();
        Assert.assertNotNull(lam);
        System.out.println(lam.size());
    }
    
    //11. test persist
    /*@Test
    public void testPersist(){
       System.out.println("\n--[11]Percistance--");
	List<Client> object = clientDao.maj.findAll(clientDao.sessionFactory);
        Assert.assertNotNull(object);
        for(int i=0;i<object.size();i++){
            System.out.println(object.get(i).getIdClient() + " " + object.get(i).getNom());
        }
        /*Contact tel = new Contact(object.get(5),TypeContact.FIXE,"0456826884");
        
        System.out.println(tel.getIdContact());*/
        /*System.out.println(personnePhysiqueDao.maj.save(cl, personnePhysiqueDao.sessionFactory));
        object = clientDao.maj.findAll("Client", clientDao.sessionFactory);
        Assert.assertNotNull(object);
        for(int i=0;i<object.size();i++){
            System.out.println(object.get(i).getNom());
        }*/
        /*cp.setBic("perci2");
        System.out.println(clientDao.maj.update(cp, clientDao.sessionFactory));
        compteBancaire = clientDao.maj.findAll("CompteBancaire", clientDao.sessionFactory);
        Assert.assertNotNull(compteBancaire);
        for(int i=0;i<compteBancaire.size();i++){
            System.out.println(compteBancaire.get(i).getIban()+" "+compteBancaire.get(i).getBic()+" "+compteBancaire.get(i).getProprietaire());
        }*/
        /*System.out.println(clientDao.maj.delete(object.get(5), clientDao.sessionFactory));
        object = clientDao.maj.findAll(clientDao.sessionFactory);
        Assert.assertNotNull(object);
        for(int i=0;i<object.size();i++){
            System.out.println(object.get(i).getIdClient() + " " + object.get(i).getNom());
        }
        
    }*/


}
