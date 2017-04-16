package com.meal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "group", schema = "mealdb")
public class GroupEntity {
  private int id;
  private int coachId;
  private byte stage;
  private byte active;
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
  @Column(name = "coach_id", nullable = false)
  public int getCoachId() {
    return coachId;
  }

  public void setCoachId(int coachId) {
    this.coachId = coachId;
  }

  @Basic
  @Column(name = "stage", nullable = false)
  public byte getStage() {
    return stage;
  }

  public void setStage(byte stage) {
    this.stage = stage;
  }

  @Basic
  @Column(name = "active", nullable = false)
  public byte getActive() {
    return active;
  }

  public void setActive(byte active) {
    this.active = active;
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

    GroupEntity that = (GroupEntity) o;

    if (id != that.id) return false;
    if (coachId != that.coachId) return false;
    if (stage != that.stage) return false;
    if (active != that.active) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + coachId;
    result = 31 * result + (int) stage;
    result = 31 * result + (int) active;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
