package com.meal.controller;

import com.meal.entity.GroupEntity;
import com.meal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class GroupController {

  private final GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  /*
     GET ALL GROUPS
    */
  @RequestMapping(value="/groups", method = RequestMethod.GET)
  public ResponseEntity<Iterable<GroupEntity>> getGroups() {
    Iterable<GroupEntity> groups = groupService.findAll();
    return new ResponseEntity<Iterable<GroupEntity>>(groups, HttpStatus.OK);
  }

  /*
     CREATE GROUP
   */
  @RequestMapping(value="/groups", method = RequestMethod.POST)
  public ResponseEntity<GroupEntity> createGroup(@RequestBody GroupEntity group) {
    GroupEntity createdGroup =  groupService.createGroup(group);
    return new ResponseEntity<GroupEntity>(createdGroup, HttpStatus.OK);
  }

  /*
    UPDATE GROUP
   */
  @RequestMapping(value="/groups", method = RequestMethod.PUT)
  public ResponseEntity<GroupEntity> updateGroup(@RequestBody GroupEntity group) {
    GroupEntity updatedGroup = groupService.updateGroup(group);
    return new ResponseEntity<GroupEntity>(updatedGroup, HttpStatus.OK);
  }

  /*
    DELETE GROUP
   */
  @RequestMapping(value="/groups/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteGroup(@RequestParam int id) {
    groupService.deleteGroup(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET GROUP
   */
  @RequestMapping(value="/groups/{id}", method = RequestMethod.GET)
  public ResponseEntity<GroupEntity> getGroup(@RequestParam int id) {
    GroupEntity topic = groupService.findOne(id);
    return new ResponseEntity<GroupEntity>(topic, HttpStatus.OK);
  }

  /*
   GET USER GROUP
  */
  @RequestMapping(value="/groups/user/{id}", method = RequestMethod.GET)
  public ResponseEntity<GroupEntity> getGroups(@RequestParam int id) {
    GroupEntity group = groupService.getUserGroup(id);
    return new ResponseEntity<GroupEntity>(group, HttpStatus.OK);
  }
//
//  /*
//     CREATE MEMBER
//   */
//  @RequestMapping(value="/groups/members", method = RequestMethod.POST)
//  public ResponseEntity<Member> createMember(@RequestParam int id) {
//    groupService.createMember(member);
//    return new ResponseEntity<Member>(member, HttpStatus.OK);
//  }
//
//  /*
//    DELETE MEMBER
//   */
//  @RequestMapping(value="/members/{id}", method = RequestMethod.DELETE)
//  public ResponseEntity deleteMember(@RequestParam int id) {
//    groupService.deleteMember(id);
//    return new ResponseEntity(HttpStatus.OK);
//  }
}
