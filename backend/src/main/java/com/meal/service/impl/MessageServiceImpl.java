package com.meal.service.impl;

import com.meal.dao.MessageRepository;
import com.meal.entity.MessageEntity;
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
    return messageRepository.findBySenderId(id);
  }

  public Iterable<MessageEntity> getMessagesByReceiverId(int id) {
    return messageRepository.findByReceiverId(id);
  }

  public MessageEntity createMessage(MessageEntity message) {
    if(!messageIsValid(message)){
      return null;
    }
    message.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return messageRepository.save(message);
  }

  public boolean messageIsValid(MessageEntity message){
    if(HelpUtils.isNullOrEmpty(message.getText())){
      return false;
    }
    if(userService.findOne(message.getSender().getId()) == null){
      return false;
    }
    if(userService.findOne(message.getReceiver().getId()) == null){
      return false;
    }
    return true;
  }
}
