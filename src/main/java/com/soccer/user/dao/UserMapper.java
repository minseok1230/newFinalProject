package com.soccer.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.user.domain.Certification;
import com.soccer.user.domain.User;

@Repository
public interface UserMapper {

	public User selectUserByEmail(String loginEmail);
	
	public Integer insertUser(
			@Param("loginEmail") String loginEmail,
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("phoneNumber") String phoneNumber, 
			@Param("birth") String birth, 
			@Param("role") boolean role,  
			@Param("loginType") String loginType);
	
	public User selectUserByLoginEmailPassword(
			@Param("loginEmail") String loginEmail, 
			@Param("password") String password);
	
	// 기존 이메일 삭제
	public void deleteByEmail(String loginEmail);
	
	// 이메일 인증코드 DB 저장
	public Integer insertCertification(
			@Param("loginEmail") String loginEmail, 
			@Param("code") String code);
	
	// 이메일 인증코드 DB 확인
	public Certification selectCertificationByEmailNum(
			@Param("loginEmail") String loginEmail, 
			@Param("code") String code);
	
	
	public User selectUserByLoginEmailName(
			@Param("name") String name, 
			@Param("loginEmail") String loginEmail);
	
	public Integer updateUserByNameLoginEmail(
			@Param("name") String name, 
			@Param("loginEmail") String loginEmail, 
			@Param("hashedCode") String hashedCode);
}




