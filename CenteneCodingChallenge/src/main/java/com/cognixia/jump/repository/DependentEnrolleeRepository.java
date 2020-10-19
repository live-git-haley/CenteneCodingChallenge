package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Dependents;
import com.cognixia.jump.model.Enrollee;


public interface DependentEnrolleeRepository extends JpaRepository<Dependents,Integer>{
	
	List<Dependents> findByEnrolleeId(int enrolleeId);
	Dependents save(Dependents d);


}
