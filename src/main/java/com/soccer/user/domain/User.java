package com.soccer.user.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private String loginType;
	private boolean role;
	private int kakaoKey;
	private String profileImagePath;
	private String phoneNumber;
	private Date birth;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
}
