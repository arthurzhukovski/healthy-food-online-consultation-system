package com.meal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member", schema = "mealdb")
public class Member {
  private int id;
  private int userId;
  private int groupId;
  private Timestamp createdAt;

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
  @Column(name = "group_id", nullable = false)
  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
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

    Member that = (Member) o;

    if (id != that.id) return false;
    if (userId != that.userId) return false;
    if (groupId != that.groupId) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + userId;
    result = 31 * result + groupId;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
