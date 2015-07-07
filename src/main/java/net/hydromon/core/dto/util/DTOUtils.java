package net.hydromon.core.dto.util;

import net.hydromon.core.dto.AddSensor;
import net.hydromon.core.dto.SensorDTO;
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
	
	public SensorDTO convert(Sensor sensor) {
		SensorDTO sensorDTO = new SensorDTO();
		sensorDTO.setDescription(sensor.getDescription());
		sensorDTO.setName(sensor.getName());
		sensorDTO.setLocation(sensor.getLocation());
		sensorDTO.setType(sensor.getType());
		sensorDTO.setChart(sensor.getChart());
		sensorDTO.setId(sensor.getId());
		sensorDTO.setUnit(sensor.getUnit());
		
		return sensorDTO;
	}
	
	
}
