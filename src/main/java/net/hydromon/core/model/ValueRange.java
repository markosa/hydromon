package net.hydromon.core.model;

import javax.persistence.Embeddable;

@Embeddable
public class ValueRange {

	private Float min;
	private Float max;
	
	public Float getMin() {
		return min;
	}
	public void setMin(Float min) {
		this.min = min;
	}
	public Float getMax() {
		return max;
	}
	public void setMax(Float max) {
		this.max = max;
	}
	
}
