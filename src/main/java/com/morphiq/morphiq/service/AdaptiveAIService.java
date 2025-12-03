package com.morphiq.morphiq.service;

import com.morphiq.morphiq.dto.ChatRequest;
import com.morphiq.morphiq.dto.ChatResponse;
import com.morphiq.morphiq.model.UserProfile;
import com.morphiq.morphiq.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AdaptiveAIService {

    @Value("${openai.api.key:}")
    private String openAiApiKey;

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public AdaptiveAIService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
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
        // In a real implementation, this would integrate with OpenAI API through LangChain4j
        // For now, we'll simulate adaptive responses based on profession
        
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an AI assistant behaving as a ")
              .append(userProfile.getProfession())
              .append(". Respond to the following message in a way that aligns with ")
              .append(userProfile.getProfession())
              .append(" profession's communication style and workflow preferences: ")
              .append(chatRequest.getMessage());
              
        // In a real implementation, we would use:
        /*
        if (!openAiApiKey.isEmpty()) {
            // ChatLanguageModel model = OpenAiChatModel.withApiKey(openAiApiKey);
            return model.generate(prompt.toString());
        }
        */
        
        // Simulated responses for different professions
        return generateSimulatedResponse(chatRequest.getMessage(), userProfile.getProfession());
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