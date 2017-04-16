package com.meal.dao;

import com.meal.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<MessageEntity, Integer> {
}
