package net.joastbg.sampleapp.dao;

import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.joastbg.sampleapp.entities.Assurance;
import net.joastbg.sampleapp.entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssuranceDao {
    
    @Autowired
    public SessionFactory sessionFactory;
    
    public AddUpdateDeleteDao<Assurance,Integer> maj = new AddUpdateDeleteDao<Assurance,Integer>("Assurance");

    public Long persist(Assurance assurance){
        Session session = sessionFactory.getCurrentSession();
        Long returnID = (Long) session.save(assurance);
        return returnID;
    }

    public List<Assurance> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Assurance").list();
    }
    
    public List<Assurance> findAH(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Assurance where typeAssurance='AH'").list();
    }
    
    public List<Assurance> findAA(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("from Assurance where typeAssurance='AA'").list();
    }
    
    public List findMonth(){
        Date today= new Date();
        Date month = new Date();
        month.setMonth(today.getMonth()+3);
        Session session = sessionFactory.getCurrentSession();
        String sql ="SELECT c.nom FROM Client c, Assurance a where a.idAssure=c.idClient AND a.dateAnniversaire>='"+new SimpleDateFormat("yyyy-MM-dd").format(today)+"' AND a.dateAnniversaire<='"+new SimpleDateFormat("yyyy-MM-dd").format(month)+"'";
        return  session.createQuery(sql).list();
    }
    
    public Map<String, Assurance> findSixMonth(Client client){
        Session session = sessionFactory.getCurrentSession();
        String sql;
        Date today= new Date();
        Date month = new Date();
        Map<String, Assurance> list = new HashMap<>();
        for(int i=1;i<=6;i++){
            month.setMonth(today.getMonth()+i);
            sql ="FROM Assurance where idAssure="+client.getIdClient()+" AND dateAnniversaire>='"+new SimpleDateFormat("yyyy-MM-dd").format(month)+"' AND dateSouscription<='"+new SimpleDateFormat("yyyy-MM-dd").format(month)+"'";
            List<Assurance> listadd = session.createQuery(sql).list();
            if (listadd.size()>0){
                for(Assurance a : listadd){
                    list.put(new SimpleDateFormat("MM").format(month)+"|"+a.getIdAssurance(),a);
                }    
            }
        }
        return  list;
    }
    
    public List<Assurance> findEndAssuance(){
        Date today= new Date();
        Session session = sessionFactory.getCurrentSession();
        System.out.println("from Assurance where dateAnniversaire='"+new SimpleDateFormat("yyyy-MM-dd").format(today)+"'");
        return  session.createQuery("from Assurance where dateAnniversaire='"+new SimpleDateFormat("yyyy-MM-dd").format(today)+"'").list();
    }
    
}
