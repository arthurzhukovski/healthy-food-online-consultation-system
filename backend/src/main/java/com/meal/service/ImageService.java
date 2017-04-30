package com.meal.service;

import org.springframework.stereotype.Service;

public interface ImageService {
  void saveImage(int id, byte[] image);
  byte[] findImage(int id);
}
