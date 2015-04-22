package net.hydromon.core.dto;

public class GetSensorValuesRange extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1227763219934446506L;
	
		
	private Long from;
	private Long to;
	
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public Long getTo() {
		return to;
	}
	public void setTo(Long to) {
		this.to = to;
	}
	
	
	
	
}
