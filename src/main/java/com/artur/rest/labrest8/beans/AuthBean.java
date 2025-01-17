package com.artur.rest.labrest8.beans;

import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.entities.UserRole;
import com.artur.rest.labrest8.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Named
@RequestScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;
    private String role; // Role (e.g., Admin, Student, Teacher)
    private User loggedInUser;

    @Context SecurityContext securityContext;
    @Inject
    private UserService userService;



    // Login method
    public String login() {
        if (username == null || username.isEmpty() ) {
            // Optionally add message: Invalid credentials
            return "login.jsp"; // Stay on login page if credentials are empty
        }

        User user = userService.findUserByUsername(username);
        UUID userId = user.getId();
        String roleUser = userService.getUserRoles(userId).get(0);

        if (user != null && roleUser.equals("Student")) {
            loggedInUser = user; // Set logged-in user
            return "studentEvaluation.jsp"; // Redirect to dashboard
        }else if(user != null  && roleUser.equals("Teacher")) {
            loggedInUser = user;
//            TeacherEvaluationBean teacherEvaluationBean = FacesContext.getCurrentInstance()
//                    .getApplication()
//                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{teacherEvaluationBean}", TeacherEvaluationBean.class);
//            teacherEvaluationBean.setLoggedInTeacher(loggedInUser);
//            teacherEvaluationBean.loadEvaluations(); // Load evaluations
            return "teacherEvaluation.jsp";
        }
        else if (user != null  && roleUser.equals("Admin")) {
            loggedInUser = user;
            this.role = roleUser; // Save the role in the AuthBean

            // Optionally, load admin-specific data
//            AdminEvaluationBean adminEvaluationBean = FacesContext.getCurrentInstance()
//                    .getApplication()
//                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{adminEvaluationBean}", AdminEvaluationBean.class);
//            adminEvaluationBean.loadAllEvaluations();
            return "adminEvaluation.jsp";
        }

        else {
            // Optionally, add a message for incorrect login
            return "login.jsp"; // Redirect to login page
        }
    }

    // Register method
    public String register() {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || role == null || role.isEmpty()) {
            // Optionally add a message: Missing credentials or role
            return "register.jsp"; // Stay on registration page
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Create and assign the role
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(newUser);
        userRole.setUsername(username);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole); // Add the selected role
        newUser.setRoles(roles);

        try {
            userService.registerUser(newUser); // Save user to the database
            loggedInUser = newUser; // Set the newly registered user as the logged-in user
            return "login"; // Redirect to the login page
        } catch (Exception e) {
            // Optionally, add a message for registration failure
            return "register.jsp"; // Stay on registration page
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String   getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
