//package com.artur.rest.labrest8.service;
//
//import com.artur.rest.labrest8.entities.Evaluation;
//import com.artur.rest.labrest8.entities.User;
//import com.artur.rest.labrest8.repository.EntityManagerProducer;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
//import jakarta.persistence.EntityManager;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Named
//@ApplicationScoped
//public class SecondService {
//
////    @Inject
////    private EntityManagerProducer emProducer;
//
////    @Inject
//    private EntityManager entityManager;
//
//    @Inject
//    public void setEvaluationService(EntityManagerProducer emp) {
//        this.entityManager = emp.getEntityManager();
//    }
//
//    public SecondService() {
//    }
//
//    public EntityManager getEntityManager() {
//        return entityManager;
//    }
//
//
//    public void submitEvaluation(Evaluation evaluation) {
//        LocalDateTime now = LocalDateTime.now();
//        evaluation.setTimestamp(now);  // Set timestamp to current time
//        evaluation.setRegistrationNumber(generateRegistrationNumber());  // Generate a registration number
//
//        // Begin transaction and save to the database
//        entityManager.getTransaction().begin();
//        entityManager.persist(evaluation);
//        entityManager.getTransaction().commit();
//    }
//
//
//    public List<Evaluation> getEvaluationsByTeacher(User teacher) {
//        return entityManager.createQuery("SELECT e FROM Evaluation e WHERE e.teacher = :teacher", Evaluation.class)
//                .setParameter("teacher", teacher)
//                .getResultList();
//    }
//
//    public List<Evaluation> getAllEvaluations() {
//        return entityManager.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
//    }
//
//    public LocalDateTime getWindowEndTime() {
//        // Example logic: End time is 10 minutes from now
//        return LocalDateTime.now().plusMinutes(10);
//    }
//
//    private String generateRegistrationNumber() {
//        return "REG" + System.currentTimeMillis(); // Simple unique registration number generator
//    }
//
//    public List<Evaluation> getEvaluationsByTeacherId(UUID teacherId) {
//        return entityManager.createQuery(
//                        "SELECT e FROM Evaluation e WHERE e.teacher.id = :teacherId", Evaluation.class)
//                .setParameter("teacherId", teacherId)
//                .getResultList();
//    }
//
//    public Evaluation getEvaluationById(UUID id) {
//        return entityManager.find(Evaluation.class, id);
//    }
//
//    public Evaluation addEvaluation(Evaluation evaluation) {
//        entityManager.persist(evaluation);
//        return evaluation;
//    }
//
//    public Evaluation updateEvaluation(UUID id, Evaluation updatedEvaluation) {
//        Evaluation existingEvaluation = entityManager.find(Evaluation.class, id);
//        if (existingEvaluation != null) {
//            existingEvaluation.setActivity(updatedEvaluation.getActivity());
//            existingEvaluation.setActivityType(updatedEvaluation.getActivityType());
//            existingEvaluation.setGrade(updatedEvaluation.getGrade());
//            existingEvaluation.setComment(updatedEvaluation.getComment());
//            entityManager.merge(existingEvaluation);
//            return existingEvaluation;
//        }
//        return null;
//    }
//
//    public boolean deleteEvaluation(UUID id) {
//        Evaluation evaluation = entityManager.find(Evaluation.class, id);
//        if (evaluation != null) {
//            entityManager.remove(evaluation);
//            return true;
//        }
//        return false;
//    }
//
//}
