package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Dependent;
import com.cognixia.jump.model.Enrollee;


/**
 * Repository for Dependents
 * 
 */
@Repository
public interface DependentRepository extends JpaRepository<Dependent,Integer> {

	
	List<Dependent> findAll();

	Dependent save(Dependent newDependent);
	
}





