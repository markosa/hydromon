package net.hydromon.core.repository;

import net.hydromon.core.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUidAndApikey(String uid, String apikey);
	
	
}
