package com.meal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "mealdb")
public class CommentEntity {
  private int id;
  private String text;
  private Timestamp createdAt;

  private UserEntity coach;

  @ManyToOne
  @JoinColumn(name = "coach_id", nullable = false)
  public UserEntity getCoach() {
    return coach;
  }

  public void setCoach(UserEntity coach) {
    this.coach = coach;
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
  @Column(name = "text", nullable = true, length = 10000)
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Basic
  @Column(name = "created_at", nullable = true)
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

    CommentEntity that = (CommentEntity) o;

    if (id != that.id) return false;
    if (text != null ? !text.equals(that.text) : that.text != null) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
