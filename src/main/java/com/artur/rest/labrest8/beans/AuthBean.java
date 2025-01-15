package com.artur.rest.labrest8.beans;

import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.entities.UserRole;
import com.artur.rest.labrest8.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;
    private String role; // Role (e.g., Admin, Student, Teacher)
    private User loggedInUser;

    @Inject
    private UserService userService;



    // Login method
    public String login() {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Optionally add message: Invalid credentials
            return "login.xhtml"; // Stay on login page if credentials are empty
        }

        User user = userService.findUserByUsername(username);
        UUID userId = user.getId();
        String roleUser = userService.getUserRoles(userId).get(0);

        if (user != null && user.getPassword().equals(password) && roleUser.equals("Student")) {
            loggedInUser = user; // Set logged-in user
            return "studentEvaluation.xhtml"; // Redirect to dashboard
        }else if(user != null && user.getPassword().equals(password) && roleUser.equals("Teacher")) {
            loggedInUser = user;
            TeacherEvaluationBean teacherEvaluationBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{teacherEvaluationBean}", TeacherEvaluationBean.class);
            teacherEvaluationBean.setLoggedInTeacher(loggedInUser);
            teacherEvaluationBean.loadEvaluations(); // Load evaluations
            return "teacherEvaluation.xhtml";
        }
        else if (user != null && user.getPassword().equals(password) && roleUser.equals("Admin")) {
            loggedInUser = user;
            this.role = roleUser; // Save the role in the AuthBean

            // Store the AuthBean in the session for future role checks
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authBean", this);

            // Optionally, load admin-specific data
            AdminEvaluationBean adminEvaluationBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{adminEvaluationBean}", AdminEvaluationBean.class);
            adminEvaluationBean.loadAllEvaluations();
            return "adminEvaluation.xhtml";
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
            return "register.xhtml"; // Stay on registration page
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Create and assign the role
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(newUser);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole); // Add the selected role
        newUser.setRoles(roles);

        try {
            userService.registerUser(newUser); // Save user to the database
            loggedInUser = newUser; // Set the newly registered user as the logged-in user
            return "login.xhtml"; // Redirect to the login page
        } catch (Exception e) {
            // Optionally, add a message for registration failure
            return "register.xhtml"; // Stay on registration page
        }
    }
    public String getUsername() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        username = request.getUserPrincipal().getName();
        System.out.println("Authenticated Username: " + username); // Check if the username is being retrieved
        return username;
    }


    // Getters and setters
//    public String getUsername() {
//        return username;
//    }

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
