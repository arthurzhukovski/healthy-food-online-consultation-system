package test.utils;

import com.meal.service.Exception.ServiceException;
import com.meal.utils.GetTokenServiceImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GetTokenServiceTest {

  @Test
  public void getToken_loginIsNull_exceptionThrown() {

    GetTokenServiceImpl underTest
            = new GetTokenServiceImpl();

    assertThatThrownBy(() -> underTest.getToken(null, "password")).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getToken_loginIsEmpty_exceptionThrown() {

    GetTokenServiceImpl underTest
            = new GetTokenServiceImpl();

    assertThatThrownBy(() -> underTest.getToken("", "password")).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getToken_passwordIsNull_exceptionThrown() {

    GetTokenServiceImpl underTest
            = new GetTokenServiceImpl();

    assertThatThrownBy(() -> underTest.getToken("login", null)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getToken_passwordIsEmpty_exceptionThrown() {

    GetTokenServiceImpl underTest
            = new GetTokenServiceImpl();

    assertThatThrownBy(() -> underTest.getToken("logib", "")).isInstanceOf(ServiceException.class);
  }

}