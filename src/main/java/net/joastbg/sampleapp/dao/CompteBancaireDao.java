package net.joastbg.sampleapp.dao;

import java.util.List;
import net.joastbg.sampleapp.entities.Client;
import net.joastbg.sampleapp.entities.CompteBancaire;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompteBancaireDao {
    
    @Autowired
    public SessionFactory sessionFactory;

    public AddUpdateDeleteDao<CompteBancaire,String> maj = new AddUpdateDeleteDao<CompteBancaire,String>("CompteBancaire");
    
    public String save(CompteBancaire object){
        Session session = sessionFactory.getCurrentSession();
        String returnID = (String) session.save(object);
        return returnID;
    }

    public List<CompteBancaire> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from CompteBancaire").list();
    }
    
    public List<CompteBancaire> getComptesClient(Client client){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from CompteBancaire Where proprietaire="+client.getIdClient()).list();
    }
    
}
