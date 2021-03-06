package com.cognixia.jump.controller;

import java.util.List;


import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.repository.EnrolleeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * Controller for API calls to access Enrollees in the db 
 * 
 */

@RestController
@RequestMapping("/api")
@Api(value = "value block HERE", description = "Controller to access information on enrollees")

public class EnrolleeController {
	
	@Autowired
	EnrolleeRepository service;
	
	
	/**
	 * API mapping to retrieve all enrollees in the database
	 * @return List of enrollees
	 */
	@GetMapping("/enrollees")
	@ApiOperation(value = "Returns all enrollees")
	public List<Enrollee> getAllEnrollees() {
		return service.findAll();

	}
	
	
	/**
	 * API mapping to retrieve enrollee by enrolleeID
	 * @param enrollee id 
	 * @return <T> casted from a ResponseEntity or Enrollee
	 */
	@GetMapping("/enrollee/{id}")
	@ApiOperation(value = "Returns Enrollee by enrolleeID")
	public <T> T getEnrolleeById(@PathVariable int id) {

		Optional<Enrollee> enrollee = service.findById(id);
		if (enrollee.isPresent()) {
			return (T) enrollee.get();

		}
		
		return (T) ResponseEntity.status(200).body("There are no enrollees with ID " + id);


	}
	
	/**
	 * API mapping to add enrollee into the db
	 * @param enrollee from the Api resquest body
	 */
	@PostMapping("/add/enrollee")
	@ApiOperation(value = "Add Enrollee into db")
	public void addEnrollee(@RequestBody Enrollee newEnrollee) {

		newEnrollee.setId(-1);
		Enrollee added = service.save(newEnrollee);
		System.out.println("Added enrollee: " + added);

	}
	
	
	/**
	 * API mapping to delete enrollee by enrolleeID
	 * @param enrollee id 
	 * @return ResponseEntity response retrieved from the request
	 */
	@DeleteMapping("/delete/enrollee/{id}")
	@ApiOperation(value = "Delete Enrollee by enrolleeID")
	public ResponseEntity<String> deleteEnrollee(@PathVariable int id) {
		Optional<Enrollee> found = service.findById(id);
		if (found.isPresent()) {

			service.deleteById(id);
			return ResponseEntity.status(200).body("Deleted Enrollee with id: " + id);

		} else {
			return ResponseEntity.status(400)
					.header("Enrollee id", id + "")
					.body("Enrollee with id = " + id + " not found");
		}

	}
	
	
	
	/**
	 * API mapping to update an existing enrollee
	 * @param RequestBody enrollee with updated information
	 * @return ResponseEntity response retrieved from the request
	 */
	@PatchMapping("/update/enrollee")
	@ApiOperation(value = "Update Enrollee information")
	public ResponseEntity <String> updateEnrollee(@RequestBody Enrollee enrollee) {
		Optional<Enrollee> found = service.findById(enrollee.getId());
		if(found.isPresent()) {
			service.save(enrollee);
			return ResponseEntity.status(200).body("Updated the enrollee");

		}
		 else {
				return ResponseEntity.status(400)
						.body("Enrollee with ID " + enrollee.getId() + " was not found in the database." );
			}
		
			

	

	}


	
	


}

