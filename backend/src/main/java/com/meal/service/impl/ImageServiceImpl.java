package com.meal.service.impl;

import com.meal.dao.ImageRepository;
import com.meal.dao.ReportRepository;
import com.meal.entity.ImageEntity;
import com.meal.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

  private ImageRepository imageRepository;

  ImageServiceImpl(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public int saveImage(byte[] image){
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
