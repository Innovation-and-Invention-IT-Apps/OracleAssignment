package org.bs5lndk.persistent.beans;

public class HaadMpatientVerifyCode {
	
	private int id;
	private String mobile_number;
	private String unique_code;
	private String imei_number;
	private String added_on;
	private String modified_on;
	private String status;
	
	public String toString(){
		return id + " " + mobile_number + " " + unique_code + " " + imei_number + " " + added_on + " " + modified_on + " " + status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getUnique_code() {
		return unique_code;
	}
	public void setUnique_code(String unique_code) {
		this.unique_code = unique_code;
	}
	public String getImei_number() {
		return imei_number;
	}
	public void setImei_number(String imei_number) {
		this.imei_number = imei_number;
	}
	public String getAdded_on() {
		return added_on;
	}
	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
