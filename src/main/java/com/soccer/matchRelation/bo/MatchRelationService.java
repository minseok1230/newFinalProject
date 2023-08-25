package com.soccer.matchRelation.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.match.bo.MatchService;
import com.soccer.match.domain.MatchView;
import com.soccer.matchRelation.domain.MatchRelation;
import com.soccer.matchRelation.domain.MatchRelationView;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;

@Service
public class MatchRelationService {

	@Autowired
	private MatchRelationBO matchRelationBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private MatchService matchService;
	
	/* 매칭 신청한 MatchRelationView */
	public List<MatchRelationView> generateApplyMatchRelationView(Integer matchedTeamId){
		
		if (matchedTeamId == null) {
			return null;
		}
		
		// 리턴 값
		List<MatchRelationView> result = new ArrayList<>();
		
		String state = "대기중";
		// 매칭 신청한 목록
		List<MatchRelation> applyMatch = matchRelationBO.getMatchRelationByMatchedTeamId(matchedTeamId, state);
		
		for (MatchRelation matchRelation : applyMatch) {
			MatchRelationView matchRelationView = new MatchRelationView();
			
			// 
			matchRelationView.setMatchRelation(matchRelation);
			
			// 요청받은 팀 정보
			TeamEntity team = teamBO.getTeamById(matchRelation.getTeamId());
			matchRelationView.setTeam(team);
			
			MatchView matchView = matchService.generateMatchView(matchRelation.getMatchId());
			matchRelationView.setMatchView(matchView);
			
			result.add(matchRelationView);
		}
		return result;
	}
	
	/* 매칭 신청받은 MatchRelationView */
	public List<MatchRelationView> generateApplyedMatchRelationView(Integer teamId){
		
		if (teamId == null) {
			return null;
		}
		// 리턴 값
		List<MatchRelationView> result = new ArrayList<>();
		
		String state = "대기중";
		// 매칭 신청한 목록
		List<MatchRelation> applyedMatch = matchRelationBO.getMatchRelationByTeamId(teamId, state);
		
		for (MatchRelation matchRelation : applyedMatch) {
			MatchRelationView matchRelationView = new MatchRelationView();
			
			// 
			matchRelationView.setMatchRelation(matchRelation);
			
			// 요청받은 팀 정보
			TeamEntity team = teamBO.getTeamById(matchRelation.getMatchedTeamId());
			matchRelationView.setTeam(team);
			
			MatchView matchView = matchService.generateMatchView(matchRelation.getMatchId());
			matchRelationView.setMatchView(matchView);
			
			result.add(matchRelationView);
		}
		return result;
	}
	
	
	// 매칭 완료 목록 
	public List<MatchRelationView> generateDoneMatchRelationView(Integer teamId){
		
		if (teamId == null) {
			return null;
		}
		// 리턴 값
		List<MatchRelationView> result = new ArrayList<>();

		String state = "매칭완료";
		String state2 = "경기완료";
		// 매칭 신청한 목록
		List<MatchRelation> doneMatch = matchRelationBO.getMatchRelationByTeamIdAndMatchedTeamId(teamId, state);
		List<MatchRelation> endMatch = matchRelationBO.getMatchRelationByTeamIdAndMatchedTeamId(teamId, state2);
		
		for (MatchRelation matchRelation : doneMatch) {
			MatchRelationView matchRelationView = new MatchRelationView();
			matchRelationView.setMatchRelation(matchRelation);
			
			// 상대팀
			if (matchRelation.getTeamId() == teamId) {
				TeamEntity team = teamBO.getTeamById(matchRelation.getMatchedTeamId());
				matchRelationView.setTeam(team);
			} else {
				TeamEntity team = teamBO.getTeamById(matchRelation.getTeamId());
				matchRelationView.setTeam(team);
			}

			MatchView matchView = matchService.generateMatchView(matchRelation.getMatchId());
			matchRelationView.setMatchView(matchView);

			result.add(matchRelationView);
		}
		
		for (MatchRelation matchRelation : endMatch) {
			MatchRelationView matchRelationView = new MatchRelationView();
			matchRelationView.setMatchRelation(matchRelation);
			
			// 상대팀
			if (matchRelation.getTeamId() == teamId) {
				TeamEntity team = teamBO.getTeamById(matchRelation.getMatchedTeamId());
				matchRelationView.setTeam(team);
			} else {
				TeamEntity team = teamBO.getTeamById(matchRelation.getTeamId());
				matchRelationView.setTeam(team);
			}

			MatchView matchView = matchService.generateMatchView(matchRelation.getMatchId());
			matchRelationView.setMatchView(matchView);

			result.add(matchRelationView);
		}
		return result;
	}
	
	
}
