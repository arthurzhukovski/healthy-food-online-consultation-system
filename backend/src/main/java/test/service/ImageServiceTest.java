package test.service;

import com.meal.dao.ImageRepository;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.ImageServiceImpl;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ImageServiceTest {

  @Test
  public void saveImage_imageIsNull_exceptionThrown() {
    ImageRepository ImageRepositoryStub = mock(ImageRepository.class);

    ImageServiceImpl underTest
            = new ImageServiceImpl(ImageRepositoryStub);

    assertThatThrownBy(() -> underTest.saveImage(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void findImage_idIsLessThanZero_exceptionThrown() {
    ImageRepository ImageRepositoryStub = mock(ImageRepository.class);

    ImageServiceImpl underTest
            = new ImageServiceImpl(ImageRepositoryStub);

    assertThatThrownBy(() -> underTest.findImage(-1)).isInstanceOf(ServiceException.class);
  }


//  @Test
//  public void saveImage_imageIsNull_exceptionThrown() {
//    ImageRepository ImageRepositoryStub = mock(ImageRepository.class);
//    ImageService ImageServiceStub = mock(ImageService.class);
//
//    ImageServiceImpl underTest
//            = new ImageServiceImpl(ImageRepositoryStub);
//
//    assertThatThrownBy(() -> underTest.saveImage(null)).isInstanceOf(ServiceException.class);
//  }
//
}
