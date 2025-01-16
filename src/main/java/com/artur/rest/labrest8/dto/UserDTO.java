package com.artur.rest.labrest8.dto;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;

    // Constructor with arguments
    public UserDTO(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    // Default no-argument constructor (optional, but good practice)
    public UserDTO() {
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
