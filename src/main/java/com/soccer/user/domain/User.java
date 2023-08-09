package com.soccer.user.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class User {
	private int id;
	private String email;
	private Integer teamId;
	private String password;
	private String name;
	private String loginType;
	private String role;
	private Integer kakaoKey;
	private String profileImagePath;
	private String phoneNumber;
	private Date birth;
	private Integer mail_check;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
