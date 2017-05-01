package com.meal.service;

public interface ImageService {
  int saveImage(byte[] image);
  byte[] findImage(int id);
}
