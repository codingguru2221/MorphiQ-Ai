package com.morphiq.morphiq.service;

import com.morphiq.morphiq.dto.ModelListResponse;
import com.morphiq.morphiq.dto.ModelTrainingRequest;
import com.morphiq.morphiq.dto.ModelTrainingResponse;
import com.morphiq.morphiq.model.AIModel;
import com.morphiq.morphiq.repository.AIModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelTrainingService {

    private final AIModelRepository aiModelRepository;

    @Autowired
    public ModelTrainingService(AIModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    public ModelTrainingResponse trainModel(ModelTrainingRequest request) {
        ModelTrainingResponse response = new ModelTrainingResponse();
        response.setModelName(request.getModelName());
        response.setProfession(request.getProfession());
        response.setTrainingStartTime(Instant.now().getEpochSecond());

        try {
            // Check if model already exists
            if (aiModelRepository.existsByModelName(request.getModelName())) {
                response.setStatus("ERROR");
                response.setMessage("Model with name " + request.getModelName() + " already exists");
                response.setTrainingEndTime(Instant.now().getEpochSecond());
                return response;
            }

            // Validate training data path
            if (request.getTrainingDataPath() != null && !request.getTrainingDataPath().isEmpty()) {
                File trainingDataDir = new File(request.getTrainingDataPath());
                if (!trainingDataDir.exists() || !trainingDataDir.isDirectory()) {
                    response.setStatus("ERROR");
                    response.setMessage("Training data path is invalid or does not exist: " + request.getTrainingDataPath());
                    response.setTrainingEndTime(Instant.now().getEpochSecond());
                    return response;
                }
            }

            // Create new AI model
            AIModel aiModel = new AIModel();
            aiModel.setModelName(request.getModelName());
            aiModel.setProfession(request.getProfession());
            aiModel.setBehaviorPatterns(request.getBehaviorPatterns());
            aiModel.setCommunicationStyles(request.getCommunicationStyles());
            aiModel.setWorkflowPreferences(request.getWorkflowPreferences());
            aiModel.setTrainingDataPath(request.getTrainingDataPath());
            aiModel.setTrainingStatus("TRAINING");
            aiModel.setCreatedAt(Instant.now().getEpochSecond());
            aiModel.setUpdatedAt(Instant.now().getEpochSecond());

            // Save model to database
            AIModel savedModel = aiModelRepository.save(aiModel);

            // In a real implementation, this is where we would:
            // 1. Load the training data from the specified path
            // 2. Process text files, images, etc.
            // 3. Train the AI model using the data
            // 4. Save the trained model weights/checkpoints
            // 5. Update the model status to "TRAINED"

            // For this implementation, we'll simulate the training process
            simulateTrainingProcess(savedModel);

            response.setStatus("SUCCESS");
            response.setMessage("Model " + request.getModelName() + " created and training initiated successfully");
            response.setTrainingEndTime(Instant.now().getEpochSecond());
            
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Failed to train model: " + e.getMessage());
            response.setTrainingEndTime(Instant.now().getEpochSecond());
        }

        return response;
    }

    public ModelTrainingResponse getModelStatus(String modelName) {
        ModelTrainingResponse response = new ModelTrainingResponse();
        response.setModelName(modelName);
        
        Optional<AIModel> modelOpt = aiModelRepository.findByModelName(modelName);
        if (modelOpt.isPresent()) {
            AIModel model = modelOpt.get();
            response.setProfession(model.getProfession());
            response.setStatus(model.getTrainingStatus());
            response.setMessage("Model status retrieved successfully");
        } else {
            response.setStatus("NOT_FOUND");
            response.setMessage("Model with name " + modelName + " not found");
        }
        
        return response;
    }

    public ModelListResponse getAllModels() {
        try {
            List<AIModel> models = aiModelRepository.findAll();
            List<String> modelNames = models.stream()
                    .map(AIModel::getModelName)
                    .collect(Collectors.toList());
            
            return new ModelListResponse(modelNames, "SUCCESS", "Models retrieved successfully");
        } catch (Exception e) {
            return new ModelListResponse(null, "ERROR", "Failed to retrieve models: " + e.getMessage());
        }
    }

    private void simulateTrainingProcess(AIModel model) {
        // Simulate training by updating the model status
        model.setTrainingStatus("TRAINED");
        model.setUpdatedAt(Instant.now().getEpochSecond());
        aiModelRepository.save(model);
    }

    public boolean updateModelBehavior(String modelName, String newBehaviorPatterns, 
                                      String newCommunicationStyles, String newWorkflowPreferences) {
        Optional<AIModel> modelOpt = aiModelRepository.findByModelName(modelName);
        if (modelOpt.isPresent()) {
            AIModel model = modelOpt.get();
            model.setBehaviorPatterns(newBehaviorPatterns);
            model.setCommunicationStyles(newCommunicationStyles);
            model.setWorkflowPreferences(newWorkflowPreferences);
            model.setUpdatedAt(Instant.now().getEpochSecond());
            aiModelRepository.save(model);
            return true;
        }
        return false;
    }
}