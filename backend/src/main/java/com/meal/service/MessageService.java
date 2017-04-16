package com.meal.service;

import com.meal.entity.MessageEntity;

public interface MessageService {

  Iterable<MessageEntity> getMessagesByUserId(int id);
  MessageEntity createMessage(MessageEntity message);
  void deleteMessage(int id);
  void deleteMessagesByUserId(int id);
}
