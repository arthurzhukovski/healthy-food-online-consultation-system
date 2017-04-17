package com.meal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user", schema = "mealdb")
public class UserEntity {
  private int id;
  private String name;
  private String surname;
  private String email;
  private byte confirmed;
  private Timestamp registeredAt;
  private byte stage;
  private String role;
  private String login;
  @JsonIgnore
  private String password;

  @Id
  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name", nullable = false, length = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "surname", nullable = false, length = 255)
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Basic
  @Column(name = "email", nullable = false, length = 255)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "confirmed", nullable = false)
  public byte getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(byte confirmed) {
    this.confirmed = confirmed;
  }

  @Basic
  @Column(name = "registered_at", nullable = false)
  public Timestamp getRegisteredAt() {
    return registeredAt;
  }

  public void setRegisteredAt(Timestamp registeredAt) {
    this.registeredAt = registeredAt;
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
  @Column(name = "role", nullable = false)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Basic
  @Column(name = "login", nullable = false, length = 255)
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserEntity that = (UserEntity) o;

    if (id != that.id) return false;
    if (confirmed != that.confirmed) return false;
    if (stage != that.stage) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (registeredAt != null ? !registeredAt.equals(that.registeredAt) : that.registeredAt != null) return false;
    if (role != null ? !role.equals(that.role) : that.role != null) return false;
    if (login != null ? !login.equals(that.login) : that.login != null) return false;
    if (password != null ? !password.equals(that.password) : that.password != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (int) confirmed;
    result = 31 * result + (registeredAt != null ? registeredAt.hashCode() : 0);
    result = 31 * result + (int) stage;
    result = 31 * result + (role != null ? role.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
