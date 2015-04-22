package net.hydromon.core.dto.util;

import net.hydromon.core.dto.AddSensor;
import net.hydromon.core.model.Sensor;

import org.springframework.stereotype.Component;

@Component
public class DTOUtils {

	
	public Sensor convert(AddSensor addSensor) {
		Sensor sensor = new Sensor();
		sensor.setDescription(addSensor.getDescription());
		sensor.setName(addSensor.getName());
		return sensor;
	}
	
	
	
}
