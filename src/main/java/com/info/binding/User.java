package com.info.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class User {
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private Integer userPhno;
	private LocalDate userDob;
	private String userGender;
	private Integer userContry;
	private Integer userState;
	private Integer userCity;
	private String userPwd;
	private String userAccountStatus;
	private LocalDate createDate;
	private LocalDate updateDate;


}
