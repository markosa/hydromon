package net.hydromon.core.dto;

public class AddSensor extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319364603845584546L;
	
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
