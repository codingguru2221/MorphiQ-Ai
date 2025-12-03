package com.morphiq.morphiq.repository;

import com.morphiq.morphiq.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(String userId);
    Optional<UserProfile> findByUserIdAndProfession(String userId, String profession);
}