package com.meal.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
  int saveImage(MultipartFile image);
  byte[] findImage(int id);
}
