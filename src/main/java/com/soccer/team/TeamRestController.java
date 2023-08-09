package com.soccer.team;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.team.bo.TeamBO;
import com.soccer.team.domain.Team;

@RestController
@RequestMapping("/team")
public class TeamRestController {

	@Autowired
	private TeamBO teamBO;
	
	
	// 팀명 중복 검사 
	@RequestMapping("/is_duplicated_team")
	public Map<String, Object> isDuplicatedTeam(
			@RequestParam("teamName") String teamName){
		
		Map<String, Object> result = new HashMap<>();
		result.put("isDuplicationTeam", false);
		
		// db select
		Team team = teamBO.getTeamByName(teamName);
		if (team != null) {
			result.put("isDuplicationTeam", true);
		} 
		
		return result;
	}
	
	// 팀 만들기 
	@PostMapping("/create_team")
	public Map<String, Object> createTeam(
			@RequestParam("teamName") String teamName,
			@RequestParam("skill") String skill,
			@RequestParam("activeArea") String activeArea,
			@RequestParam(value = "introduce", required = false) String introduce,
			HttpSession session
			){
		
		Map<String, Object> result = new HashMap<>();
		int leaderId = (int)session.getAttribute("userId");
		// db insert;
		
		int insertResult = teamBO.addTeam(leaderId, teamName, skill, activeArea, introduce);
		
		if (insertResult > 0 ) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "팀생성에 실패하였습니다.");
		}
		
		return result;
	}
}











