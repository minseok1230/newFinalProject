package com.soccer.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.team.domain.Team;

@Repository
public interface TeamMapper {

	public List<Team> selectTeam();
	
	public Team selectTeamByName(String name);
	
	public void insertTeam(Map<String, Object> teamParameter);
	
	public void updateTeamByTeamId(
			@Param("teamId") int teamId, 
			@Param("teamName") String teamName, 
			@Param("skill") String skill, 
			@Param("activeArea") String activeArea, 
			@Param("introduce") String introduce, 
			@Param("profileImagePath") String profileImagePath);
}
