package com.meal.entity;

import com.meal.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "report", schema = "mealdb")
public class ReportEntity {
  private int id;
  private Timestamp createdAt;
  private Grade grade;
  private String content;

  private CommentEntity comment;
  private UserEntity user;
  private int imageId;
//  private byte[] image;
//
//  @Basic(fetch = FetchType.LAZY)
//  @Lob
//  @Column(name = "image")
//  public byte[] getImage(){
//    return this.image;
//  }
//
//  public void setImage(byte[] image){
//    this.image = image;
//  }


  @Column(name = "image_id")
  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "user_id")
  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "comment_id")
  public CommentEntity getComment() {
    return comment;
  }

  public void setComment(CommentEntity comment) {
    this.comment = comment;
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "created_at", nullable = false)
  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  @Basic
  @Column(name = "grade", columnDefinition = "ENUM default 'EMPTY'")
  @Enumerated(EnumType.STRING)
  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  @Basic
  @Column(name = "content", nullable = true, length = 45)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ReportEntity that = (ReportEntity) o;

    if (id != that.id) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
    if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
    if (content != null ? !content.equals(that.content) : that.content != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (grade != null ? grade.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    return result;
  }
}
