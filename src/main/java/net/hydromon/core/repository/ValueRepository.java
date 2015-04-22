package net.hydromon.core.repository;

import java.sql.Timestamp;
import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<SensorValue, Long> {

	List<SensorValue> findBySensorOrderByTimestampAsc(Sensor sensor);

	List<SensorValue> findBySensorAndTimestampBetweenOrderByTimestampAsc(Sensor sensor, Timestamp from, Timestamp to);
}
