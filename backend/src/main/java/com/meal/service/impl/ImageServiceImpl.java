package com.meal.service.impl;

import com.meal.dao.ImageRepository;
import com.meal.dao.ReportRepository;
import com.meal.entity.ImageEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

  private ImageRepository imageRepository;
  private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

  ImageServiceImpl(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public int saveImage(MultipartFile file) throws ServiceException{
    if(!contentTypes.contains(file.getContentType())){
      throw new ServiceException("invalid file type");
    }
    byte[] image;
    try {
      image = file.getBytes();
    } catch(Exception e){
      throw new ServiceException(e);
    }
    if (image.length == 0){
      throw new ServiceException("invalid image object");
    }
    ImageEntity entity = new ImageEntity();
    entity.setImage(image);
    ImageEntity imageEntity = imageRepository.save(entity);
    return imageEntity.getId();
  }

  public byte[] findImage(int id){
    ImageEntity entity = imageRepository.findOne(id);
    return entity.getImage();
  }

}
