package test.service;

import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ForbiddenException;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.UserServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UserServiceTest {

  @Test
  public void hasPermission_userIdNotEqual_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);
    UserEntity currentUser = new UserEntity();
    currentUser.setRole(RoleEnum.ADMIN);
    currentUser.setId(0);
    UserEntity user = new UserEntity();
    user.setId(1);
    assertThatThrownBy(() -> underTest.hasPermission(user, currentUser, RoleEnum.ADMIN)).isInstanceOf(ForbiddenException.class);
  }

  @Test
  public void hasPermission_userIsNullEqual_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);
    assertThatThrownBy(() -> underTest.hasPermission(null, new UserEntity(), RoleEnum.USER)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void hasPermission_idNotEqual_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);
    UserEntity currentUser = new UserEntity();
    currentUser.setRole(RoleEnum.USER);
    currentUser.setId(1);
    assertThatThrownBy(() -> underTest.hasPermission(0, currentUser, RoleEnum.USER)).isInstanceOf(ForbiddenException.class);
  }

  @Test
  public void hasPermission_rolesNotEqual_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);
    UserEntity currentUser = new UserEntity();
    currentUser.setRole(RoleEnum.COACH);
    currentUser.setId(0);
    assertThatThrownBy(() -> underTest.hasPermission(0, currentUser, RoleEnum.USER)).isInstanceOf(ForbiddenException.class);
  }

  @Test
  public void hasPermission_checkRoleIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.hasPermission(0, new UserEntity(), null)).isInstanceOf(ForbiddenException.class);
  }

  @Test
  public void hasPermission_currentUserIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.hasPermission(0, null, RoleEnum.USER)).isInstanceOf(ForbiddenException.class);
  }

  @Test
  public void findByLogin_loginIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.findByLogin(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void findByLogin_loginIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.findByLogin("")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void createUser_userIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.createUser(null)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userEmailIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userEmailIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userNameIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userNameIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userSurnameIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userSurnameIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userLoginIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userLoginIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("12345678");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userPasswordIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userPasswordIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createUser_userPasswordIsLess_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity user = new UserEntity();
    user.setId(0);
    user.setPassword("1234567");
    user.setStage((byte)0);
    user.setRole(RoleEnum.ADMIN);
    user.setGroupId(0);
    user.setName("name");
    user.setSurname("surname");
    user.setEmail("aa@aa.ru");
    user.setLogin("login");
    assertThatThrownBy(() -> underTest.createUser(user)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void updateUser_userIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.updateUser(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void updateUserFields_userIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.updateUserFields(null, new UserEntity())).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void updateUserFields_newUserIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.updateUserFields(new UserEntity(), null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void updateUserFields_newUserPasswordIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity newUser = new UserEntity();
    newUser.setPassword("");
    assertThatThrownBy(() -> underTest.updateUserFields(new UserEntity(), newUser)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void updateUserFields_newUserPasswordIsLess_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity newUser = new UserEntity();
    newUser.setPassword("1234567");
    assertThatThrownBy(() -> underTest.updateUserFields(new UserEntity(), newUser)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void updateUserFields_newUserNameIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity newUser = new UserEntity();
    newUser.setName("");
    assertThatThrownBy(() -> underTest.updateUserFields(new UserEntity(), newUser)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void updateUserFields_newUserSurnameIsEmpty_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    UserEntity newUser = new UserEntity();
    newUser.setSurname("");
    assertThatThrownBy(() -> underTest.updateUserFields(new UserEntity(), newUser)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void hasPermission_idIsLessThanZero_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.hasPermission(-1, new UserEntity(), RoleEnum.USER)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findOne_idIsLessThanZero_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.findOne(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteUser_idIsLessThanZero_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteUser(-1)).isInstanceOf(ServiceException.class);
  }

}
