package net.hydromon.core.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AddSensorValue extends BaseDTO {
	
	private static final long serialVersionUID = 7320789281757270248L;

	@NotEmpty
	private String value;
	@NotNull
	private Long time;

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

}
