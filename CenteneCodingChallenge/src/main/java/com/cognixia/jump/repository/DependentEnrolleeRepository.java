package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Dependents;
import com.cognixia.jump.model.Enrollee;


/**
 * Repository for DependentEnrollee 
 * 
 */
@Repository
public interface DependentEnrolleeRepository extends JpaRepository<Dependents,Integer>{
	
	List<Dependents> findByEnrolleeId(int enrolleeId);
	Dependents save(Dependents d);


}
