package net.hydromon.core.repository;

import java.sql.Timestamp;
import java.util.List;

import net.hydromon.core.model.Sensor;
import net.hydromon.core.model.SensorValue;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<SensorValue, Long> {

	List<SensorValue> findBySensorOrderByTimestampAsc(Sensor sensor);

	List<SensorValue> findBySensorAndTimestampBetweenOrderByTimestampAsc(Sensor sensor, Timestamp from, Timestamp to);

	@Query("select v from SensorValue v where v.sensor = :sensor and v.timestamp = (select max(timestamp) from SensorValue sv where sv.sensor = :sensor)")
	SensorValue getLatestSensorValue(@Param("sensor") Sensor sensor);
	
	@Query("select new java.lang.String(v.value) from SensorValue v where sensor = :sensor order by v.timestamp desc")
	List<String> findBySensorOrderByTimestampDesc(@Param("sensor") Sensor sensor, Pageable pageable);
	
	
}
