package com.morphiq.morphiq.repository;

import com.morphiq.morphiq.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByUserIdOrderByTimestampAsc(String userId);
    List<Conversation> findByUserIdAndProfessionContext(String userId, String professionContext);
}