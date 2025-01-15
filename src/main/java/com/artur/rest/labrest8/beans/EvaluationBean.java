package com.artur.rest.labrest8.beans;

import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.service.EvaluationService;
import com.artur.rest.labrest8.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class EvaluationBean  implements Serializable {
    private String activity;
    private String activityType;
    private Integer grade;
    private String comment;
    private User teacher; // To hold the selected teacher
    private List<User> teachers;  // List of teachers for the dropdown
    private List<User> students;  // List of teachers for the dropdown
    private String successMessage;
    private UUID teacherId;

    private List<Evaluation> allEvaluations;
    private List<Evaluation> evaluations = new ArrayList<>();
    private Evaluation newEvaluation = new Evaluation();
    private Evaluation updatedEvaluation = new Evaluation();

    private final String apiUrl = "http://localhost:8088/Lab7_JT_war_exploded/api/evaluations";

    @Inject
    private EvaluationService evaluationService;

    @Inject
    private UserService userService;

    @Inject
    private AuthBean authBean;


    @PostConstruct
    public void init() {
        this.teachers = userService.getAllTeachers();
        this.students = userService.getAllStudents();
        this.allEvaluations = userService.getAllEvaluations();
        this.updatedEvaluation = new Evaluation();
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String submitEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setActivity(activity);
        evaluation.setActivityType(activityType);
        evaluation.setGrade(grade);
        evaluation.setComment(comment);
        evaluation.setTeacher(teacher);
        evaluation.setStudent(authBean.getLoggedInUser());

        evaluationService.submitEvaluation(evaluation);  // Save to DB

        successMessage = "Evaluation submitted successfully!";
        return null;  // Stay on the same page
    }

    public void loadEvaluationForUpdate(UUID evaluationId) {
        this.updatedEvaluation = evaluationService.getEvaluationById(evaluationId);
    }


//    public int getRemainingTime() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime windowEnd = evaluationService.getWindowEndTime();
//        return (int) Duration.between(now, windowEnd).toSeconds();
//    }

    public void addEvaluation() {
        // Use POST /evaluations to add the new evaluation
        newEvaluation.setId(UUID.randomUUID()); // Simulate an ID for UI purposes
        evaluations.add(newEvaluation); // Add to local list for demonstration
        newEvaluation = new Evaluation(); // Reset form
    }

    public void updateEvaluation() {
        // Use PUT /evaluations/{id} to update the evaluation
        // Update the evaluation in the local list for demonstration
        evaluations.replaceAll(e -> e.getId().equals(updatedEvaluation.getId()) ? updatedEvaluation : e);
        updatedEvaluation = new Evaluation(); // Reset form
    }

    public void deleteEvaluation(UUID id) {
        // Use DELETE /evaluations/{id} to delete the evaluation
        evaluations.removeIf(e -> e.getId().equals(id));
    }

    // Getters and Setters
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Evaluation getNewEvaluation() {
        return newEvaluation;
    }

    public void setNewEvaluation(Evaluation newEvaluation) {
        this.newEvaluation = newEvaluation;
    }

    public Evaluation getUpdatedEvaluation() {
        return updatedEvaluation;
    }

    public void setUpdatedEvaluation(Evaluation updatedEvaluation) {
        this.updatedEvaluation = updatedEvaluation;
    }

    // Getters and setters for successMessage
    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<User> teachers) {
        this.teachers = teachers;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<Evaluation> getEvaluationsDb() {
        return allEvaluations;
    }

    public void setEvaluationsDb(List<Evaluation> evaluationsDb) {
        this.allEvaluations = evaluationsDb;
    }

    // Getters and setters for all properties

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
}
