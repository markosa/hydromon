package net.hydromon.core.dto;

import java.util.List;


public class SensorDTO extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1533476250234929346L;

	private Long id;
	private String description;
	private String name; 
	private String location;
	private String type;
	private String chart;
	private String unit;
	private String latestValue;
	private String latestValueTime;
	private StatisticsDTO statistics;
	
	private List<String> latestValues;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLatestValue() {
		return latestValue;
	}
	public void setLatestValue(String latestValue) {
		this.latestValue = latestValue;
	}
	public List<String> getLatestValues() {
		return latestValues;
	}
	public void setLatestValues(List<String> latestValues) {
		this.latestValues = latestValues;
	}
	public String getLatestValueTime() {
		return latestValueTime;
	}
	public void setLatestValueTime(String latestValueTime) {
		this.latestValueTime = latestValueTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public StatisticsDTO getStatistics() {
		return statistics;
	}
	public void setStatistics(StatisticsDTO statistics) {
		this.statistics = statistics;
	}
	
	
}
