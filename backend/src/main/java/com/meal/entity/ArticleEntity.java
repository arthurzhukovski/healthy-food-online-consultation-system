package com.meal.entity;

import com.meal.service.ArticleService;
import com.meal.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "article", schema = "mealdb")
public class ArticleEntity {
  private int id;
  private String title;
  private String content;
  private Timestamp createdAt;
  private UserEntity coach;

  @Autowired
  ArticleService service;

  @Transient
  public String getCount() {
    return String.valueOf(service.getCount(coach.getId()));
  }

  @Transient
  public String getCountPerMonth() {
    return String.valueOf(service.getCountPerMonth(coach.getId()));
  }
  @Transient
  public String getLastPub() {
    return String.valueOf(service.getLastPub(coach.getId()));
  }

  @ManyToOne
  @JoinColumn(name = "coach_id")
  public UserEntity getCoach() {
    return coach;
  }

  public void setCoach(UserEntity coach) {
    this.coach = coach;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "title", nullable = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "content", nullable = false, columnDefinition = "longtext")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Basic
  @Column(name = "created_at", nullable = false)
  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ArticleEntity that = (ArticleEntity) o;

    if (id != that.id) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (content != null ? !content.equals(that.content) : that.content != null) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
