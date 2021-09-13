package com.youcode.ecommerce.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "user_id", updatable = false, nullable = false)
	private long id;

	@Column(name = "user_uuid", unique = true, updatable = false)
	private UUID uuid = UUID.randomUUID();
	
	@Size(min = 1)
	@NotBlank
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Size(min = 1)
	@NotBlank
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Size(min = 6)
	@Column(name = "password", nullable = false)
	private String password = "123456";
	
	@Column(name = "is_enabled")
	private boolean isEnabled = true;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();
	
	public UserEntity() {
		super();
	}

	public UserEntity(long id, UUID uuid, @Size(min = 1) @NotBlank String firstName,
			@Size(min = 1) @NotBlank String lastName, @Email String email, @NotBlank @Size(min = 6) String password,
			boolean isEnabled, List<Role> roles) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
