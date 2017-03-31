package com.meal.dao;

import com.meal.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
  //List<User> getAllUsers();
  //TODO: read spec
  //@Query("SELECT u FROM User u WHERE u.login = :login")
  User findByLogin(@Param("login")String login);

}
