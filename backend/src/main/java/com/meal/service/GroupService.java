package com.meal.service;

import com.meal.entity.GroupEntity;

public interface GroupService {

  Iterable<GroupEntity> findAll();
  GroupEntity findOne(int id);
  GroupEntity createGroup(GroupEntity group);
  GroupEntity updateGroup(GroupEntity group);
  void deleteGroup(int id);

  GroupEntity getUserGroup(int userId);
  Iterable<GroupEntity> getCoachGroups(int coachId);

  void createMember(int userId, int groupId);
  void deleteMember(int userId, int groupId);
}
