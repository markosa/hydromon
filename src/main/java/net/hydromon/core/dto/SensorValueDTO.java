package net.hydromon.core.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.hydromon.core.dto.util.TimeformatUtil;

public class SensorValueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2683234141104002745L;

	private Long sensorId;
	private String value;
	private Long time;
	private String formattedTime;
	
	public SensorValueDTO() {
	}

	public SensorValueDTO(Long sensorId, String value, Long time) {
		this.sensorId = sensorId;
		this.value = value;
		this.time = time;
		this.formattedTime=TimeformatUtil.formatDate(time);
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getFormattedTime() {
		return formattedTime;
	}

	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}

}
