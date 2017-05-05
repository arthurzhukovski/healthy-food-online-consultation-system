package com.meal.service;

import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;

public interface GroupService {

  Iterable<GroupEntity> findAll();
  GroupEntity findOne(int id);
  GroupEntity createGroup(GroupEntity group);
  GroupEntity updateGroup(GroupEntity group);
  void deleteGroup(int id);

  Iterable<GroupEntity> findGroupsByCoachId (int coachId);
  Iterable<UserEntity> findUsersByGroupsId (int[] usersId);
  GroupEntity addUserToGroup(int groupId, int userId);
  GroupEntity deleteUserFromGroup(int groupId, int  userId);
}
