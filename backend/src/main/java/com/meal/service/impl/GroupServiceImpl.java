package com.meal.service.impl;

import com.meal.dao.GroupDao;
import com.meal.dao.MemberDao;
import com.meal.entity.GroupEntity;
import com.meal.entity.MemberEntity;
import com.meal.service.GroupService;

public class GroupServiceImpl implements GroupService {
  
  private GroupDao groupDao;
  private MemberDao memberDao;

  public GroupServiceImpl(GroupDao groupDao, MemberDao memberDao) {
    this.groupDao = groupDao;
    this.memberDao = memberDao;
  }

  public Iterable<GroupEntity> findAll() {
    return groupDao.findAll();
  }

  public GroupEntity findOne(int id) {
    return groupDao.findOne(id);
  }

  public GroupEntity createGroup(GroupEntity group) {
    return groupDao.save(group);
  }

  public GroupEntity updateGroup(GroupEntity group) {
    return groupDao.save(group);
  }

  public void deleteGroup(int id) {
    deleteGroup(id);
  }

  public GroupEntity getUserGroup(int userId) {
    return null;
  }

  public Iterable<GroupEntity> getCoachGroups(int coachId) {
    return null;
  }

  public void createMember(int userId, int groupId) {
    MemberEntity member = new MemberEntity();
    member.setUserId(userId);
    member.setGroupId(groupId);
    memberDao.save(member);
  }

  public void deleteMember(int userId, int groupId) {
    MemberEntity member = new MemberEntity();
    member.setUserId(userId);
    member.setGroupId(groupId);
    memberDao.delete(member);
  }
}
