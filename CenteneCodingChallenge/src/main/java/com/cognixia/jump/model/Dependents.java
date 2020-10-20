package com.cognixia.jump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Model class to define details of DependentEnrollee 
 * 
 */
@Entity
public class Dependents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int enrolleeId;
	private int dependentId;
	
	
	public Dependents(int id, int enrolleeId, int dependentId) {
		super();
		this.id = id;
		this.enrolleeId = enrolleeId;
		this.dependentId = dependentId;
	}
	
	public Dependents() {
		this.id = -1;
		this.enrolleeId = -1;
		this.dependentId = -1;
	}


	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getEnrolleeId() {
		return enrolleeId;
	}


	public void setEnrolleeId(int enrolleeId) {
		this.enrolleeId = enrolleeId;
	}


	public int getDependentId() {
		return dependentId;
	}


	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}
	
	@Override
	public String toString() {
		return "Dependents [id=" + id + ", enrolleeId=" + enrolleeId + ", dependentId=" + dependentId + "]";
	}

	

}
