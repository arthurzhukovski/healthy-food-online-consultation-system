package com.meal.entity;

import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_data", schema = "mealdb")
public class UserDataEntity {
  private Integer id;
  private Integer weight;
  private Integer height;
  private Gender gender;
  private Date birthdate;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "weight", nullable = true)
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  @Basic
  @Column(name = "height", nullable = true)
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  @Basic
  @Column(name = "gender", nullable = true, columnDefinition = "enum")
  @Enumerated(EnumType.STRING)
  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Basic
  @Column(name = "birthdate", nullable = true)
  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserDataEntity that = (UserDataEntity) o;

    if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
    if (height != null ? !height.equals(that.height) : that.height != null) return false;
    if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
    if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (weight != null ? weight.hashCode() : 0);
    result = 31 * result + (height != null ? height.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
    return result;
  }
}
