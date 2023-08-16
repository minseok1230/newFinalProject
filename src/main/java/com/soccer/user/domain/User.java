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
	private String birth;
	private Integer mail_check;
	private String position;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}

/*
 * private UserRole role; // role을 enum 타입으로 변경
 * 
 *  public enum UserRole {
        ADMIN,
        USER,
        GUEST
        // 필요한 역할들을 추가로 정의할 수 있습니다.
    }
 * */


