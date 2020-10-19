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

@RestController
@RequestMapping("/api")
public class EnrolleeController {
	
	@Autowired
	EnrolleeRepository service;

	@GetMapping("/enrollees")
	public List<Enrollee> getAllEnrollees() {

		return service.findAll();

	}
	

	@GetMapping("/enrollee/{id}")
	public Enrollee getEnrolleeById(@PathVariable int id) {

		Optional<Enrollee> enrollee = service.findById(id);
		if (enrollee.isPresent()) {
			return enrollee.get();

		}
		
		return new Enrollee();


	}
	
	@PostMapping("/add/enrollee")
	public void addEnrollee(@RequestBody Enrollee newEnrollee) {

		newEnrollee.setId(-1);
		Enrollee added = service.save(newEnrollee);
		System.out.println("Added enrollee: " + added);

	}
	
	
	@DeleteMapping("/delete/enrollee/{id}")
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
	
	@PatchMapping("/update/enrollee")
	public ResponseEntity <String> updateLocation(@RequestBody Enrollee enrollee) {
		System.out.println();
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

