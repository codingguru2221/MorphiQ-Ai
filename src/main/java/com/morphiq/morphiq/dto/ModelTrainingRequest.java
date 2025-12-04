package com.morphiq.morphiq.dto;

public class ModelTrainingRequest {
    private String modelName;
    private String profession;
    private String trainingDataPath; // Path to training data (text files, images, etc.)
    private String behaviorPatterns;
    private String communicationStyles;
    private String workflowPreferences;
    
    // Constructors
    public ModelTrainingRequest() {}
    
    public ModelTrainingRequest(String modelName, String profession, String trainingDataPath, 
                               String behaviorPatterns, String communicationStyles, String workflowPreferences) {
        this.modelName = modelName;
        this.profession = profession;
        this.trainingDataPath = trainingDataPath;
        this.behaviorPatterns = behaviorPatterns;
        this.communicationStyles = communicationStyles;
        this.workflowPreferences = workflowPreferences;
    }
    
    // Getters
    public String getModelName() {
        return modelName;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public String getTrainingDataPath() {
        return trainingDataPath;
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
    
    // Setters
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public void setTrainingDataPath(String trainingDataPath) {
        this.trainingDataPath = trainingDataPath;
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
}