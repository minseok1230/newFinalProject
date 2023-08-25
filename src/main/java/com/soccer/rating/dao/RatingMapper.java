package com.soccer.rating.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.rating.domain.Rating;

@Repository
public interface RatingMapper {

	
	public Rating selectRatingByTeamIdAndMatchedTeamIdAndMatchId(
			@Param("matchId") int matchId, 
			@Param("teamId") int teamId, 
			@Param("matchedTeamId") int matchedTeamId);
	
	public int insertRatingByTeamIdAndMatchedTeamIdAndMatchIdTemperature(
			@Param("matchId") int matchId, 
			@Param("teamId") int teamId, 
			@Param("matchedTeamId") int matchedTeamId, 
			@Param("temperature") double temperature);

}
