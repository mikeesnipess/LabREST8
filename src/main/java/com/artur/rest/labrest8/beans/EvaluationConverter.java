package com.artur.rest.labrest8.beans;

import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.service.EvaluationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
@FacesConverter(value = "evaluationConverter", managed = true)
public class EvaluationConverter implements Converter, Serializable {
    @Inject
    private EvaluationService evaluationService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            UUID idRec = UUID.fromString(value);
            return evaluationService.getEvaluationById(idRec); // Assuming the userService method works as expected
        } catch (IllegalArgumentException e) {
            return null;  // Return null if the value can't be parsed to UUID
        }
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Evaluation) {
            Evaluation evaluation = (Evaluation) value;
            return evaluation.getId().toString();  // Make sure the `id` is a UUID
        }
        return "";
    }
}

