package net.hydromon.core.service;

import java.sql.Timestamp;
import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;

public interface ValueService {

	SensorValue addValue(SensorValue value);

	List<SensorValue> addValues(List<SensorValue> values);

	List<SensorValue> getValues(Sensor sensor);

	List<SensorValue> getValues(Sensor sensor, Timestamp start, Timestamp end);

}
