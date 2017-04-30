package com.meal.service.impl;

import com.meal.dao.ImageRepository;
import com.meal.entity.ImageEntity;
import com.meal.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

  private ImageRepository imageRepository;

  ImageServiceImpl(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public void saveImage(int id, byte[] image){
    ImageEntity entity = new ImageEntity();
    entity.setImage(image);
    entity.setReportId(id);
    imageRepository.save(entity);
  }

  public byte[] findImage(int id){
    ImageEntity entity = imageRepository.findByReportId(id);
    return entity.getImage();
  }

}
