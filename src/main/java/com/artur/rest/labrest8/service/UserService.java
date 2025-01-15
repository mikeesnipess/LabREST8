    package com.artur.rest.labrest8.service;

    import com.artur.rest.labrest8.entities.Evaluation;
    import com.artur.rest.labrest8.entities.User;
    import com.artur.rest.labrest8.repository.EntityManagerProducer;
    import jakarta.enterprise.context.ApplicationScoped;
    import jakarta.inject.Inject;
    import jakarta.inject.Named;
    import jakarta.persistence.EntityManager;

    import java.util.List;
    import java.util.UUID;

    @Named
    @ApplicationScoped
    public class UserService {

        private EntityManager em;

        @Inject
        public void setEntityManager(EntityManagerProducer emp) {
            this.em = emp.getEntityManager();
        }


        public UserService() {}

        // Register a new user in the database
        public void registerUser(User user) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }

        public List<User> getAllTeachers() {
            return em.createQuery(
                            "SELECT u FROM User u " +
                                    "JOIN UserRole ur ON u.id = ur.user.id " +
                                    "WHERE ur.role = :role", User.class)
                    .setParameter("role", "Teacher")
                    .getResultList();
        }

        public List<User> getAllStudents() {
            return em.createQuery(
                            "SELECT u FROM User u " +
                                    "JOIN UserRole ur ON u.id = ur.user.id " +
                                    "WHERE ur.role = :role", User.class)
                    .setParameter("role", "Student")
                    .getResultList();
        }


        public List<Evaluation> getAllEvaluations() {
            return em.createQuery(
                            "SELECT u FROM Evaluation u ", Evaluation.class )
                    .getResultList();
        }

        public List<String> getUserRoles(UUID userId) {
            return em.createQuery(
                            "SELECT ur.role FROM UserRole ur " +
                                    "WHERE ur.user.id = :userId", String.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }


        // Find a user by their username
        public User findUserByUsername(String username) {
            try {
                return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();
            } catch (Exception e) {
                return null; // Return null if user not found
            }
        }

        // Get user by ID (UUID)
        public User getUserById(UUID userId) {
            try {
                return em.createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class)
                        .setParameter("userId", userId)
                        .getSingleResult();
            } catch (Exception e) {
                return null; // Return null if no user is found
            }
        }
    }
