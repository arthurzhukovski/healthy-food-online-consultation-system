package com.meal.entity;

public enum RoleEnum {
  ADMIN(0),
  COACH(1),
  USER(2);

  private int levelId;

  RoleEnum(int levelId){
    this.levelId = levelId;
  }

  public int getValue(){
    return levelId;
  }

}
