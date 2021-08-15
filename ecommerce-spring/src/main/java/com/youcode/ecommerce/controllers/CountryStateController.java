package com.youcode.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecommerce.entities.Country;
import com.youcode.ecommerce.entities.State;
import com.youcode.ecommerce.services.CountryStateService;

@RestController
@CrossOrigin
@RequestMapping("api/")
public class CountryStateController {
	
	@Autowired
	private CountryStateService countryStateService;

	@GetMapping("countries")
	public ResponseEntity<List<Country>> getAllCountries(){
		List<Country> countryList = countryStateService.FindAllCountries();
		
		return new ResponseEntity<List<Country>>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("states")
	public ResponseEntity<List<State>> getAllStates(@RequestParam(name = "code") String code){
		List<State> stateList = countryStateService.findStatesByCountryCode(code);
		
		return new ResponseEntity<List<State>>(stateList, HttpStatus.OK);
	}
	
}
