package com.meal.controller;


import com.meal.entity.RoleEnum;
import com.meal.security.Secured;
import com.meal.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class ImageController {

  private ImageService imageService;

  @Autowired
  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value = "/report/image", method = RequestMethod.POST, consumes = "multipart/form-data")
  public ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile image) {
    Integer id = null;
    try {
      id = imageService.saveImage(image.getBytes());
    } catch (Exception e){

    }
    return new ResponseEntity<Integer>(id, HttpStatus.OK);
  }

  //Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value = "/report/image/{id}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE,
          MediaType.IMAGE_JPEG_VALUE})
  public ResponseEntity<byte[]> loadFile(@PathVariable(value = "id") int id) {
    byte[] image = imageService.findImage(id);
    return new ResponseEntity<byte[]>(image, HttpStatus.OK);
  }

}
