package net.joastbg.sampleapp;

import java.util.ArrayList;
import junit.framework.Assert;
import net.joastbg.sampleapp.dao.ClientDao;
import net.joastbg.sampleapp.entities.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import net.joastbg.sampleapp.dao.CompteBancaireDao;
import net.joastbg.sampleapp.entities.CompteBancaire;
import net.joastbg.sampleapp.entities.PersonneMorale;
import net.joastbg.sampleapp.entities.PersonnePhysique;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/config/BeanLocations.xml")
public class ClientDaoTest {

    @Autowired
    ClientDao clientDao;
    CompteBancaireDao comptebancaireDao;

    @Before
    public void setUp() {
        Assert.assertTrue(true);
    }

    @Test
    public void testPersist(){
        Assert.assertTrue(true);
    }
    
    @Test
    public void testFindAll(){
	List<Client> client = clientDao.findAll();
        System.out.println("\n------------------------------ ");
        System.out.println("client number "+client.size());
        System.out.println("------------------------------\n");
	Assert.assertNotNull(client);
    }
    
    @Test
    public void testFindAllPP(){
	List<Client> client = clientDao.findPP();
        System.out.println("\n------------------------------");
        for(int i=0;i<client.size();i++){
            System.out.println(client.get(i).getNom()+" "+client.get(i).getIdClient()+" "+((PersonnePhysique)client.get(i)).getDateNaissance()+" "+((PersonnePhysique)client.get(i)).getPrenom());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(client);
    }
    
    @Test
    public void testFindAllPM(){
	List<Client> client = clientDao.findPM();
        System.out.println("\n------------------------------");
        for(int i=0;i<client.size();i++){
            System.out.println(client.get(i).getNom()+" "+client.get(i).getIdClient()+" "+((PersonneMorale)client.get(i)).getSiren());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(client);
    }
    
    @Test
    public void createPersonnePhysique() {
	new PersonnePhysique("nom","prenom","01/01/2001");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createPersonnePhysiqueVide() {
	new PersonnePhysique("","prenom","01/01/2001");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createPersonnePhysiqueNull() {
	new PersonnePhysique("nom",null,"01/01/2001");
    }
    
    
    @Test
    public void createPersonneMorale() {
	new PersonneMorale("nom","siren");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createPersonneMoraleVide() {
	new PersonneMorale("","siren");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createPersonneMoraleNull() {
	new PersonneMorale("nom",null);
    }
    
    @Test
    public void addListe() {
	ArrayList<CompteBancaire> lcb = new ArrayList<CompteBancaire>();
        lcb.add(new CompteBancaire("iban1","bic1"));
        lcb.add(new CompteBancaire("iban2","bic2"));
        lcb.add(new CompteBancaire("iban3","bic3"));
        PersonnePhysique Pierre= new PersonnePhysique("Pierre","Karunin","08/12/1981");
        Pierre.addComptes(lcb);
        System.out.println("\n------------------------------");
        boolean test=true;
        for(int i=0;i<lcb.size();i++){
            System.out.println(lcb.get(i).getIban()+" "+ lcb.get(i).getProprietaire().getIdClient());
            if(lcb.get(i).getProprietaire().getIdClient()!=Pierre.getIdClient()) test=false;
        }
        System.out.println("------------------------------\n");
        Assert.assertEquals(test,true);
    }
    
    @Test
    public void addCp() {
	CompteBancaire cp = new CompteBancaire("iban4","bic4");
        PersonnePhysique Paul= new PersonnePhysique("Paul","Karunin","08/12/1981");
        Paul.addComptesPrincipal(cp);
        System.out.println("\n------------------------------ ");
        System.out.println("Compte Principal : "+Paul.getIbanComptePrincipal().getIban());
        System.out.println("------------------------------\n");
        Assert.assertEquals(cp, Paul.getIbanComptePrincipal());
    }



}
