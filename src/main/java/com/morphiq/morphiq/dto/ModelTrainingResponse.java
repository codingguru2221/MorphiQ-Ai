package com.morphiq.morphiq.dto;

public class ModelTrainingResponse {
    private String modelName;
    private String profession;
    private String status;
    private String message;
    private Long trainingStartTime;
    private Long trainingEndTime;
    
    // Constructors
    public ModelTrainingResponse() {}
    
    public ModelTrainingResponse(String modelName, String profession, String status, String message, 
                                Long trainingStartTime, Long trainingEndTime) {
        this.modelName = modelName;
        this.profession = profession;
        this.status = status;
        this.message = message;
        this.trainingStartTime = trainingStartTime;
        this.trainingEndTime = trainingEndTime;
    }
    
    // Getters
    public String getModelName() {
        return modelName;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Long getTrainingStartTime() {
        return trainingStartTime;
    }
    
    public Long getTrainingEndTime() {
        return trainingEndTime;
    }
    
    // Setters
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setTrainingStartTime(Long trainingStartTime) {
        this.trainingStartTime = trainingStartTime;
    }
    
    public void setTrainingEndTime(Long trainingEndTime) {
        this.trainingEndTime = trainingEndTime;
    }
}