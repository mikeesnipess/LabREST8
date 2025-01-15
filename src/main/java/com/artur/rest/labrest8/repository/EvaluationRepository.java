package com.artur.rest.labrest8.repository;//package com.example.lab7_jt.repository;
//
//import com.example.lab7_jt.entities.Evaluation;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import java.util.List;
//
//public class EvaluationRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(Evaluation evaluation) {
//        entityManager.persist(evaluation);
//    }
//
//    public List<Evaluation> findByTeacherId(Long teacherId) {
//        return entityManager.createQuery("SELECT e FROM Evaluation e WHERE e.teacher.id = :teacherId", Evaluation.class)
//                .setParameter("teacherId", teacherId)
//                .getResultList();
//    }
//
//    public List<Evaluation> findAll() {
//        return entityManager.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
//    }
//}
