package com.cognixia.jump.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Enrollee;
/**
 * Service class for Enrollees 
 * 
 */
@Service 
public interface EnrolleeService {
	
	public void addEnrollee(Enrollee i);
	public List<Enrollee> findAll();
}
