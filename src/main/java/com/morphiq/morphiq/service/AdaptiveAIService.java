package com.morphiq.morphiq.service;

import com.morphiq.morphiq.dto.ChatRequest;
import com.morphiq.morphiq.dto.ChatResponse;
import com.morphiq.morphiq.model.AIModel;
import com.morphiq.morphiq.model.UserProfile;
import com.morphiq.morphiq.repository.AIModelRepository;
import com.morphiq.morphiq.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Import OpenAI and LangChain4j classes
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;

import java.time.Instant;
import java.util.Optional;

@Service
public class AdaptiveAIService {

    @Value("${openai.api.key:}")
    private String openAiApiKey;

    private final UserProfileRepository userProfileRepository;
    private final AIModelRepository aiModelRepository;

    @Autowired
    public AdaptiveAIService(UserProfileRepository userProfileRepository, AIModelRepository aiModelRepository) {
        this.userProfileRepository = userProfileRepository;
        this.aiModelRepository = aiModelRepository;
    }

    public ChatResponse processMessage(ChatRequest chatRequest) {
        // Get or create user profile
        UserProfile userProfile = getOrCreateUserProfile(chatRequest);

        // Adapt response based on profession
        String adaptedResponse = generateAdaptedResponse(chatRequest, userProfile);

        // Save conversation
        // saveConversation(chatRequest, adaptedResponse, userProfile.getProfession());

        return new ChatResponse(adaptedResponse, userProfile.getProfession(), userProfile.getTonePreference());
    }

    private UserProfile getOrCreateUserProfile(ChatRequest chatRequest) {
        Optional<UserProfile> existingProfile = userProfileRepository.findByUserId(chatRequest.getUserId());
        
        if (existingProfile.isPresent()) {
            UserProfile profile = existingProfile.get();
            // Update profession if it's different
            if (!profile.getProfession().equals(chatRequest.getProfession())) {
                profile.setProfession(chatRequest.getProfession());
                profile.setUpdatedAt(Instant.now().getEpochSecond());
                return userProfileRepository.save(profile);
            }
            return profile;
        } else {
            // Create new user profile
            UserProfile newUserProfile = new UserProfile();
            newUserProfile.setUserId(chatRequest.getUserId());
            newUserProfile.setProfession(chatRequest.getProfession());
            newUserProfile.setBehaviorPattern(getDefaultBehaviorPattern(chatRequest.getProfession()));
            newUserProfile.setCommunicationStyle(getDefaultCommunicationStyle(chatRequest.getProfession()));
            newUserProfile.setWorkflowPreferences(getDefaultWorkflowPreferences(chatRequest.getProfession()));
            newUserProfile.setTonePreference(getDefaultTonePreference(chatRequest.getProfession()));
            newUserProfile.setCreatedAt(Instant.now().getEpochSecond());
            newUserProfile.setUpdatedAt(Instant.now().getEpochSecond());
            
            return userProfileRepository.save(newUserProfile);
        }
    }

    private String generateAdaptedResponse(ChatRequest chatRequest, UserProfile userProfile) {
        // Check if there's a trained model for this profession
        Optional<AIModel> trainedModel = aiModelRepository.findByProfession(userProfile.getProfession());
        
        if (trainedModel.isPresent()) {
            // Use the trained model's behavior patterns
            return generateResponseUsingTrainedModel(chatRequest, userProfile, trainedModel.get());
        } else {
            // Fall back to the default behavior
            return generateDefaultResponse(chatRequest, userProfile);
        }
    }

    private String generateResponseUsingTrainedModel(ChatRequest chatRequest, UserProfile userProfile, AIModel trainedModel) {
        // Use OpenAI API through LangChain4j if API key is available
        if (!openAiApiKey.isEmpty()) {
            try {
                ChatLanguageModel model = OpenAiChatModel.withApiKey(openAiApiKey);
                
                StringBuilder prompt = new StringBuilder();
                prompt.append("You are an AI assistant trained to behave as a ")
                      .append(userProfile.getProfession())
                      .append(" with these characteristics:\n")
                      .append("Behavior Patterns: ").append(trainedModel.getBehaviorPatterns()).append("\n")
                      .append("Communication Style: ").append(trainedModel.getCommunicationStyles()).append("\n")
                      .append("Workflow Preferences: ").append(trainedModel.getWorkflowPreferences()).append("\n")
                      .append("Respond to the following message in a way that aligns with these characteristics: ")
                      .append(chatRequest.getMessage());
                      
                return model.generate(prompt.toString());
            } catch (Exception e) {
                // Fallback to simulated response if OpenAI call fails
                return generateSimulatedResponseWithModel(chatRequest.getMessage(), trainedModel);
            }
        } else {
            // Fallback to simulated response if no API key
            return generateSimulatedResponseWithModel(chatRequest.getMessage(), trainedModel);
        }
    }

