package net.hydromon.core.dto;

public class AddSensor extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319364603845584546L;
	
	private String description;
	private String name; 
	private String location;
	private String type;
	private String chart;
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

	
	
	
}
