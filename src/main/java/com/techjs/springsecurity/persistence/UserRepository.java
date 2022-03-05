package com.techjs.springsecurity.persistence;

import com.techjs.springsecurity.model.User;

public interface UserRepository {

	Iterable<User> findAll();

	User save(User user);

	User findUser(Long id);

	void deleteUser(Long id);

}