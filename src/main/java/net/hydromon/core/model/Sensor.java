package net.hydromon.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Sensor {

	@Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable=true,length=1024)
	private String description;

	@Column(nullable=true,length=1024)
	private String name;
	
	private String location;
	private String type;
	private String chart;
	
	private String unit;
	
	@OneToMany(mappedBy="sensor")
	private List<SensorValue> sensorValues;
	
	@OneToMany(mappedBy="sensor")
	private List<Alarm> alarms;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SensorValue> getSensorValues() {
		if (sensorValues == null)
			sensorValues = new ArrayList<SensorValue>();
		
		return sensorValues;
	}

	public void setSensorValues(List<SensorValue> sensorValues) {
		this.sensorValues = sensorValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
