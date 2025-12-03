package com.morphiq.morphiq.controller;

import com.morphiq.morphiq.dto.ChatRequest;
import com.morphiq.morphiq.dto.ChatResponse;
import com.morphiq.morphiq.service.AdaptiveAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final AdaptiveAIService adaptiveAIService;

    @Autowired
    public ChatController(AdaptiveAIService adaptiveAIService) {
        this.adaptiveAIService = adaptiveAIService;
    }

    @PostMapping("/message")
    public ChatResponse sendMessage(@RequestBody ChatRequest chatRequest) {
        return adaptiveAIService.processMessage(chatRequest);
    }
}