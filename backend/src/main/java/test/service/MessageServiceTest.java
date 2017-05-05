package test.service;

import com.meal.dao.MessageRepository;
import com.meal.entity.MessageEntity;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.service.impl.MessageServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

  @Test
  public void createMessage_messageIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    assertThatThrownBy(() -> underTest.createMessage(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void createMessage_messageTextIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    assertThatThrownBy(() -> underTest.createMessage(new MessageEntity())).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageTextIsEmpty_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("");
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageLengthIsMore_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("");
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageSenderIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("text");;
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageSenderIdIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("text");;
    message.setSender(new UserEntity());
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageReceiverIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("text");
    UserEntity user = new UserEntity();
    user.setId(0);
    message.setSender(user);
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createMessage_messageReceiverIdIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    MessageEntity message = new MessageEntity();
    message.setText("text");;
    UserEntity user = new UserEntity();
    user.setId(0);
    message.setSender(user);
    message.setReceiver(new UserEntity());
    assertThatThrownBy(() -> underTest.createMessage(message)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getMessagesBySenderId_messageReceiverIdIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    assertThatThrownBy(() -> underTest.getMessagesBySenderId(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getMessagesByReceiverId_messageReceiverIdIsNull_exceptionThrown() {
    MessageRepository MessageRepositoryStub = mock(MessageRepository.class);
    UserService UserServiceStub = mock(UserService.class);

    MessageServiceImpl underTest
            = new MessageServiceImpl(MessageRepositoryStub, UserServiceStub);

    assertThatThrownBy(() -> underTest.getMessagesByReceiverId(-1)).isInstanceOf(ServiceException.class);
  }
}