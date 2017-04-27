package com.meal.controller;

import com.meal.entity.MessageEntity;
import com.meal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
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
  public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity messageEntity) {
    messageService.createMessage(messageEntity);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
   GET OUTCOMING MESSAGES
  */
  @RequestMapping(value="/messages/outgoing/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<MessageEntity>> getOutgoingMessages(@PathVariable(value = "id") int id) {
    Iterable<MessageEntity> messages = messageService.getMessagesBySenderId(id);
    return new ResponseEntity<Iterable<MessageEntity>>(messages, HttpStatus.OK);
  }

  /*
   GET ALL USER MESSAGES
  */
  @RequestMapping(value="/messages/incoming/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<MessageEntity>> getIncomingMessages(@PathVariable(value = "id") int id) {
    Iterable<MessageEntity> messages = messageService.getMessagesByReceiverId(id);
    return new ResponseEntity<Iterable<MessageEntity>>(messages, HttpStatus.OK);
  }
  
}
