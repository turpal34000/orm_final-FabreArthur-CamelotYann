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
import net.joastbg.sampleapp.dao.ContactDao;
import net.joastbg.sampleapp.entities.Contact;
import net.joastbg.sampleapp.entities.TypeContact;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/config/BeanLocations.xml")
public class ContactDaoTest {

    @Autowired
    ContactDao contactDao;

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
	List<Contact> contact = contactDao.findAll();
        System.out.println("\n------------------------------ ");
        System.out.println("client number "+contact.size());
        System.out.println("------------------------------\n");
	Assert.assertNotNull(contact);
    }
    
    @Test
    public void testFindAllInfo(){
	List<Contact> contact = contactDao.findAll();
        System.out.println("\n---------ALL CONTACTS------------- ");
         for(int i=0;i<contact.size();i++){
            System.out.println(contact.get(i).getIdContact()+" "+contact.get(i).getClient().getNom()+" "+contact.get(i).getcType()+" "+contact.get(i).getValeur());
        }
        System.out.println("------------------------------\n");
	Assert.assertNotNull(contact);
    }
    
    public void testCreateContact(Contact contact,TypeContact cType, String valeur){
        //a completer
    }
    



}
