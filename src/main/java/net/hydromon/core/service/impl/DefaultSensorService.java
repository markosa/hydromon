package net.hydromon.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.User;
import net.hydromon.core.repository.SensorRepository;
import net.hydromon.core.service.SensorService;

@Service
public class DefaultSensorService implements SensorService {

	@Autowired
	private SensorRepository sensorRepository;

	public Sensor addSensor(Sensor sensor) {
		return sensorRepository.save(sensor);
	}
	
	public Sensor getSensor(Long id) {
		return sensorRepository.findOne(id);
	}

	public List<Sensor> getSensors() {
		return sensorRepository.findAll();
	}
	
	public List<Sensor> getSensors(User user) {
		return sensorRepository.findByUser(user);
	}
	
	
}
