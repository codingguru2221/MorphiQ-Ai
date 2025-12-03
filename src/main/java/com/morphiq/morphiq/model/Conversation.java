package com.morphiq.morphiq.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conversations")
public class Conversation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(length = 5000)
    private String userInput;
    
    @Column(length = 5000)
    private String aiResponse;
    
    private String professionContext;
    
    @Column(name = "timestamp")
    private Long timestamp;
    
    // Constructors
    public Conversation() {}
    
    public Conversation(Long id, String userId, String userInput, String aiResponse, 
                       String professionContext, Long timestamp) {
        this.id = id;
        this.userId = userId;
        this.userInput = userInput;
        this.aiResponse = aiResponse;
        this.professionContext = professionContext;
        this.timestamp = timestamp;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getUserInput() {
        return userInput;
    }
    
    public String getAiResponse() {
        return aiResponse;
    }
    
    public String getProfessionContext() {
        return professionContext;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    
    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }
    
    public void setProfessionContext(String professionContext) {
        this.professionContext = professionContext;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}