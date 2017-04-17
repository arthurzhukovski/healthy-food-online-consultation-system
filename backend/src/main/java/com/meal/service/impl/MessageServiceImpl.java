package com.meal.service.impl;

import com.meal.dao.MessageRepository;
import com.meal.entity.Message;
import com.meal.service.MessageService;

public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;

  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Iterable<Message> getMessagesByUserId(int id) {
    return null;
  }

  public Message createMessage(Message message) {
    return messageRepository.save(message);
  }

  public void deleteMessage(int id) {
    messageRepository.delete(id);
  }

  public void deleteMessagesByUserId(int id) {

  }
}
