package com.youcode.ecommerce.services;

import java.util.Optional;
import java.util.UUID;

import com.youcode.ecommerce.entities.Customer;
import com.youcode.ecommerce.entities.UserEntity;

public interface UserService {

	Customer createUserEntity(Customer customer) throws Exception;

	UserEntity updateUserEntity(UUID uuid, UserEntity userEntity);

	Optional<UserEntity> getUser(String email);
	
	Optional<UserEntity> getUser(UUID uuid);
	
	Optional<UserEntity> getUser(Long id);

	Iterable<UserEntity> findAllUsers(int page, int limit);

}
