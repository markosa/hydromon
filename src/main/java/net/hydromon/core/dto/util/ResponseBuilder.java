package net.hydromon.core.dto.util;

import net.hydromon.core.dto.BaseResponseDTO;

public class ResponseBuilder<T extends BaseResponseDTO> {

	
	public T setOK(T t) {
		t.setStatus("OK");
		return t;
	}
	
	public T setFAIL(T t) {
		t.setStatus("FAIL");
		return t;
	}
	
	public T setDENIED(T t) {
		t.setStatus("DENIED");
		return t;
	}
	
	
	
}
