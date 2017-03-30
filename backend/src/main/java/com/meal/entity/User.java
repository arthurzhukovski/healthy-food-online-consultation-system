package com.meal.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  private String login;

  @NotNull String password;

  @NotNull
  private String name;

  @NotNull
  private String surname;

  private Boolean confirmed;

  @NotNull
  private Date registred_at;

  @NotNull
  private int stage;

  private enum role { ADMIN, USER, COACH };

  @NotNull
  private String email;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Boolean getConfirmed() {
    return confirmed;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
  }

  public Date getRegistred_at() {
    return registred_at;
  }

  public void setRegistred_at(Date registred_at) {
    this.registred_at = registred_at;
  }

  public int getStage() {
    return stage;
  }

  public void setStage(int stage) {
    this.stage = stage;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}