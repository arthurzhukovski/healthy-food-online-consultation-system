package com.meal.service;

import com.meal.entity.GroupEntity;
import com.meal.entity.GroupView;
import com.meal.entity.UserEntity;
import com.meal.service.impl.model.entity.ViewerFactoryInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

  String getUsers(int id);
  String getReports(int id);
  String getCoach(int id);
  void createDoc(String type,
                 HttpServletResponse response,
                 List<GroupView> entities,
                 ViewerFactoryInterface viewerFactory,
                 boolean isEncrypt);
}
