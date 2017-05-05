package com.meal.dao;

import com.meal.entity.MessageEntity;
import com.meal.service.MessageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

  //@Query("select m from Message m where m.sender_id = :id")
  Iterable<MessageEntity> findBySenderIdOrderByCreatedAtDesc(@Param("sender_id")int sender_id);

  //@Query("select m from Message m where m.receiver_id = :id")
  Iterable<MessageEntity> findByReceiverIdOrderByCreatedAtDesc(@Param("receiver_id")int receiver_id);
}
