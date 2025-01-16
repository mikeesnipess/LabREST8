package com.artur.rest.labrest8.service;

import com.artur.rest.labrest8.dto.EvaluationDTO;
import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.repository.EntityManagerProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class EvaluationService {

    private EntityManager em;

    @Context SecurityContext securityContext;
    @Inject
    public void setEvaluationService(EntityManagerProducer emp) {
        this.em = emp.getEntityManager();
    }

    public EvaluationService() {
    }


    public void submitEvaluation(Evaluation evaluation) {
        LocalDateTime now = LocalDateTime.now();
        evaluation.setTimestamp(now);  // Set timestamp to current time
        evaluation.setRegistrationNumber(generateRegistrationNumber());  // Generate a registration number

        // Begin transaction and save to the database
        em.getTransaction().begin();
        em.persist(evaluation);
        em.getTransaction().commit();
    }


    public List<Evaluation> getEvaluationsByTeacher(User teacher) {
        return em.createQuery("SELECT e FROM Evaluation e WHERE e.teacher = :teacher", Evaluation.class)
                .setParameter("teacher", teacher)
                .getResultList();
    }

    public List<Evaluation> getAllEvaluations() {
        return em.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
    }

    public List<EvaluationDTO> getAllEvaluationsDTO() {

        if (securityContext.getUserPrincipal() != null) {
            // Get the authenticated user's principal name
            String username = securityContext.getUserPrincipal().getName();
            System.out.println(username);
        }
        List<Evaluation> evaluations = em.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();

        if (evaluations != null && !evaluations.isEmpty()) {
            // Convert each Evaluation entity to EvaluationDTO
            return evaluations.stream()
                    .map(ev -> new EvaluationDTO(
                            ev.getId(),
                            ev.getActivity(),
                            ev.getActivityType(),
                            ev.getGrade(),
                            ev.getComment(),
                            ev.getRegistrationNumber()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public LocalDateTime getWindowEndTime() {
        // Example logic: End time is 10 minutes from now
        return LocalDateTime.now().plusMinutes(10);
    }

    private String generateRegistrationNumber() {
        return "REG" + System.currentTimeMillis(); // Simple unique registration number generator
    }

    public List<Evaluation> getEvaluationsByTeacherId(UUID teacherId) {
        return em.createQuery(
                        "SELECT e FROM Evaluation e WHERE e.teacher.id = :teacherId", Evaluation.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
    }

    public Evaluation getEvaluationById(UUID id) {
        Evaluation some = em.find(Evaluation.class, id);
        return some;
    }

    public EvaluationDTO getEvaluationId(UUID id) {
        Evaluation evaluation = em.find(Evaluation.class, id);
        if (evaluation != null) {
            // Convert to
            return new EvaluationDTO(
                    evaluation.getId(),
                    evaluation.getActivity(),
                    evaluation.getActivityType(),
                    evaluation.getGrade(),
                    evaluation.getComment(),
                    evaluation.getRegistrationNumber()
            );
        }
        return null; // Or throw an exception
    }

    public EvaluationDTO addEvaluation(Evaluation evaluation) {
        em.getTransaction().begin();

        // Ensure teacher and student are managed (attached to the persistence context)
        User managedTeacher = em.find(User.class, evaluation.getTeacher().getId());
        User managedStudent = em.find(User.class, evaluation.getStudent().getId());

        evaluation.setTeacher(managedTeacher);
        evaluation.setStudent(managedStudent);

        em.persist(evaluation);

        em.getTransaction().commit();
        if (evaluation != null) {
            // Convert to DTO
            return new EvaluationDTO(
                    evaluation.getId(),
                    evaluation.getActivity(),
                    evaluation.getActivityType(),
                    evaluation.getGrade(),
                    evaluation.getComment(),
                    evaluation.getRegistrationNumber()
            );
        }
        return null;
    }


    public EvaluationDTO updateEvaluation(UUID id, Evaluation updatedEvaluation) {
        em.getTransaction().begin(); // Start transaction
        Evaluation existingEvaluation = em.find(Evaluation.class, id);
        if (existingEvaluation != null) {
            existingEvaluation.setActivity(updatedEvaluation.getActivity());
            existingEvaluation.setActivityType(updatedEvaluation.getActivityType());
            existingEvaluation.setGrade(updatedEvaluation.getGrade());
            existingEvaluation.setComment(updatedEvaluation.getComment());

            em.merge(existingEvaluation); // Merge to update entity
            em.getTransaction().commit(); // Commit transaction

            // Convert to DTO
            return new EvaluationDTO(
                    existingEvaluation.getId(),
                    existingEvaluation.getActivity(),
                    existingEvaluation.getActivityType(),
                    existingEvaluation.getGrade(),
                    existingEvaluation.getComment(),
                    existingEvaluation.getRegistrationNumber()
            );
        }
        em.getTransaction().rollback(); // Rollback if not found
        return null;
    }


    public boolean deleteEvaluation(UUID id) {
        // Start transaction if not already started
        em.getTransaction().begin();

        Evaluation evaluation = em.find(Evaluation.class, id);
        if (evaluation != null) {
            em.remove(evaluation);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback(); // Rollback if something goes wrong
        return false;
    }


}
