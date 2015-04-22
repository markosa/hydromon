package net.hydromon.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hydromon.core.model.User;
import net.hydromon.core.repository.UserRepository;
import net.hydromon.core.service.UserService;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser() {
		
		return null;
	}

	public void deleteUser(User user) {

	}

	public User getUser(String uid) {
		return userRepository.getOne(uid);
	}

}
