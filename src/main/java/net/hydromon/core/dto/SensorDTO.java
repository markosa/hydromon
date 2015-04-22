package net.hydromon.core.dto;


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
	
	
}
