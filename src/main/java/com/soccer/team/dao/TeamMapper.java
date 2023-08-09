package com.soccer.team.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.team.domain.Team;

@Repository
public interface TeamMapper {

	public Team selectTeamByName(String name);
	
	public int insertTeam(
			@Param("leaderId") int leaderId, 
			@Param("teamName") String teamName, 
			@Param("skill") String skill, 
			@Param("activeArea") String activeArea, 
			@Param("introduce") String introduce);
}
