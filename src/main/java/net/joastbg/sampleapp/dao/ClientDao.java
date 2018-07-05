package net.joastbg.sampleapp.dao;

import net.joastbg.sampleapp.entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientDao {

    @Autowired
    public SessionFactory sessionFactory;

    public AddUpdateDeleteDao<Client,Integer> maj = new AddUpdateDeleteDao<Client,Integer>("Client");
    
    public Long persist(Client client){
        Session session = sessionFactory.getCurrentSession();
        Long returnID = (Long) session.save(client);
        return returnID;
    }

    public List<Client> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Client").list();
    }
    
    public List<Client> findPP(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Client where typeClient='PP'").list();
    }
    
    public List<Client> findPM(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Client where typeClient='PM'").list();
    }
    
    public List<Client> findClient(Client client){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Client where idClient="+client.getIdClient()).list();
    }
}
