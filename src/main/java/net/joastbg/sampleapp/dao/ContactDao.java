package net.joastbg.sampleapp.dao;

import java.util.List;
import net.joastbg.sampleapp.entities.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactDao {
    
    @Autowired
    public SessionFactory sessionFactory;

    public AddUpdateDeleteDao<Contact,Integer> maj = new AddUpdateDeleteDao<Contact,Integer>("Contact");
    
    public Long persist(Contact contact){
        Session session = sessionFactory.getCurrentSession();
        Long returnID = (Long) session.save(contact);
        return returnID;
    }

    public List<Contact> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Contact").list();
    }
    
}
