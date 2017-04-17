package com.meal.service;

import com.meal.entity.Message;

public interface MessageService {

  Iterable<Message> getMessagesByUserId(int id);
  Message createMessage(Message message);
  void deleteMessage(int id);
  void deleteMessagesByUserId(int id);
}
