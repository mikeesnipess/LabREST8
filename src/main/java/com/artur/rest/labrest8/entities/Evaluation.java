    package com.artur.rest.labrest8.entities;

    import jakarta.persistence.*;
    import org.hibernate.annotations.UuidGenerator;

    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity
    @Table(name = "evaluations")
    public class Evaluation {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
        @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
        private User teacher;

        @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
        @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
        private User student;

        @Column(nullable = false)
        private String activity;

        @Column(nullable = false)
        private String activityType; // "course" or "lab"

        @Column(nullable = false)
        private int grade;

        private String comment;

        @Column(nullable = false, unique = true)
        private String registrationNumber;

        @Column(nullable = false)
        private LocalDateTime timestamp = LocalDateTime.now();;


        // Getters and Setters

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public User getStudent() {
            return student;
        }

        public void setStudent(User student) {
            this.student = student;
        }

        public User getTeacher() {
            return teacher;
        }

        public void setTeacher(User teacher) {
            this.teacher = teacher;
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
            return grade; // Replace null with a default value
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

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }
    }
