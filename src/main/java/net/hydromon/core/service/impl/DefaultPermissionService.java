package net.hydromon.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.User;
import net.hydromon.core.repository.SensorRepository;
import net.hydromon.core.repository.UserRepository;
import net.hydromon.core.service.PermissionService;

@Service
public class DefaultPermissionService implements PermissionService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SensorRepository sensorRepository;
	
	
	public boolean canProceed(String uid, String apikey) {
		User user = userRepository.findByUidAndApikey(uid, apikey);
		return user != null;
	}

	public boolean canProceedAdmin(String uid, String apikey) {
		User user = userRepository.findByUidAndApikey(uid, apikey);
		if (user != null)
			return user.isAdmin();
		
		return false;
	}
	
	public boolean isOwner(String uid, Long sensorId) {
		Sensor sensor = sensorRepository.findOne(sensorId);
		if (sensor != null && sensor.getUser() != null)
			return uid.equals(sensor.getUser().getUid());
		
		
		return false;
	}
	
}
