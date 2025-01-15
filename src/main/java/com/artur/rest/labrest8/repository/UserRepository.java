package com.artur.rest.labrest8.repository;//package com.example.lab7_jt.repository;
//
//import com.example.lab7_jt.entities.User;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//public class UserRepository {
//    private EntityManager entityManager;
//
//    // Manually set the EntityManager for testing
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public void save(User user) {
//        entityManager.persist(user);
//    }
//
//    public User findByUsername(String username) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
//                .setParameter("username", username)
//                .getSingleResult();
//    }
//}
