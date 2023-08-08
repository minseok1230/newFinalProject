package com.soccer.team;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		/*
		 * if (user != null) { result.put("isDuplicationTeam", true); }
		 */
		
		return result;
	}
}











