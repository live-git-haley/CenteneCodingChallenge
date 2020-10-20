package com.cognixia.jump.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;


/**
 * Model class to define details of Dependent
 * 
 */
@Entity
public class Dependent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Dependent ID ")
	private int id;
	@ApiModelProperty(value = "Full Name of Dependent")
	private String name;
	@ApiModelProperty(value = "Dependent Date of Birth")
	private String dob;
	
	public Dependent(int id, String name, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	public Dependent() {
		this.id = -1;
		this.name = "NA";
		this.dob = "01/01/1900";
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

	

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Dependent [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
}
