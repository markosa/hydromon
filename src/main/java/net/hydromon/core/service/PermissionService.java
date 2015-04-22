package net.hydromon.core.service;


public interface PermissionService {
	
	boolean canProceed(String uid, String apikey);
	boolean canProceedAdmin(String uid, String apikey);
	boolean isOwner(String uid, Long sensorId);
	
}
