package com.meal.controller;

import com.meal.entity.MessageEntity;
import com.meal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  /*
     CREATE MESSAGE
   */
  @RequestMapping(value="/messages", method = RequestMethod.POST)
  public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity message) {
    messageService.createMessage(message);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    DELETE MESSAGE
   */
  @RequestMapping(value="/messages/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteMessage(@RequestParam int id) {
      messageService.deleteMessage(id);
      return new ResponseEntity(HttpStatus.OK);
  }

  /*
   GET ALL USER MESSAGES
  */
  @RequestMapping(value="/messages/user/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<MessageEntity>> getMessages(@RequestParam int id) {
    Iterable<MessageEntity> messages = messageService.getMessagesByUserId(id);
    return new ResponseEntity<Iterable<MessageEntity>>(messages, HttpStatus.OK);
  }

  //  /*
//    GET MESSAGE
//   */
//  @RequestMapping(value="/messages/:id", method = RequestMethod.GET)
//  public ResponseEntity<MessageEntity> getMessage(int id) {
//    try {
//      MessageEntity topic = messageService.findOne(id);
//      //TODO: throw exception
//      return new ResponseEntity<MessageEntity>(topic, HttpStatus.OK);
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
  
}
