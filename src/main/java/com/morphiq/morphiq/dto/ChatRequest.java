package com.morphiq.morphiq.dto;

public class ChatRequest {
    private String userId;
    private String message;
    private String profession;
    
    // Constructors
    public ChatRequest() {}
    
    public ChatRequest(String userId, String message, String profession) {
        this.userId = userId;
        this.message = message;
        this.profession = profession;
    }
    
    // Getters
    public String getUserId() {
        return userId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getProfession() {
        return profession;
    }
    
    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
}