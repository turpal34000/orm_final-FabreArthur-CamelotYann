package net.joastbg.sampleapp.dao;

import java.util.Date;
import java.util.List;
import net.joastbg.sampleapp.entities.Client;
import net.joastbg.sampleapp.entities.Echeances;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EcheancesDao {
    
    @Autowired
    public SessionFactory sessionFactory;
 
    public AddUpdateDeleteDao<Echeances,Integer> maj = new AddUpdateDeleteDao<Echeances,Integer>("Echeances");
    
    public Long persist(Echeances echeances){
        Session session = sessionFactory.getCurrentSession();
        Long returnID = (Long) session.save(echeances);
        return returnID;
    }
    
    public List<Echeances> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Echeances").list();
    }
    
    public List<Echeances> getEcheancier(Client client){
        Session session = sessionFactory.getCurrentSession();
        return this.maj.findWithParameters("where assuranceLiee="+client.getIdClient(), this.sessionFactory);
    }
    
    
    
}
