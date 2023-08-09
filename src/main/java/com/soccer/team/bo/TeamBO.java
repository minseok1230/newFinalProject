package com.soccer.team.bo;

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
	
	public int addTeam(int leaderId, String teamName, String skill, String activeArea, String introduce) {
		return teamMapper.insertTeam(leaderId, teamName, skill, activeArea, introduce);
	}
}
