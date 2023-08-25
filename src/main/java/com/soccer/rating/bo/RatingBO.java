package com.soccer.rating.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.rating.dao.RatingMapper;
import com.soccer.rating.domain.Rating;

@Service
public class RatingBO {

	
	@Autowired
	private RatingMapper ratingMapper;
	
	public Rating getRatingByTeamIdAndMatchedTeamIdAndMatchId(int matchId, int teamId, int matchedTeamId) {
		return ratingMapper.selectRatingByTeamIdAndMatchedTeamIdAndMatchId(matchId, teamId, matchedTeamId);
	}
	
	public int addRatingByTeamIdAndMatchedTeamIdAndMatchIdTemperature(int matchId, int teamId, int matchedTeamId, double temperature) {
		
		
		// db select
		Rating rating = getRatingByTeamIdAndMatchedTeamIdAndMatchId(matchId, teamId, matchedTeamId);
		
		if (rating != null) {
			return 300;
		} 
		// db insert
		return ratingMapper.insertRatingByTeamIdAndMatchedTeamIdAndMatchIdTemperature(matchId, teamId, matchedTeamId, temperature);
		
	}
}
