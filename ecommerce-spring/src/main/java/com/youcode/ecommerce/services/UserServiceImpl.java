package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youcode.ecommerce.entities.Customer;
import com.youcode.ecommerce.entities.UserEntity;
import com.youcode.ecommerce.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	

	@Transactional
	public Customer createUserEntity(Customer userEntity) throws Exception {

		Optional<UserEntity> checkUser = userRepository.findByEmail(userEntity.getEmail().toLowerCase());

		if (checkUser.isPresent()) {
			throw new Exception("User already exists");
		} else {

			userEntity.setEmail(userEntity.getEmail().toLowerCase());

			userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

			return userRepository.save(userEntity);

		}

	}

	public List<UserEntity> findAllUsers(int page, int limit) {
		if (page > 0)
			page -= 1;

		Pageable pageable = PageRequest.of(page, limit);

		Page<UserEntity> userPage = userRepository.findAll(pageable);

		List<UserEntity> users = userPage.getContent();

		return users;
	}

	@Override
	public UserEntity updateUserEntity(UUID uuid, UserEntity userEntity) {

		Optional<UserEntity> updatedUser = getUser(uuid);

		if (updatedUser.isPresent()) {
			
			UserEntity user = updatedUser.get();

			user.setFirstName(userEntity.getFirstName());
			user.setLastName(userEntity.getLastName());
			
			UserEntity userUpdated = userRepository.save(user);
			
			return userUpdated;
		}else {
			throw new EntityNotFoundException();
		}
		
	}

	@Override
	public Optional<UserEntity> getUser(String email) {

		Optional<UserEntity> userEntity = userRepository.findByEmail(email);

		return userEntity;
	}

	@Override
	public Optional<UserEntity> getUser(UUID uuid) {

		Optional<UserEntity> userEntity = userRepository.findByUuid(uuid);

		return userEntity;
	}
	
	@Override
	public Optional<UserEntity> getUser(Long id) {

		Optional<UserEntity> userEntity = userRepository.findById(id);

		return userEntity;
	}

}
