package com.soccer.member.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.member.domain.Member;
import com.soccer.member.domain.MemberView;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class MemberService {

	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private MemberBO memberBO;
	
	@Autowired
	private UserBO userBO;
	
	
	public List<MemberView> generateMemberViewByApproval(int teamId, boolean approval){
		
		List<MemberView> memberViewList = new ArrayList<>();
		
		// 멤버 검색
		List<Member> memberList = memberBO.getMemberByApprovalAndTeamId(teamId, approval);
		
		for (Member member : memberList) {
			MemberView memberView = new MemberView();
			memberView.setMember(member);
			
			//팀
			TeamEntity team = teamBO.getTeamById(member.getTeamId());
			memberView.setTeam(team);
			
			// 사용자 정보  
			User user = userBO.getUserById(member.getUserId());
			memberView.setUser(user);
			
			memberViewList.add(memberView);
		}
		return memberViewList;
	}
}
