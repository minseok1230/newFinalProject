package com.soccer.matchRelation.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.match.bo.MatchBO;
import com.soccer.match.domain.Match;
import com.soccer.matchRelation.dao.MatchRelationMapper;
import com.soccer.matchRelation.domain.MatchRelation;
import com.soccer.reservation.bo.ReservationBO;

@Service
public class MatchRelationBO {

	@Autowired
	private MatchRelationMapper matchRelationMapper;
	
	@Autowired
	private MatchBO matchBO;
	
	@Autowired ReservationBO reservationBO;
	
	public int addMatchRelationByTeamIdMatchIdMatchedTeamId(int teamId, int matchId, int matchedTeamId) {

		String state = "대기중";
		MatchRelation matchRelation = matchRelationMapper.selectMatchRelationByTeamIdMatchIdMatchedTeamId(teamId, matchId,matchedTeamId, state);
		if (matchRelation != null) {
			return 400;
		}
		return matchRelationMapper.insertMatchRelationByTeamIdMatchIdMatchedTeamId(teamId, matchId, matchedTeamId, state);
	}
	
	public MatchRelation getMatchRelationByMatchIdAndState(int matchId, String state) {
		return matchRelationMapper.selectMatchRelationByMatchIdAndState(matchId, state);
	}

	public List<MatchRelation> getMatchRelationByMatchedTeamId(int matchedTeamId, String state) {
		return matchRelationMapper.selectMatchRelationByMatchedTeamId(matchedTeamId, state);
	}

	public List<MatchRelation> getMatchRelationByTeamId(int teamId, String state) {
		return matchRelationMapper.selectMatchRelationByTeamId(teamId, state);
	}
	
	public List<MatchRelation> getMatchRelationByTeamIdAndMatchedTeamId(int teamId, String state){
		return matchRelationMapper.selectMatchRelationByTeamIdAndMatchedTeamId(teamId, state);
	}
	
	public int updateMatchRelationById(int id) {
		
		MatchRelation matchRelation = matchRelationMapper.selectMatchRelationById(id);
		
		// match 글도 매칭완료로 수정 
		String state = "매칭완료";
		matchBO.updateMatchByIdState(matchRelation.getMatchId(), state);
		
		
		// reservation isPossible값 false로 수정
		Match match = matchBO.getMatchById(matchRelation.getMatchId());
		reservationBO.updateReservationById(match.getReservationId());
		
		// matchRelation 삭제( 매칭 수락 안된 모든 경기들 삭제 필요!!!)
		matchRelationMapper.deleteMatchRelationMatchIdAndTeamIdAndMatchedTeamId(matchRelation.getMatchId(), matchRelation.getTeamId(), matchRelation.getMatchedTeamId());
		
		return matchRelationMapper.updateMatchRelationByIdState(id, state);
	}
	
	public void updateMatchRelationByIdState(int id, String state) {
		matchRelationMapper.updateMatchRelationByIdState(id, state);
	}
	
	public void deleteMatchRelationByMatchId(int matchId) {
		matchRelationMapper.deleteMatchRelationByMatchId(matchId);
	}
}








