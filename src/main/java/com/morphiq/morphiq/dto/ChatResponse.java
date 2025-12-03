package com.morphiq.morphiq.dto;

public class ChatResponse {
    private String response;
    private String professionContext;
    private String tone;
    
    // Constructors
    public ChatResponse() {}
    
    public ChatResponse(String response, String professionContext, String tone) {
        this.response = response;
        this.professionContext = professionContext;
        this.tone = tone;
    }
    
    // Getters
    public String getResponse() {
        return response;
    }
    
    public String getProfessionContext() {
        return professionContext;
    }
    
    public String getTone() {
        return tone;
    }
    
    // Setters
    public void setResponse(String response) {
        this.response = response;
    }
    
    public void setProfessionContext(String professionContext) {
        this.professionContext = professionContext;
    }
    
    public void setTone(String tone) {
        this.tone = tone;
    }
}