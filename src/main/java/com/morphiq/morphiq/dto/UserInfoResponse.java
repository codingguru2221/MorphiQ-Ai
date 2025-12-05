package com.morphiq.morphiq.dto;

import java.util.List;

public class UserInfoResponse {
    private String username;
    private List<String> roles;
    
    // Constructors
    public UserInfoResponse() {}
    
    public UserInfoResponse(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public List<String> getRoles() {
        return roles;
    }
    
    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}