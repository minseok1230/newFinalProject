package com.soccer.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.user.dao.UserMapper;
import com.soccer.user.domain.User;

@Service
public class UserBO {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByEmail(String loginEmail){
		return userMapper.selectUserByEmail(loginEmail);
	}
	
	public Integer addUser(String loginEmail,String password, String name, String phoneNumber, String birth, boolean role,  String loginType) {
		return userMapper.insertUser(loginEmail, password, name, phoneNumber, birth, role, loginType);
	}
	
	public User getUserByLoginEmailPassword(String loginEmail, String password) {
		return userMapper.selectUserByLoginEmailPassword(loginEmail, password);
	}
	
}
