package com.meal.service.impl;

import com.meal.dao.MessageDao;
import com.meal.entity.MessageEntity;
import com.meal.service.MessageService;

public class MessageServiceImpl implements MessageService {

  private MessageDao messageDao;

  public MessageServiceImpl(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  public Iterable<MessageEntity> getMessagesByUserId(int id) {
    return null;
  }

  public MessageEntity createMessage(MessageEntity message) {
    return messageDao.save(message);
  }

  public void deleteMessage(int id) {
    messageDao.delete(id);
  }

  public void deleteMessagesByUserId(int id) {

  }
}
