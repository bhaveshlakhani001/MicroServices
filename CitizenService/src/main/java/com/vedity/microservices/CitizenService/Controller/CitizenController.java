package com.vedity.microservices.CitizenService.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vedity.microservices.CitizenService.Entity.Citizen;
import com.vedity.microservices.CitizenService.exception.VaccineationCenterNotFoundException;
import com.vedity.microservices.CitizenService.repositories.CitizenRepo;


@RestController
@RequestMapping("/api")
public class CitizenController {
	
	@Autowired
	private CitizenRepo repo; 

	@RequestMapping(path ="/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
	
	
	@RequestMapping(path ="/getAllCenter")
	public List<Citizen> getAllCenter() {
		return repo.findAll();
	}
	
	
	@RequestMapping(path ="citizen/{id}")
	public ResponseEntity<java.util.List<Citizen>> getById(@PathVariable Integer id) {
		
		List<Citizen> listCitizen = repo.findByVaccinationCenterId(id);
		boolean isfound = isCenterFound(repo,listCitizen,id);
		if(!isfound)
			throw new VaccineationCenterNotFoundException("Id "+id);
		
		
		return new ResponseEntity<>(listCitizen, HttpStatus.OK);
	}
	
	private boolean isCenterFound(CitizenRepo repo2, List<Citizen> citizen, Integer id) {

		for (Citizen c : citizen) {
			if (c.getVaccinationCenterId() == id) {
				return true;
			}
		}

		return false;
	}


	@PostMapping(path = "/citizen")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {

		Citizen citizen = repo.save(newCitizen);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(citizen.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
}
