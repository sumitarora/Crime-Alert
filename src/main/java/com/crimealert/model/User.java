package com.crimealert.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.crimealert.enums.Role;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_user")
public class User {
        
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_user_id")
	@SequenceGenerator(name="seq_user_id", sequenceName="seq_user_id")	
	@Column(name="user_id")
    private Integer userId;

	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false)
	private String email;

	@Column(name="about", nullable=true)
	private String about;

	@Column(name="photo", nullable=false)
	private String photo;

	@Column(name="address", nullable=true)
	private String address;

	@Column(name="city", nullable=true)
	private String city;
	
	@Column(name="provience", nullable=true)
	private String provience;
	
	@Column(name="country", nullable=true)
	private String country;
	
	@Column(name="password", nullable=false)
	private String password;

	@Column(name="enabled", nullable=false)
	private Boolean enabled;
	
	@Column(name="verification_token", nullable=true)
	private String verifyToken;
	
	@Column(name="forgot_password_token", nullable=true)
	private String forgotPasswordToken;

	@Column(name="date_joined", nullable=true)
	private String dateJoined;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", nullable=false)
	private Role role;
	
	@Transient
	private Boolean fromAdmin = false;	
        
}