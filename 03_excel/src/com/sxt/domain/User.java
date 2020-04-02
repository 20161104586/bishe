package com.sxt.domain;

import java.util.Date;

public class User {
	
	private Integer id;
	private String name;
	private String address;
	private Date hiredate;
	
	public User() {
	}
	
	public User(Integer id, String name, String address, Date hiredate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.hiredate = hiredate;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	

}
