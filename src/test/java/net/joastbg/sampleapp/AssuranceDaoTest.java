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
import net.joastbg.sampleapp.dao.AssuranceDao;
import net.joastbg.sampleapp.entities.Assurance;
import net.joastbg.sampleapp.entities.AssuranceAuto;
import net.joastbg.sampleapp.entities.AssuranceHabitat;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/config/BeanLocations.xml")
public class AssuranceDaoTest {

    @Autowired
    public AssuranceDao assuranceDao;

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
	List<Assurance> assurance = assuranceDao.maj.findAll(assuranceDao.sessionFactory);
        System.out.println("[testFindAllPM]");
        this.afficheList(assurance);
	Assert.assertNotNull(assurance);
    }
    
    @Test
    public void testFindAllPP(){
	List<Assurance> assurance = assuranceDao.findAH();
        System.out.println("\n------------------------------");
        for(int i=0;i<assurance.size();i++){
            System.out.println(assurance.get(i).getIdAssurance()+" "+assurance.get(i).getDateSouscription()+" "+assurance.get(i).getDateAnniversaire()+" "+assurance.get(i).getDatePrelevement()+" "+((AssuranceHabitat)assurance.get(i)).getAdresseAssuree()+" "+((AssuranceHabitat)assurance.get(i)).getValeurCouverture());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(assurance);
    }
    
    @Test
    public void testFindAllPM(){
	List<Assurance> assurance = assuranceDao.findAA();
        System.out.println("\n------------------------------");
        for(int i=0;i<assurance.size();i++){
            System.out.println(assurance.get(i).getIdAssurance()+" "+assurance.get(i).getDateSouscription()+" "+assurance.get(i).getDateAnniversaire()+" "+assurance.get(i).getDatePrelevement()+" "+((AssuranceAuto)assurance.get(i)).getImmatriculation()+" "+((AssuranceAuto)assurance.get(i)).getBonusMalus());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(assurance);
    }
    
    public void afficheList(List<Assurance> assurance){
        for(int i=0;i<assurance.size();i++){
            System.out.println(assurance.get(i).getIdAssurance()+" "+assurance.get(i).getDateSouscription()+" "+assurance.get(i).getDateAnniversaire()+" "+assurance.get(i).getDatePrelevement());
        }       
    }



}
