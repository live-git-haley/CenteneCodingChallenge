package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Dependent;
import com.cognixia.jump.model.Dependents;
import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.repository.DependentEnrolleeRepository;
import com.cognixia.jump.repository.DependentRepository;

@RestController
@RequestMapping("/api")
public class DependentController {
	
		
		@Autowired
		DependentRepository service;
		
		@Autowired
		DependentEnrolleeRepository service2;

		@GetMapping("/dependents")
		public List<Dependent> getAllDependents() {

			return service.findAll();

		}
		
		
		@GetMapping("/dependentId/{id}")
		public Dependent findDependentById(@PathVariable int id) {
			Optional<Dependent> dependent = service.findById(id);
			if (dependent.isPresent()) {
				return dependent.get();

			}
			
			return new Dependent();
	

		}

		@GetMapping("/dependents/{enrolleeId}")
		public List<Dependent> getDependentsByEnrolleeId(@PathVariable int enrolleeId) {

			List<Dependents> dependent = service2.findByEnrolleeId(enrolleeId);
			List<Dependent> list = new ArrayList();
			
			for(Dependents i: dependent) {
				
				list.add(findDependentById(i.getDependentId()));
			}
			
			
			return list;


		}
		
		@PostMapping("/add/dependent/{enrolleeId}")
		public void addDependent(@RequestBody Dependent newDependent, @PathVariable int enrolleeId) {

			newDependent.setId(-1);
			Dependent added = service.save(newDependent);
			Dependents d = new Dependents();
			d.setDependentId(added.getId());
			d.setEnrolleeId(enrolleeId);
			
			Dependents newDependents = service2.save(d);
			System.out.println("Added into Dependents table. ");
			System.out.println(newDependents);
			
			
			
			

		}
		

		@DeleteMapping("/delete/dependent/{dependentId}/{enrolleeId}")
		public ResponseEntity<String> deleteDependentFromEnrollee(@PathVariable int enrolleeId, @PathVariable int dependentId) {
			List<Dependents> dependents = service2.findAll();
			boolean deleted = false;
			for(Dependents i: dependents) {
				if(i.getDependentId() == dependentId && i.getEnrolleeId()== enrolleeId) {
					service2.deleteById(i.getId());
					deleted = true;
				
				}
			}
			if(deleted) {
			
				return ResponseEntity.status(200).body("Deleted Dependent with id: " + dependentId);

			} else {
				return ResponseEntity.status(400)
						.header("Dependent id", dependentId + "")
						.body("Record not found: Dependent with ID = " + dependentId + " , associated with Enrollee with ID = " + enrolleeId + " not found.");
			}

		}
		
		@PatchMapping("/update/dependent")
		public ResponseEntity <String> updateDependent(@RequestBody Dependent dependent) {
			System.out.println();
			Optional<Dependent> found = service.findById(dependent.getId());
			if(found.isPresent()) {
				service.save(dependent);
				return ResponseEntity.status(200).body("Updated the dependent");

			}
			 else {
					return ResponseEntity.status(400)
							.body("Dependent with ID " + dependent.getId() + " was not found in the database." );
				}
			
				

		

		}

		
		

		
}
