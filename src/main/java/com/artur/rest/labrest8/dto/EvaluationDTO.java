package com.artur.rest.labrest8.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EvaluationDTO {
    private UUID id;
    private String studentName;
    private String teacherName;
    private String activity;
    private String activityType;
    private int grade;
    private String comment;
    private String registrationNumber;
//    private LocalDateTime timestamp;

    // Constructor
    public EvaluationDTO(UUID id, String activity, String activityType, int grade, String comment, String registrationNumber) {
        this.id = id;
        this.activity = activity;
        this.activityType = activityType;
        this.grade = grade;
        this.comment = comment;
        this.registrationNumber = registrationNumber;
//        this.timestamp = timestamp;
    }

    public EvaluationDTO(UUID id,String studentName, String teacherName, String activity, String activityType, int grade, String comment, String registrationNumber) {
        this.id = id;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.activity = activity;
        this.activityType = activityType;
        this.grade = grade;
        this.comment = comment;
        this.registrationNumber = registrationNumber;
    }

    public EvaluationDTO(String teacherName, int grade, String activity) {
        this.teacherName = teacherName;
        this.grade = grade;
        this.activity = activity;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStudentName() {return studentName;}
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public String getTeacherName() {return teacherName;}
    public void setTeacherName(String teacherName) {this.teacherName = teacherName;}

//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
}
