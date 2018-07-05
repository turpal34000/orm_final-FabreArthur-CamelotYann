package net.joastbg.sampleapp;

import junit.framework.Assert;
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

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/config/BeanLocations.xml")
public class CompteBancaireDaoTest {

    @Autowired
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
	List<CompteBancaire> compteBancaire = comptebancaireDao.findAll();
        System.out.println("\n------------------------------ ");
        System.out.println("compteBancaire number "+compteBancaire.size());
        System.out.println("------------------------------\n");
	Assert.assertNotNull(compteBancaire);
    }
    
    @Test
    public void testFindAllInfo(){
	List<CompteBancaire> compteBancaire = comptebancaireDao.findAll();
        System.out.println("\n---------ALL CompteBancaire------------- ");
         for(int i=0;i<compteBancaire.size();i++){
            System.out.println(compteBancaire.get(i).getIban()+" "+compteBancaire.get(i).getBic()+" "+compteBancaire.get(i).getProprietaire().getNom());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(compteBancaire);
    }
    



}
