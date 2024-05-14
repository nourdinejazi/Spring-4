package com.nourdine.vetements.service;

import com.nourdine.vetements.entities.Role;
import com.nourdine.vetements.entities.User;

public interface UserService {
	void deleteAllusers();

	void deleteAllRoles();

	User saveUser(User user);

	User findUserByUsername(String username);

	Role addRole(Role role);

	User addRoleToUser(String username, String rolename);
}