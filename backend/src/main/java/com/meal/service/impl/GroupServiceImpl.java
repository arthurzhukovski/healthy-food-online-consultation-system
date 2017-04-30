package com.meal.service.impl;

import com.meal.dao.GroupRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;
import com.meal.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

  public GroupEntity createGroup(GroupEntity group) {
    group.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return groupRepository.save(group);
  }

  public GroupEntity updateGroup(GroupEntity group) {
    GroupEntity oldGroup = groupRepository.findOne(group.getId());
    oldGroup.setActive(group.getActive());
    oldGroup.setCoach(group.getCoach());
    oldGroup.setStage(group.getStage());
    return groupRepository.save(oldGroup);
  }

  public void deleteGroup(int id) {
    deleteGroup(id);
  }

  public GroupEntity getUserGroup(int userId) {
    return null;
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
      return null;
    }
  }

  public GroupEntity addUserToGroup (int groupId, int userId) {
    UserEntity u = userRepository.findOne(userId);
    u.setGroupId(groupId);
    userRepository.save(u);
    return groupRepository.findOne(groupId);
  }

  public GroupEntity deleteUserFromGroup (int groupId, int userId) {
    UserEntity u = userRepository.findOne(userId);
    u.setGroupId(null);
    userRepository.save(u);
    return groupRepository.findOne(groupId);
  }


//  public void createMember(int userId, int groupId) {
//    MemberEntity memberEntity = new MemberEntity();
//    memberEntity.setUserId(userId);
//    memberEntity.setGroupId(groupId);
//    memberRepository.save(memberEntity);
//  }
//
//  public void deleteMember(int userId, int groupId) {
//    MemberEntity memberEntity = new MemberEntity();
//    memberEntity.setUserId(userId);
//    memberEntity.setGroupId(groupId);
//    memberRepository.delete(memberEntity);
//  }
}
