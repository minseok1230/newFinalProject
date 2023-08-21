package com.soccer.matchRelation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.matchRelation.bo.MatchRelationBO;

@RestController
@RequestMapping("/matchRelation")
public class MatchRelationRestController {

		@Autowired
		private MatchRelationBO matchRelationBO;
	
		// match Relation
		@PostMapping("/create_matchRelation")
		public Map<String, Object> createMatchRelation(
				@RequestParam("teamId") int teamId, // 신청하는 사람
				@RequestParam("matchingTeamId") int matchingTeamId, // 글올린사람
				@RequestParam("matchId") int matchId
				){
			
			
			Map<String, Object> result = new HashMap<>();
			// matchRelation DB insert
			int insertMatchRelationResult = matchRelationBO.addMatchRelationByTeamIdMatchIdMatchedTeamId(matchingTeamId, matchId, teamId);
			
			if (insertMatchRelationResult == 300) {
				result.put("errorMessage", "이미 신청한 경기입니다. 확인 부탁드립니다.");
			}
			
			if (insertMatchRelationResult > 0) {
				result.put("code", 1);
			} else {
				result.put("errorMessage", "신청 실패하였습니다.");
			}
			
			return result;
		}
		
		// 수정
		@PutMapping("{matchRelationId}")
		public Map<String, Object> updateMatchRelation(
				@PathVariable int matchRelationId){
			
			Map<String, Object> result = new HashMap<>();
			
			// db update
			int updateResult = matchRelationBO.updateMatchRelationById(matchRelationId);
			if (updateResult > 0) {
				result.put("code", 1);
			} else {
				result.put("errorMessage", "매칭 수락에 실패하였습니다.");
			}
			
			return result;
		}
}
