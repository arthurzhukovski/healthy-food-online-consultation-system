package com.meal.entity;

public class UserFullEntity {

  private UserEntity user;
  private UserDataEntity userData;

  public UserFullEntity(UserEntity user, UserDataEntity userData) {
    this.user = user;
    this.userData = userData;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public UserDataEntity getUserData() {
    return userData;
  }

  public void setUserData(UserDataEntity userData) {
    this.userData = userData;
  }

}
