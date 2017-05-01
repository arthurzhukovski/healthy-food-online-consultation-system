package com.meal.service.impl;

import com.meal.dao.MessageRepository;
import com.meal.entity.MessageEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.MessageService;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private UserService userService;
  private MessageRepository messageRepository;
  private Date dateTime;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
    this.dateTime = new Date();
  }

  public Iterable<MessageEntity> getMessagesBySenderId(int id) {
    return messageRepository.findBySenderIdOrderByCreatedAtDesc(id);
  }

  public Iterable<MessageEntity> getMessagesByReceiverId(int id) {
    return messageRepository.findByReceiverIdOrderByCreatedAtDesc(id);
  }

  public MessageEntity createMessage(MessageEntity message) throws ServiceException {
    messageIsValid(message);
    message.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return messageRepository.save(message);
  }

  public void messageIsValid(MessageEntity message) throws ServiceException{
    if(HelpUtils.isNullOrEmpty(message.getText())){
      throw new ServiceException("message text is invalid");
    }
    if(userService.findOne(message.getSender().getId()) == null){
      throw new ServiceException("no such user-sender");
    }
    if(userService.findOne(message.getReceiver().getId()) == null){
      throw new ServiceException("no such user-receiver");
    }
  }
}
