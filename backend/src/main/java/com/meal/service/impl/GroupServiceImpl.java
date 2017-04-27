package com.meal.service.impl;

import com.meal.dao.GroupRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;
import com.meal.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  
  private GroupRepository groupRepository;
  private UserRepository userRepository;

  public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
  }

  public Iterable<GroupEntity> findAll() {
    return groupRepository.findAll();
  }

  public GroupEntity findOne(int id) {
    return groupRepository.findOne(id);
  }

  public GroupEntity createGroup(GroupEntity group) {
    return groupRepository.save(group);
  }

  public GroupEntity updateGroup(GroupEntity group) {
    return groupRepository.save(group);
  }

  public void deleteGroup(int id) {
    deleteGroup(id);
  }

  public GroupEntity getUserGroup(int userId) {
    return null;
  }

  public Iterable<GroupEntity> findGroupsByCoachId(int coachId) {
    return null;
  }

  public Iterable<GroupEntity> findGroupsByUsersId (int[] usersId){
    List<Integer> ids = new ArrayList<Integer>();
    for(int i=0; i<usersId.length; i++) {
      ids.add(usersId[i]);
    }
    if (ids != null && !ids.isEmpty()) {
      return groupRepository.findGroupsByUsersId(ids);
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
