package net.hydromon.core.repository;

import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
	List<Sensor> findByUser(User user);
	
}
