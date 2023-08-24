package com.soccer.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.team.domain.Team;
import com.soccer.team.entity.TeamEntity;

@Repository
public interface TeamMapper {

	public int selectTeamCount();
	
	public List<Team> selectTeamByPageNum(
			@Param("startPageNum") int startPageNum, 
			@Param("POST_MAX_SIZE") int POST_MAX_SIZE);
	
	public List<Team> selectTeam();
	
	public Team selectTeamByName(String name);
	
	public TeamEntity selectTeamById(int id);
	
	public void insertTeam(Map<String, Object> teamParameter);
	
	public void updateTeamByTeamId(
			@Param("teamId") int teamId, 
			@Param("teamName") String teamName, 
			@Param("skill") String skill, 
			@Param("activeArea") String activeArea, 
			@Param("introduce") String introduce, 
			@Param("profileImagePath") String profileImagePath);
}
