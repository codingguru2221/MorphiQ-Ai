package com.morphiq.morphiq.repository;

import com.morphiq.morphiq.model.AIModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AIModelRepository extends JpaRepository<AIModel, Long> {
    Optional<AIModel> findByModelName(String modelName);
    Optional<AIModel> findByProfession(String profession);
    boolean existsByModelName(String modelName);
}