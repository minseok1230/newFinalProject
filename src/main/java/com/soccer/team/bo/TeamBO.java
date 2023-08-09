package com.soccer.team.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.team.dao.TeamMapper;
import com.soccer.team.domain.Team;

@Service
public class TeamBO {

	@Autowired
	private TeamMapper teamMapper;
	
	public Team getTeamByName(String name) {
		return teamMapper.selectTeamByName(name);
	}
	
	// 팀 생성
	public Map<String, Object> addTeam(int leaderId, String teamName, String skill, String activeArea, String introduce) {
		Map<String, Object> teamParameter = new HashMap<>();
		
		teamParameter.put("teamId", null);
		teamParameter.put("leaderId", leaderId);
		teamParameter.put("teamName", teamName);
		teamParameter.put("skill", skill);
		teamParameter.put("activeArea", activeArea);
		teamParameter.put("introduce", introduce);
		
		teamMapper.insertTeam(teamParameter);
		return teamParameter;
	}
	
	public List<Team> getTeam(){
		return teamMapper.selectTeam();
	}
	
	
}











