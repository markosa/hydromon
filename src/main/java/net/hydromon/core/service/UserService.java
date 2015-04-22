package net.hydromon.core.service;

import net.hydromon.core.model.User;

public interface UserService {
	
	User addUser();
	void deleteUser(User user);
	User getUser(String uid);
	
}
