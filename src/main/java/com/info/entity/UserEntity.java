package com.info.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity {
	@Id
	@GeneratedValue
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	@Column(unique = true)
	private String userEmail;
	private Integer userPhno;
	private LocalDate userDob;
	private String userGender;
	private Integer userContry;
	private Integer userState;
	private Integer userCity;
	private String userPwd;
	private String userAccountStatus;
	@CreationTimestamp
	private LocalDate createBate;
	@CreationTimestamp
	private LocalDate updateDate;

}
