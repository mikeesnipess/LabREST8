package com.artur.rest.labrest8.beans;

import com.artur.rest.labrest8.dto.EvaluationDTO;
import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.service.EvaluationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

@Named
@ManagedBean
@SessionScoped
public class AdminEvaluationBean implements Serializable {

    @Inject
    private EvaluationService evaluationService;

    private List<Evaluation> allEvaluations;

    private Evaluation newEvaluation;
    private Evaluation updatedEvaluation;
    private List<User> teachers;
    private List<User> students;

    public void loadAllEvaluations() {
        allEvaluations = evaluationService.getAllEvaluations();
    }

    public String getEvaluationsAsJson() {
        List<EvaluationDTO> evaluationsDTO = evaluationService.getAllEvaluationsDTO(); // Assuming you have the method to get the list of DTOs
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(evaluationsDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Return an empty JSON object in case of an error
        }
    }

    // Getters and Setters
    public List<Evaluation> getAllEvaluations() {
        return allEvaluations;
    }


}
