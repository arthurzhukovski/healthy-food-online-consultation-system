package com.meal.service.impl;

import com.meal.dao.GroupRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  
  private GroupRepository groupRepository;
  private UserRepository userRepository;
  private final Date dateTime;

  public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
    this.dateTime = new Date();
  }

  public Iterable<GroupEntity> findAll() {
    return groupRepository.findAll();
  }

  public GroupEntity findOne(int id) {
    return groupRepository.findOne(id);
  }

  @Transactional
  public GroupEntity createGroup(GroupEntity group) {
    validateGroup(group);
    group.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return groupRepository.save(group);
  }

  @Transactional
  public GroupEntity updateGroup(GroupEntity group) throws ServiceException {
    GroupEntity oldGroup = groupRepository.findOne(group.getId());
    oldGroup.setActive(group.getActive());
    UserEntity coach = userRepository.findOne(group.getCoach().getId());
    if(coach == null){
      throw new ServiceException("no such coach");
    }
    oldGroup.setCoach(group.getCoach());
    oldGroup.setStage(group.getStage());
    return groupRepository.save(oldGroup);
  }

  @Transactional
  public void deleteGroup(int id) {
    Iterable<UserEntity> users = userRepository.findByGroupId(id);
    for (UserEntity user: users) {
      user.setGroupId(null);
      userRepository.save(user);
    }
    groupRepository.delete(id);
  }

  public Iterable<GroupEntity> findGroupsByCoachId(int coachId) {
    return groupRepository.findByCoachId(coachId);
  }

  public Iterable<UserEntity> findUsersByGroupsId (int[] groupsId){
    List<Integer> ids = new ArrayList<Integer>();
    for(int i=0; i<groupsId.length; i++) {
      ids.add(groupsId[i]);
    }
    if (ids != null && !ids.isEmpty()) {
      return groupRepository.findUsersByGroupsId(ids);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Transactional
  public GroupEntity addUserToGroup (int groupId, int userId) throws ServiceException {
    UserEntity user = userRepository.findOne(userId);
    GroupEntity group = groupRepository.findOne(groupId);
    if(user == null){
      throw new ServiceException("no such user");
    }
    if(group == null) {
      throw new ServiceException("no such group");
    }
    user.setGroupId(groupId);
    user.setStage(group.getStage());
    userRepository.save(user);
    return group;
  }

  @Transactional
  public GroupEntity deleteUserFromGroup (int groupId, int userId) throws ServiceException {
    UserEntity user = userRepository.findOne(userId);
    GroupEntity group = groupRepository.findOne(groupId);
    if(user == null){
      throw new ServiceException("no such user");
    }
    if(group == null) {
      throw new ServiceException("no such group");
    }
    user.setGroupId(null);
    userRepository.save(user);
    return group;
  }

  private void validateGroup(GroupEntity groupEntity) throws ServiceException{
    Assert.notNull(groupEntity);
    if(groupEntity.getActive() == null) {
      throw new ServiceException("group invalid active value");
    }
    if(groupEntity.getCoach() != null) {
      if(userRepository.findOne(groupEntity.getCoach().getId()) == null) {
        throw new ServiceException("no such coach");
      }
    }
  }
}
