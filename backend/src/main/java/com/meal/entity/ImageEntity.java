package com.meal.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "image", schema = "mealdb")
public class ImageEntity {
  private int id;
  private byte[] image;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "image", nullable = false)
  @Lob
  public byte[] getImage() {
    return this.image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ImageEntity that = (ImageEntity) o;

    if (id != that.id) return false;
    if (!Arrays.equals(image, that.image)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + Arrays.hashCode(image);
    return result;
  }
}