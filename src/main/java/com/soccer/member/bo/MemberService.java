package com.soccer.member.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.member.domain.Member;
import com.soccer.member.domain.MemberView;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;

@Service
public class MemberService {

	@Autowired
	private TeamBO teamBO;
	
	
	@Autowired
	private MemberBO memberBO;
	
	
	public List<MemberView> generateMemberViewByTeamId(Integer teamId){
		
		List<MemberView> memberViewList = new ArrayList<>();
		
		MemberView memberView = new MemberView();
		// 팀
		TeamEntity team = teamBO.getTeamById(teamId);
		memberView.setTeam(team);
		
		// 멤버 
		List<Member> memberList = memberBO.getMemberListByTeamId(team.getId());
		memberView.setMember(memberList);
		
		memberViewList.add(memberView);
		
		return memberViewList;
	}
}
