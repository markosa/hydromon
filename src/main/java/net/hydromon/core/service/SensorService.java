package net.hydromon.core.service;

import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.User;

public interface SensorService {
	
	Sensor addSensor(Sensor sensor);
	Sensor getSensor(Long id);
	List<Sensor> getSensors();
	List<Sensor> getSensors(User user);	

}
