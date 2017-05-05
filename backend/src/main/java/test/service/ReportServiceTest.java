package test.service;

import com.meal.dao.CommentRepository;
import com.meal.dao.ReportRepository;
import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;
import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ForbiddenException;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.ReportServiceImpl;
import com.meal.service.impl.UserServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

  private String THREE_HUNDREDS = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

  @Test
  public void createReport_reportIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.createReport(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void createReport_reportContentIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.createReport(new ReportEntity())).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createReport_reportContentIsEmpty_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    ReportEntity report = new ReportEntity();
    report.setContent("");
    assertThatThrownBy(() -> underTest.createReport(report)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createReport_reportContentIsMore_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    ReportEntity report = new ReportEntity();
    report.setContent(THREE_HUNDREDS);
    assertThatThrownBy(() -> underTest.createReport(report)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void updateReport_reportIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.updateReport(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void commentReport_reportIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.commentReport(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void commentReport_commentIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.commentReport(new ReportEntity())).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void commentReport_commentIsEmpty_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    ReportEntity report = new ReportEntity();
    CommentEntity comment = new CommentEntity();
    comment.setText("");
    report.setComment(comment);
    assertThatThrownBy(() -> underTest.commentReport(report)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void commentReport_commentIsMore_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    ReportEntity report = new ReportEntity();
    CommentEntity comment = new CommentEntity();
    comment.setText("dj");
    report.setComment(comment);
    assertThatThrownBy(() -> underTest.commentReport(report)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findByUsersId_idsIsNull_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.findByUsersId(null)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findByUsersId_idsIsEmpty_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.findByUsersId(new int[0])).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findOne_idsIsLessThanZero_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.findOne(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteReport_idsIsLessThanZero_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteReport(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getReportByUserId_idsIsLessThanZero_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.getReportsByUserId(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getReportsByGroupId_idsIsLessThanZero_exceptionThrown() {
    ReportRepository ReportRepositoryStub = mock(ReportRepository.class);
    CommentRepository CommentRepositoryStub = mock(CommentRepository.class);
    UserRepository UserRepositoryStub = mock(UserRepository.class);

    ReportServiceImpl underTest
            = new ReportServiceImpl(ReportRepositoryStub, CommentRepositoryStub, UserRepositoryStub);

    assertThatThrownBy(() -> underTest.getReportsByGroupId(-1)).isInstanceOf(ServiceException.class);
  }
}