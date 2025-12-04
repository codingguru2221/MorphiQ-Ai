package com.morphiq.morphiq.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ai_models")
public class AIModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String modelName;
    
    @Column(nullable = false)
    private String profession;
    
    @Column(length = 5000)
    private String behaviorPatterns;
    
    @Column(length = 5000)
    private String communicationStyles;
    
    @Column(length = 5000)
    private String workflowPreferences;
    
    @Column(length = 1000)
    private String trainingDataPath;
    
    @Column(name = "created_at")
    private Long createdAt;
    
    @Column(name = "updated_at")
    private Long updatedAt;
    
    @Column(name = "training_status")
    private String trainingStatus;
    
    // Constructors
    public AIModel() {}
    
    public AIModel(Long id, String modelName, String profession, String behaviorPatterns, 
                  String communicationStyles, String workflowPreferences, String trainingDataPath,
                  Long createdAt, Long updatedAt, String trainingStatus) {
        this.id = id;
        this.modelName = modelName;
        this.profession = profession;
        this.behaviorPatterns = behaviorPatterns;
        this.communicationStyles = communicationStyles;
        this.workflowPreferences = workflowPreferences;
        this.trainingDataPath = trainingDataPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.trainingStatus = trainingStatus;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public String getBehaviorPatterns() {
        return behaviorPatterns;
    }
    
    public String getCommunicationStyles() {
        return communicationStyles;
    }
    
    public String getWorkflowPreferences() {
        return workflowPreferences;
    }
    
    public String getTrainingDataPath() {
        return trainingDataPath;
    }
    
    public Long getCreatedAt() {
        return createdAt;
    }
    
    public Long getUpdatedAt() {
        return updatedAt;
    }
    
    public String getTrainingStatus() {
        return trainingStatus;
    }
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public void setBehaviorPatterns(String behaviorPatterns) {
        this.behaviorPatterns = behaviorPatterns;
    }
    
    public void setCommunicationStyles(String communicationStyles) {
        this.communicationStyles = communicationStyles;
    }
    
    public void setWorkflowPreferences(String workflowPreferences) {
        this.workflowPreferences = workflowPreferences;
    }
    
    public void setTrainingDataPath(String trainingDataPath) {
        this.trainingDataPath = trainingDataPath;
    }
    
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }
}