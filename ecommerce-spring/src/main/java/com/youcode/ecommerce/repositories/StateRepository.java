package com.youcode.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecommerce.entities.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	
	List<State> findByCountryCode(String code);

}
