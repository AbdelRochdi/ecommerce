package com.youcode.ecommerce.services;

import java.util.List;

import com.youcode.ecommerce.entities.Country;
import com.youcode.ecommerce.entities.State;

public interface CountryStateService {

	List<Country> FindAllCountries();
	
	List<State> FindAllStates();
	
	List<State> findStatesByCountryCode(String code);
}
