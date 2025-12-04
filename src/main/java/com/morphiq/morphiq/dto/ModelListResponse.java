package com.morphiq.morphiq.dto;

import java.util.List;

public class ModelListResponse {
    private List<String> modelNames;
    private String status;
    private String message;
    
    // Constructors
    public ModelListResponse() {}
    
    public ModelListResponse(List<String> modelNames, String status, String message) {
        this.modelNames = modelNames;
        this.status = status;
        this.message = message;
    }
    
    // Getters
    public List<String> getModelNames() {
        return modelNames;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
    
    // Setters
    public void setModelNames(List<String> modelNames) {
        this.modelNames = modelNames;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}