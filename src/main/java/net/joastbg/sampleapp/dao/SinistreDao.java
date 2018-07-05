package net.joastbg.sampleapp.dao;

import net.joastbg.sampleapp.entities.Sinistre;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SinistreDao {
    
    @Autowired
    public SessionFactory sessionFactory;
 
    public AddUpdateDeleteDao<Sinistre,Integer> maj = new AddUpdateDeleteDao<Sinistre,Integer>("Sinistre");
    
    
}
