package test.service;

import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.service.impl.AuthServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

  @Test
  public void login_loginIsNull_exceptionThrown() {
    UserService UserServiceStub = mock(UserService.class);

    AuthServiceImpl underTest
            = new AuthServiceImpl(UserServiceStub);

    assertThatThrownBy(() -> underTest.login(null, "12345678")).isInstanceOf(ServiceException.class);
  }

  @Test
  public void login_loginIsEmpty_exceptionThrown() {
    UserService UserServiceStub = mock(UserService.class);

    AuthServiceImpl underTest
            = new AuthServiceImpl(UserServiceStub);

    assertThatThrownBy(() -> underTest.login("", "12345678")).isInstanceOf(ServiceException.class);
  }

  @Test
  public void login_passwordIsNull_exceptionThrown() {
    UserService UserServiceStub = mock(UserService.class);

    AuthServiceImpl underTest
            = new AuthServiceImpl(UserServiceStub);

    assertThatThrownBy(() -> underTest.login("login", null)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void login_passwordIsEmpty_exceptionThrown() {
    UserService UserServiceStub = mock(UserService.class);

    AuthServiceImpl underTest
            = new AuthServiceImpl(UserServiceStub);

    assertThatThrownBy(() -> underTest.login("login", "")).isInstanceOf(ServiceException.class);
  }
}