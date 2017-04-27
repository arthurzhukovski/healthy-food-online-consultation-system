package com.meal.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "mealdb")
public class MessageEntity {
  private int id;
  private String text;
  private int receiverId;
  private Timestamp createdAt;

  private UserEntity sender;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  public UserEntity getSender() {
    return sender;
  }

  public void setSender(UserEntity sender) {
    this.sender = sender;
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
  @Column(name = "text", nullable = false, length = 10000)
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Basic
  @Column(name = "receiver_id", nullable = false)
  public int getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(int receiverId) {
    this.receiverId = receiverId;
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

    MessageEntity that = (MessageEntity) o;

    if (id != that.id) return false;
    if (receiverId != that.receiverId) return false;
    if (text != null ? !text.equals(that.text) : that.text != null) return false;
    if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + receiverId;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
