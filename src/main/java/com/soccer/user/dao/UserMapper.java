package com.soccer.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
