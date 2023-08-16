package com.soccer.match.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.match.domain.Match;



@Repository
public interface MatchMapper {

	
	public List<Match> selectMatchByTeamId(int teamId);
	
	public List<Match> selectMatch();
	
	public Match selectMatchById(int id);
	
	public int insertMatch(
			@Param("teamId") int teamId, 
			@Param("reservationId") int reservationId, 
			@Param("title") String title, 
			@Param("price") int price, 
			@Param("content") String content);
	
}
