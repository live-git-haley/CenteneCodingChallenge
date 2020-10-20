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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller for API calls to access dependents in the db 
 * 
 */

@RestController
@RequestMapping("/api")
@Api(value = "value block HERE", description = "Controller to access information on dependents")
public class DependentController {
	
		
		@Autowired
		DependentRepository service;
		
		@Autowired
		DependentEnrolleeRepository service2;
		
		/**
		 * API mapping to retrieve all dependents in the database
		 * @return List of dependents
		 */
		@GetMapping("/dependents")
		@ApiOperation(value = "Return a list of all dependents in the db")
		public List<Dependent> getAllDependents() {

			return service.findAll();

		}
		
		/**
		 * API mapping to retrieve dependent by dependentID
		 * @param DependentID
		 * @return <T> casted to ResponseEntity or dependent found in db 
		 */
		@GetMapping("/dependentId/{id}")
		@ApiOperation(value = "Return Dependent by dependentID")
		public <T> T findDependentById(@PathVariable int id) {
			Optional<Dependent> dependent = service.findById(id);
			if (dependent.isPresent()) {
				return (T) dependent.get();

			}
			
			return (T) ResponseEntity.status(200).body("There are no dependents with ID " + id);
	

		}
		
		
		/**
		 * API mapping to retrieve all dependents associated with enrolleeID
		 * @param EnrolleeID 
		 * @return <T> casted to a list of dependents associated with that enrollee or a ResponseEntity 
		 */
		@GetMapping("/dependents/{enrolleeId}")
		@ApiOperation(value = "Return a list of all dependents associated with enrolleeID given")
		public <T> T getDependentsByEnrolleeId(@PathVariable int enrolleeId) throws Exception {

			List<Dependents> dependent = service2.findByEnrolleeId(enrolleeId);
			List<Dependent> list = new ArrayList();
			boolean found = false;
			for(Dependents i: dependent) {
				
				list.add(findDependentById(i.getDependentId()));
			}
			
			if(list.size() == 0) {
				return  (T) ResponseEntity.status(200).body("This enrollee does not have any associated dependents");
				
			}
			
			return (T) list;


		}
		
		/**
		 * API mapping to add a dependent with a specified enrolleeID 
		 * @param Dependent to be added to db and enrolleeID associated with that dependent
		 */
		@PostMapping("/add/dependent/{enrolleeId}")
		@ApiOperation(value = "Add a Dependent into the db")
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
		
		/**
		 * API mapping delete dependent by dependentID
		 * @param EnrolleeID associated with dependent and dependentID
		 * @return ResponseEntity generated from request
		 */
		@DeleteMapping("/delete/dependent/{dependentId}/{enrolleeId}")
		@ApiOperation(value = "Delete Dependent by dependentID")
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
		
		/**
		 * API mapping to update a dependent
		 * @param dependent to be updated
		 * @return ResponseEntity generated from request
		 */
		@PatchMapping("/update/dependent")
		@ApiOperation(value = "Update a Dependent")
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
