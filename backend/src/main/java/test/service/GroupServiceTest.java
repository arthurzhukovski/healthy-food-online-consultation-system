package test.service;

import com.meal.dao.GroupRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.GroupServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

  @Test
  public void createGroup_groupIsNull_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.createGroup(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void createGroup_groupActiveIsNull_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.createGroup(new GroupEntity())).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createGroup_groupCoachIsNull_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    GroupEntity group = new GroupEntity();
    group.setActive(true);
    assertThatThrownBy(() -> underTest.createGroup(group)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createGroup_groupCoachIdIsNull_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    GroupEntity group = new GroupEntity();
    group.setActive(true);
    group.setCoach(new UserEntity());
    assertThatThrownBy(() -> underTest.createGroup(group)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findOne_groupCoachIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.findOne(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteGroup_groupCoachIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteGroup(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findGroupsByCoachId_coachIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.findGroupsByCoachId(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void addUserToGroup_userIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.addUserToGroup(-1, 0)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void addUserToGroup_groupIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.addUserToGroup(0, -1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteUserFromGroup_userIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteUserFromGroup(-1, 0)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteUserFromGroup_groupIdIsLessThanZero_exceptionThrown() {
    GroupRepository GroupRepositoryStub = mock(GroupRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    GroupServiceImpl underTest
            = new GroupServiceImpl(GroupRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteUserFromGroup(0, -1)).isInstanceOf(ServiceException.class);
  }


}