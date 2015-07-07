package net.hydromon.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Alarm {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String destinationEmail;
	private Integer threshold;
	private Float referenceValue;
	private ValueRange valueRange;
	
	@ManyToOne
	@JoinColumn(name="sensor_id")
	private Sensor sensor;
	
	private Boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Float getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(Float referenceValue) {
		this.referenceValue = referenceValue;
	}
	public ValueRange getValueRange() {
		return valueRange;
	}
	public void setValueRange(ValueRange valueRange) {
		this.valueRange = valueRange;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
		
}
