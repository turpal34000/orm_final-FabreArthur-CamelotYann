package net.joastbg.sampleapp.dao;

import java.lang.ProcessBuilder.Redirect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddUpdateDeleteDao<T,S> {
    
    private String table;
    
    public AddUpdateDeleteDao(){
        
    }

    public AddUpdateDeleteDao(String table){
        this.table=table;
    }
    
    public S save(T object,SessionFactory sessionFactory){ //marche pas avec les sequences
        Session session = sessionFactory.getCurrentSession();
        S returnID = (S) session.save(object);
        return returnID;
    }
    
    public String update(T object,SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
        return "Session Update";
    }
    
    public String delete(T object,SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
        return "Session Delete";
    }
    
    public List<T> findAll(SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession(); 
        return  session.createQuery("from "+this.table).list();
    }
    
    public List<T> findWithParameters(String condition,SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from "+this.table+" where "+condition).list();
    }
}
