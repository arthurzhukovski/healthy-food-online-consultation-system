package com.meal.controller;

import com.meal.entity.Message;
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
  public ResponseEntity<Message> createMessage(@RequestBody Message message) {
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
  public ResponseEntity<Iterable<Message>> getMessages(@RequestParam int id) {
    Iterable<Message> messages = messageService.getMessagesByUserId(id);
    return new ResponseEntity<Iterable<Message>>(messages, HttpStatus.OK);
  }

  //  /*
//    GET MESSAGE
//   */
//  @RequestMapping(value="/messages/:id", method = RequestMethod.GET)
//  public ResponseEntity<Message> getMessage(int id) {
//    try {
//      Message topic = messageService.findOne(id);
//      //TODO: throw exception
//      return new ResponseEntity<Message>(topic, HttpStatus.OK);
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
  
}
