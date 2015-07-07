package net.hydromon.core.service.impl;

import java.sql.Timestamp;
import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;
import net.hydromon.core.repository.ValueRepository;
import net.hydromon.core.service.ValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

	public SensorValue getLatestSensorValue(Sensor sensor) {
		SensorValue sv = valueRepository.getLatestSensorValue(sensor);
		return sv;
	}

	public List<String> getLatestSensorValues(Sensor sensor, int n) {
		return valueRepository.findBySensorOrderByTimestampDesc(sensor,new PageRequest(0, n));
	}
}
