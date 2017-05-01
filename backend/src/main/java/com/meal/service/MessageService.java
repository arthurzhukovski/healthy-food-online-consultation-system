package com.meal.service;

import com.meal.entity.MessageEntity;
import com.meal.service.Exception.ServiceException;

public interface MessageService {

  Iterable<MessageEntity> getMessagesBySenderId(int id);
  Iterable<MessageEntity> getMessagesByReceiverId(int id);
  MessageEntity createMessage(MessageEntity messageEntity) throws ServiceException;
}
