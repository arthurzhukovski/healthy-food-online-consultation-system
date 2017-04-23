package com.meal.service.impl;

import com.meal.dao.GroupRepository;
import com.meal.dao.MemberRepository;
import com.meal.entity.GroupEntity;
import com.meal.entity.MemberEntity;
import com.meal.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
  
  private GroupRepository groupRepository;
  private MemberRepository memberRepository;

  public GroupServiceImpl(GroupRepository groupRepository, MemberRepository memberRepository) {
    this.groupRepository = groupRepository;
    this.memberRepository = memberRepository;
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

  public Iterable<GroupEntity> getCoachGroups(int coachId) {
    return null;
  }

  public void createMember(int userId, int groupId) {
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setUserId(userId);
    memberEntity.setGroupId(groupId);
    memberRepository.save(memberEntity);
  }

  public void deleteMember(int userId, int groupId) {
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setUserId(userId);
    memberEntity.setGroupId(groupId);
    memberRepository.delete(memberEntity);
  }
}
