package com.soccer.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
