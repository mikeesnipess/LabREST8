package com.artur.rest.labrest8.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class EntityManagerProducer{

    private EntityManager em;

    public EntityManager getEntityManager(){
        if(em == null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
            em = emf.createEntityManager();
        }

        return em;
    }

}
