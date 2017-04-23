package com.meal.service;

import com.meal.entity.MessageEntity;

public interface MessageService {

  Iterable<MessageEntity> getMessagesBySenderId(int id);
  Iterable<MessageEntity> getMessagesByReceiverId(int id);
  MessageEntity createMessage(MessageEntity messageEntity);
}
