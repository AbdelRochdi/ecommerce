package com.youcode.ecommerce.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youcode.ecommerce.dto.AuthenticationRequest;
import com.youcode.ecommerce.dto.AuthenticationResponse;
import com.youcode.ecommerce.entities.Customer;
import com.youcode.ecommerce.entities.UserEntity;
import com.youcode.ecommerce.services.MyUserDetailsService;
import com.youcode.ecommerce.services.UserServiceImpl;
import com.youcode.ecommerce.utils.JwtUtil;

import io.jsonwebtoken.impl.DefaultClaims;

@Controller
@RequestMapping("api/users/")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("register")
	public ResponseEntity<Customer> registerUser(@Valid @RequestBody Customer customer) throws Exception {

		Customer savedCustomer = userService.createUserEntity(customer);

		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit) {

		List<UserEntity> users = userService.findAllUsers(page, limit);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		Optional<UserEntity> userEntity = userService.getUser(authenticationRequest.getEmail().toLowerCase());

		if (userEntity.isPresent()) {
			if (userEntity.get().isEnabled() == false) {
				throw new Exception("This account is not yet activated");
			}
		}

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail().toLowerCase(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userdetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail().toLowerCase());

		final String jwt = jwtUtil.generateToken(userdetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@GetMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = jwtUtil.getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	

	

}
