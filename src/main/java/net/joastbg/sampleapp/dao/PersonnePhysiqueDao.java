package net.joastbg.sampleapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.joastbg.sampleapp.entities.PersonnePhysique;

@Service
@Transactional
public class PersonnePhysiqueDao {

    @Autowired
    public SessionFactory sessionFactory;

    public AddUpdateDeleteDao<PersonnePhysique,Integer> maj = new AddUpdateDeleteDao<PersonnePhysique,Integer>();
    
}
