package com.soccer.member.dao;

import java.util.List;

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
	
	public List<Member> selectMemberByApprovalAndTeamId(
			@Param("teamId") int teamId,
			@Param("approval") boolean approval);
	
	public List<Member> selectMemberListByUserId(int userId);
	
	public List<Member> selectMemberListByTeamId(int teamId);
	
	public int deleteMemberByTeamIdAndUserId(
			@Param("teamId") int teamId, 
			@Param("userId") int userId);
	
	public void updateMemberByTeamIdAndUserId(
			@Param("teamId") int teamId, 
			@Param("userId") int userId);
}
