package com.soccer.user.entity;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user")
@Getter
@Entity
public class UserEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	private String name;
	
	@Column(name = "loginType")
	private String loginType;
	
	@Column(name = "teamId")
	private Integer teamId;
	private String position;
	private String role;
	
	@Column(name = "kakaoKey")
	private String kakaoKey;
	
	@Column(name = "profileImagePath")
	private String profileImagePath;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	private Date birth;
	private Integer mail_check;
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
}














