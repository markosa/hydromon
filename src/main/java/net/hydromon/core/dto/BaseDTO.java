package net.hydromon.core.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6380417187525545760L;

	@NotEmpty
	private String uid;
	@NotEmpty
	private String apikey;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	
	
}
