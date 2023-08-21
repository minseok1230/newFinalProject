package com.soccer.member.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.member.dao.MemberMapper;
import com.soccer.member.domain.Member;
import com.soccer.user.bo.UserBO;

@Service
public class MemberBO {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private UserBO userBO;
	
	/* 팀 가입 신청 */
	public int addMember(int userId, int teamId) {
		boolean approval = false;
		
		return memberMapper.insertMemberByTeamId(userId, teamId, approval);
	}
	
	public Member getMemberByUserId(int userId) {
		return memberMapper.selectMemberByUserId(userId);
	}
	
	public List<Member> getMemberByApprovalAndTeamId(int teamId, boolean approval) {
		return memberMapper.selectMemberByApprovalAndTeamId(teamId, approval);
	}
	
	public List<Member> getMemberListByUserId(int userId){
		return memberMapper.selectMemberListByUserId(userId);
	}
	
	public List<Member> getMemberListByTeamId(int teamId){
		return memberMapper.selectMemberListByUserId(teamId);
	}
	
	public int deleteMemberByTeamIdAndUserId(int teamId, int userId) {
		return memberMapper.deleteMemberByTeamIdAndUserId(teamId, userId);
	}
	
	public void updateMemberByTeamIdAndUserId(int teamId, int userId) {
		
		String role = "팀원";
		memberMapper.updateMemberByTeamIdAndUserId(teamId, userId);
		
		// user update
		userBO.updateUserByTeamIdAndRole(teamId, role, userId);
	}
}









