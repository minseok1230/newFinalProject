package com.soccer.member.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.member.dao.MemberMapper;
import com.soccer.member.domain.Member;

@Service
public class MemberBO {

	@Autowired
	private MemberMapper memberMapper;
	
	/* 팀 가입 신청 */
	public int addMember(int userId, int teamId) {
		boolean approval = false;
		
		return memberMapper.insertMemberByTeamId(userId, teamId, approval);
	}
	
	public Member getMemberByUserId(int userId) {
		return memberMapper.selectMemberByUserId(userId);
	}
}









