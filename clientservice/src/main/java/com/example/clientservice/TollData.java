package com.example.clientservice;

public class TollData {

	private String recordId;
	private String licensePlate;
	private String timestamp;
	
	public TollData() {}
	
	public TollData(String recordId, String licensePlate, String timestamp) {
		super();
		this.recordId = recordId;
		this.licensePlate = licensePlate;
		this.timestamp = timestamp;
	}
	
	
	
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
