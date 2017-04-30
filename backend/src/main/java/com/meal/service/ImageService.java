package com.meal.service;

import org.springframework.stereotype.Service;

public interface ImageService {
  int saveImage(byte[] image);
  byte[] findImage(int id);
}
