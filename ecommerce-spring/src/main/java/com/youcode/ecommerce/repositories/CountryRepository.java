package com.youcode.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecommerce.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
