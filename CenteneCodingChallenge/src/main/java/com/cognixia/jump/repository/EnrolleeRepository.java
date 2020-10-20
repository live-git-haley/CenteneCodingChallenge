package com.cognixia.jump.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Enrollee;


/**
 * Repository for Enrollees 
 * 
 */
@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee,Integer>{
	
	List<Enrollee> findAll();

}


