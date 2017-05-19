package com.meal.entity;

import com.meal.service.GroupService;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "groups", schema = "mealdb")
public class GroupEntity {
  private int id;
  private byte stage;
  private Boolean active;
  private Timestamp createdAt;

  private UserEntity coach;

///////////////////////

  @ManyToOne
  @JoinColumn(name = "coach_id")
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
  @Column(name = "stage", nullable = false)
  public byte getStage() {
    return stage;
  }

  public void setStage(byte stage) {
    this.stage = stage;
  }

  @Basic
  @Column(name = "active", nullable = false)
  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
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
    if (stage != that.stage) return false;
    if (active != that.active) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (int) stage;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
