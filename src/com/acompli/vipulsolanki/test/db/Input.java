package com.acompli.vipulsolanki.test.db;

import com.turbomanage.storm.api.Entity;

@Entity
public class Input {

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	private long id;
	private String name;
	private String value;
	
	
		
}
