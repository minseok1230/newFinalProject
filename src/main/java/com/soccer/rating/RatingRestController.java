package com.soccer.rating;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.rating.bo.RatingBO;

@RestController
@RequestMapping("/rating")
public class RatingRestController {
	
	@Autowired
	private RatingBO ratingBO;
	
	@PostMapping("/create_rating")
	public Map<String, Object> createRating(
			@RequestParam("count") double count,
			@RequestParam("teamId") int teamId,
			@RequestParam("matchedTeamId") int matchedTeamId,
			@RequestParam("matchId") int matchId
			){
		
		
		// db select 및 insert
		Map<String, Object> result = new HashMap<>();
		int addResult = ratingBO.addRatingByTeamIdAndMatchedTeamIdAndMatchIdTemperature(matchId, teamId, matchedTeamId, count);
		if (addResult == 300) {
			result.put("code", 300);
		} else if (addResult > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "매너점수 등록에 실패하였습니다.");
		}
		return result;
	}

}
