package com.youcode.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youcode.ecommerce.entities.Country;
import com.youcode.ecommerce.entities.State;
import com.youcode.ecommerce.repositories.CountryRepository;
import com.youcode.ecommerce.repositories.StateRepository;

@Service
public class CountryStateServiceImpl implements CountryStateService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<Country> FindAllCountries() {
		return countryRepository.findAll();
	}

	@Override
	public List<State> FindAllStates() {
		return stateRepository.findAll();
	}

	@Override
	public List<State> findStatesByCountryCode(String code) {
		return stateRepository.findByCountryCode(code);
	}

}
