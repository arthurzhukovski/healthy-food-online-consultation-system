package com.meal.entity;

import javax.persistence.*;

@Entity
@Table(name = "image", schema = "mealdb")
public class ImageEntity {
  private int id;
  private int reportId;
  private byte[] image;

  @Id
  @GeneratedValue
  @Column(name = "id")
  public int getId(){
    return this.id;
  }

  public void setId(int id){
    this.id = id;
  }

  @Column(name = "reportId")
  public int getReportId(){
    return this.reportId;
  }

  public void setReportId(int reportId){
    this.reportId = reportId;
  }

  @Column(name = "image")
  @Lob
  public byte[] getImage(){
    return this.image;
  }

  public void setImage(byte[] image){
    this.image = image;
  }
//  private UserEntity coach;
//
//  ImageEntity(){}
//
//  public ImageEntity(String jsonString){
//    this.setText(jsonString);
//  }
//
//  @ManyToOne
//  @JoinColumn(name = "coach_id", nullable = false)
//  public UserEntity getCoach() {
//    return coach;
//  }
//
//  public void setCoach(UserEntity coach) {
//    this.coach = coach;
//  }
//
//  @Id
//  @Column(name = "id")
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  @Basic
//  @Column(name = "text", nullable = true, length = 10000)
//  public String getText() {
//    return text;
//  }
//
//  public void setText(String text) {
//    this.text = text;
//  }
//
//  @Basic
//  @Column(name = "created_at", nullable = true)
//  public Timestamp getCreatedAt() {
//    return createdAt;
//  }
//
//  public void setCreatedAt(Timestamp createdAt) {
//    this.createdAt = createdAt;
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//
//    ImageEntity that = (ImageEntity) o;
//
//    if (id != that.id) return false;
//    if (text != null ? !text.equals(that.text) : that.text != null) return false;
//    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
//
//    return true;
//  }
//
//  @Override
//  public int hashCode() {
//    int result = id;
//    result = 31 * result + (text != null ? text.hashCode() : 0);
//    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
//    return result;
//  }
}
