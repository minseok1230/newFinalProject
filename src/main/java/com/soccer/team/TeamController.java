package com.soccer.team;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.member.bo.MemberBO;
import com.soccer.member.domain.Member;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.domain.Team;
import com.soccer.team.entity.TeamEntity;

@Controller
@RequestMapping("/team")
public class TeamController {

	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private MemberBO memberBO;
	
	@GetMapping("/team_create_view")
	public String teamCreateView(Model model) {
		
		model.addAttribute("view", "team/teamCreate");
		return "template/layout";
	}
	
	/**
	 * 팀 목록
	 * @param model
	 * @return
	 */
	@GetMapping("/team_list_view")
	public String teamListView(Model model, HttpSession session) {
		
		// db 가져오기(teamlist)
		List<Team> teamList = teamBO.getTeam();
		
		int userId = (int)session.getAttribute("userId");
		Integer userTeamId = (Integer)session.getAttribute("userTeamId");
		// db 가져오기 (member)
		Member member = memberBO.getMemberByUserId(userId);
		
		
		model.addAttribute("userTeamId", userTeamId);
		model.addAttribute("member", member);
		model.addAttribute("teamList", teamList);
		model.addAttribute("view", "team/teamList");
		return "template/layout";
	}
	
	/**
	 * 팀 수정
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("team_update_view")
	public String teamUpdateView(
			Model model,
			HttpSession session) {
		
		//session
		int userTeamId = (int)session.getAttribute("userTeamId");
		
		// 팀 정보 가져오기 
		TeamEntity myTeam = teamBO.getTeamById(userTeamId); 
		
		model.addAttribute("myTeam", myTeam);
		model.addAttribute("view", "team/teamModify");
		return "template/layout";
	}
}


















