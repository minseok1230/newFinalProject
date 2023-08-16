package com.soccer.member.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.member.domain.Member;

@Repository
public interface MemberMapper {

	
	public int insertMemberByTeamId(
			@Param("userId") int userId, 
			@Param("teamId") int teamId, 
			@Param("approval") boolean approval);
	
	public Member selectMemberByUserId(int userId);
}
