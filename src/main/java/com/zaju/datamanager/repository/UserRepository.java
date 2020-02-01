package com.zaju.datamanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zaju.datamanager.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u where u.login = :login AND u.password = :password")
	public Optional<User> getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
	
	@Query("SELECT u FROM User u where u.login = :login")
	public Optional<User> getUserByLogin(@Param("login") String login);
}
