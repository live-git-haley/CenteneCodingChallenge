package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrollee implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int status;
	private String dob;
	private String phone;
	
	public Enrollee(int id, String name, int status, String dob, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.dob = dob;
		this.phone = phone;
	}
	
	public Enrollee() {
		this.id = -1;
		this.name = "NA";
		this.status = 0;
		this.dob = "01/01/1900";
		this.phone = "XXX-XXX-XXXX";
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Enrollee [id=" + id + ", name=" + name + ", status=" + status + ", dob=" + dob + ", phone=" + phone
				+ "]";
	}
	
	

}