    private String generateDefaultResponse(ChatRequest chatRequest, UserProfile userProfile) {
        // Use OpenAI API through LangChain4j if API key is available
        if (!openAiApiKey.isEmpty()) {
            try {
                ChatLanguageModel model = OpenAiChatModel.withApiKey(openAiApiKey);
                
                StringBuilder prompt = new StringBuilder();
                prompt.append("You are an AI assistant behaving as a ")
                      .append(userProfile.getProfession())
                      .append(". Respond to the following message in a way that aligns with ")
                      .append(userProfile.getProfession())
                      .append(" profession's communication style and workflow preferences: ")
                      .append(chatRequest.getMessage());
                      
                return model.generate(prompt.toString());
            } catch (Exception e) {
                // Fallback to simulated response if OpenAI call fails
                return generateSimulatedResponse(chatRequest.getMessage(), userProfile.getProfession());
            }
        } else {
            // Fallback to simulated response if no API key
            return generateSimulatedResponse(chatRequest.getMessage(), userProfile.getProfession());
        }
    }

    private String generateSimulatedResponseWithModel(String message, AIModel trainedModel) {
        // Simulate response using trained model characteristics
        return "As a trained " + trainedModel.getProfession() + ", I understand your query about '" + message + 
               "'. Based on my specialized training, I recommend approaching this from a " + 
               trainedModel.getBehaviorPatterns() + " perspective.";
    }

    private String generateSimulatedResponse(String message, String profession) {
        switch (profession.toLowerCase()) {
            case "doctor":
                return "As a medical professional, I understand your concern about '" + message + "'. Based on medical protocols, I recommend consulting with a healthcare provider for personalized advice.";
            case "engineer":
                return "From an engineering perspective, '" + message + "' presents interesting technical challenges. I suggest breaking down the problem into smaller components for systematic analysis.";
            case "teacher":
                return "In educational terms, '" + message + "' is an important concept that can be explained through practical examples and interactive learning approaches.";
            case "lawyer":
                return "Legally speaking, '" + message + "' involves several considerations under relevant statutes. It's advisable to review applicable regulations and precedents.";
            default:
                return "I'm adapting my response based on your role as a " + profession + ". Regarding '" + message + "', I suggest considering multiple perspectives for a comprehensive understanding.";
        }
    }

    private String getDefaultBehaviorPattern(String profession) {
        switch (profession.toLowerCase()) {
            case "doctor":
                return "Analytical, detail-oriented, evidence-based decision making";
            case "engineer":
                return "Problem-solving, systematic approach, logical reasoning";
            case "teacher":
                return "Patient, explanatory, structured knowledge transfer";
            case "lawyer":
                return "Analytical, precise, precedent-aware, risk-conscious";
            default:
                return "Professional, helpful, adaptive";
        }
    }

    private String getDefaultCommunicationStyle(String profession) {
        switch (profession.toLowerCase()) {
            case "doctor":
                return "Clear, empathetic, using medical terminology when appropriate";
            case "engineer":
                return "Technical, precise, solution-focused";
            case "teacher":
                return "Explanatory, encouraging, using analogies";
            case "lawyer":
                return "Formal, precise, citing authorities";
            default:
                return "Professional, clear, respectful";
        }
    }

    private String getDefaultWorkflowPreferences(String profession) {
        switch (profession.toLowerCase()) {
            case "doctor":
                return "Prioritize critical issues, follow medical protocols, document decisions";
            case "engineer":
                return "Break down problems, follow systematic approaches, document solutions";
            case "teacher":
                return "Explain concepts, provide examples, encourage questions";
            case "lawyer":
                return "Research precedents, cite authorities, minimize risks";
            default:
                return "Organized, efficient, thorough";
        }
    }

    private String getDefaultTonePreference(String profession) {
        switch (profession.toLowerCase()) {
            case "doctor":
                return "Professional yet empathetic";
            case "engineer":
                return "Direct and technical";
            case "teacher":
                return "Encouraging and supportive";
            case "lawyer":
                return "Formal and precise";
            default:
                return "Professional and friendly";
        }
    }
}