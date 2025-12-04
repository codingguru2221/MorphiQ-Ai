package com.morphiq.morphiq.controller;

import com.morphiq.morphiq.dto.ModelListResponse;
import com.morphiq.morphiq.dto.ModelTrainingRequest;
import com.morphiq.morphiq.dto.ModelTrainingResponse;
import com.morphiq.morphiq.service.ModelTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/models")
@CrossOrigin(origins = "*")
public class ModelController {

    private final ModelTrainingService modelTrainingService;

    @Autowired
    public ModelController(ModelTrainingService modelTrainingService) {
        this.modelTrainingService = modelTrainingService;
    }

    @PostMapping("/train")
    public ModelTrainingResponse trainModel(@RequestBody ModelTrainingRequest request) {
        return modelTrainingService.trainModel(request);
    }

    @GetMapping("/status/{modelName}")
    public ModelTrainingResponse getModelStatus(@PathVariable String modelName) {
        return modelTrainingService.getModelStatus(modelName);
    }

    @PutMapping("/behavior/{modelName}")
    public ModelTrainingResponse updateModelBehavior(
            @PathVariable String modelName,
            @RequestParam String behaviorPatterns,
            @RequestParam String communicationStyles,
            @RequestParam String workflowPreferences) {
        
        boolean success = modelTrainingService.updateModelBehavior(
                modelName, behaviorPatterns, communicationStyles, workflowPreferences);
        
        ModelTrainingResponse response = new ModelTrainingResponse();
        response.setModelName(modelName);
        
        if (success) {
            response.setStatus("SUCCESS");
            response.setMessage("Model behavior updated successfully");
        } else {
            response.setStatus("ERROR");
            response.setMessage("Model not found: " + modelName);
        }
        
        return response;
    }

    @GetMapping("/list")
    public ModelListResponse listAllModels() {
        return modelTrainingService.getAllModels();
    }
}