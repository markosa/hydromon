package net.hydromon.core.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;
import net.hydromon.core.repository.ValueRepository;
import net.hydromon.core.service.ValueService;

@Service
public class DefaultValueService implements ValueService {

	@Autowired
	private ValueRepository valueRepository;
	
	public SensorValue addValue(SensorValue value) {
		return valueRepository.save(value);
	}

	public List<SensorValue> addValues(List<SensorValue> values) {
		return valueRepository.save(values);
	}

	public List<SensorValue> getValues(Sensor sensor) {
		return valueRepository.findBySensorOrderByTimestampAsc(sensor);
	}

	public List<SensorValue> getValues(Sensor sensor, Timestamp start, Timestamp end) {
		return valueRepository.findBySensorAndTimestampBetweenOrderByTimestampAsc(sensor, start, end);
	}


}
