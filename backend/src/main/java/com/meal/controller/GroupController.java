package com.meal.controller;

import com.google.common.collect.Lists;
import com.meal.entity.*;
import com.meal.security.Secured;
import com.meal.service.GroupService;
import com.meal.service.impl.model.entity.GroupViewFactory;
import com.meal.service.impl.model.entity.UserViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@RestController
public class GroupController {

  private final GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @Secured({RoleEnum.ADMIN, RoleEnum.USER})
  @RequestMapping(value="/group/stat/{type}", method = RequestMethod.GET)
  public void getCoachStat(
          @PathVariable String type,
          @RequestParam("encrypt")  boolean isEncrypt,
          HttpServletResponse response) {
    List<GroupEntity> g =  Lists.newArrayList(groupService.findAll());
    List<GroupView> groups = new LinkedList<>();
    for (GroupEntity group:
            g) {
      groups.add(new GroupView(group, groupService));
    }
    GroupViewFactory factory = new GroupViewFactory();
    groupService.createDoc(type, response, groups, factory, isEncrypt);
  }

  /*
     GET ALL GROUPS
    */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups", method = RequestMethod.GET)
  public ResponseEntity<Iterable<GroupEntity>> getGroups() {
    Iterable<GroupEntity> groups = groupService.findAll();
    return new ResponseEntity<Iterable<GroupEntity>>(groups, HttpStatus.OK);
  }

  /*
     CREATE GROUP
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups", method = RequestMethod.POST)
  public ResponseEntity<GroupEntity> createGroup(@RequestBody GroupEntity group) {
    GroupEntity createdGroup = groupService.createGroup(group);
    return new ResponseEntity<GroupEntity>(createdGroup, HttpStatus.OK);
  }

  /*
    UPDATE GROUP
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups", method = RequestMethod.PUT)
  public ResponseEntity<GroupEntity> updateGroup(@RequestBody GroupEntity group) {
    GroupEntity updatedGroup = groupService.updateGroup(group);
    return new ResponseEntity<GroupEntity>(updatedGroup, HttpStatus.OK);
  }

  /*
    DELETE GROUP
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteGroup(@PathVariable(value = "id") int id) {
    groupService.deleteGroup(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET GROUP
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/groups/{id}", method = RequestMethod.GET)
  public ResponseEntity<GroupEntity> getGroup(@PathVariable(value = "id") int id) {
    GroupEntity group = groupService.findOne(id);
    return new ResponseEntity<GroupEntity>(group, HttpStatus.OK);
  }

  /*
    GET GROUPS BY USERS ID
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/groups/users", method = RequestMethod.POST)
  public ResponseEntity<Iterable<UserEntity>> getUsersByGroupsId(@RequestBody int[] groupsId) {
    Iterable<UserEntity> users = groupService.findUsersByGroupsId((groupsId));
    return new ResponseEntity<Iterable<UserEntity>>(users, HttpStatus.OK);
  }


  /*
   GET COACH GROUPS
  */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/groups/coach/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<GroupEntity>> findCoachGroups(@PathVariable(value = "id") int id) {
    Iterable<GroupEntity> groups = groupService.findGroupsByCoachId(id);
    return new ResponseEntity<Iterable<GroupEntity>>(groups, HttpStatus.OK);
  }

  /*
     ADD USER TO GROUP
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups/{groupId}/add/{userId}", method = RequestMethod.POST)
  public ResponseEntity<GroupEntity> addUserToGroup(@PathVariable(value = "groupId") int groupId,
                                                    @PathVariable(value = "userId") int userId) {
    GroupEntity group = groupService.addUserToGroup(groupId, userId);
    return new ResponseEntity<GroupEntity>(group, HttpStatus.OK);
  }


  /*
    DELETE USER FROM GROUP
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/groups/{groupId}/delete/{userId}", method = RequestMethod.POST)
  public ResponseEntity<GroupEntity> deleteMember(@PathVariable(value = "groupId") int groupId,
                                     @PathVariable(value = "userId") int userId) {
    GroupEntity group = groupService.deleteUserFromGroup(groupId, userId);
    return new ResponseEntity<GroupEntity>(group, HttpStatus.OK);
  }
}
