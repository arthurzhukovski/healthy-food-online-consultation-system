package com.meal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "report", schema = "mealdb")
public class ReportEntity {
  private int id;
  private int userId;
  private Timestamp createdAt;
  private Byte grade;
  private String content;

  @Id
  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "user_id", nullable = false)
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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
  @Column(name = "grade", nullable = true)
  public Byte getGrade() {
    return grade;
  }

  public void setGrade(Byte grade) {
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
    if (userId != that.userId) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
    if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
    if (content != null ? !content.equals(that.content) : that.content != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + userId;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (grade != null ? grade.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    return result;
  }
}
