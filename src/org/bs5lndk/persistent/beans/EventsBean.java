package org.bs5lndk.persistent.beans;

public class EventsBean {
	private int id;
	private String data;
	
	public String toString(){
		return id + " " + data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}