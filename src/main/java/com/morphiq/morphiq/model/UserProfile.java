package com.morphiq.morphiq.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private String profession;
    
    @Column(length = 1000)
    private String behaviorPattern;
    
    @Column(length = 1000)
    private String communicationStyle;
    
    @Column(length = 1000)
    private String workflowPreferences;
    
    private String tonePreference;
    
    @Column(name = "created_at")
    private Long createdAt;
    
    @Column(name = "updated_at")
    private Long updatedAt;
    
    // Constructors
    public UserProfile() {}
    
    public UserProfile(Long id, String userId, String profession, String behaviorPattern, 
                      String communicationStyle, String workflowPreferences, String tonePreference, 
                      Long createdAt, Long updatedAt) {
        this.id = id;
        this.userId = userId;
        this.profession = profession;
        this.behaviorPattern = behaviorPattern;
        this.communicationStyle = communicationStyle;
        this.workflowPreferences = workflowPreferences;
        this.tonePreference = tonePreference;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public String getBehaviorPattern() {
        return behaviorPattern;
    }
    
    public String getCommunicationStyle() {
        return communicationStyle;
    }
    
    public String getWorkflowPreferences() {
        return workflowPreferences;
    }
    
    public String getTonePreference() {
        return tonePreference;
    }
    
    public Long getCreatedAt() {
        return createdAt;
    }
    
    public Long getUpdatedAt() {
        return updatedAt;
    }
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public void setBehaviorPattern(String behaviorPattern) {
        this.behaviorPattern = behaviorPattern;
    }
    
    public void setCommunicationStyle(String communicationStyle) {
        this.communicationStyle = communicationStyle;
    }
    
    public void setWorkflowPreferences(String workflowPreferences) {
        this.workflowPreferences = workflowPreferences;
    }
    
    public void setTonePreference(String tonePreference) {
        this.tonePreference = tonePreference;
    }
    
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}