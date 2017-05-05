package test.utils;

import org.assertj.core.api.AssertProvider;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelpUtils {

  @Test
  public void getToken_stringIsNull_exceptionThrown() {
    assertThat((AssertProvider<Boolean>) () -> com.meal.utils.HelpUtils.isNullOrEmpty(null) == false);
  }


  @Test
  public void getToken_stringIsEmpty_exceptionThrown() {
    assertThat((AssertProvider<Boolean>) () -> com.meal.utils.HelpUtils.isNullOrEmpty("") == false);
  }
}