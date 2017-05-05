package test.service;

import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

  @Test
  public void findByLogin_loginIsNull_exceptionThrown(){
    UserRepository userRepositoryStub = mock(UserRepository.class);
    UserDataRepository userDataRepositoryStub = mock(UserDataRepository.class);

    UserServiceImpl underTest
            = new UserServiceImpl(userRepositoryStub, userDataRepositoryStub);

    assertThatThrownBy(() -> underTest.findByLogin(null)).isInstanceOf(IllegalArgumentException.class);
  }
}
