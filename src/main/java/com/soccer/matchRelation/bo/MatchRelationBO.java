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
		MatchRelation matchRelation = matchRelationMapper.selectMatchRelationByTeamIdMatchIdMatchedTeamId(teamId, matchId,
				matchedTeamId, state);
		if (matchRelation != null) {
			return 400;
		}
		return matchRelationMapper.insertMatchRelationByTeamIdMatchIdMatchedTeamId(teamId, matchId, matchedTeamId, state);
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
		matchBO.updateMatchById(matchRelation.getMatchId());
		
		
		// reservation isPossible값 false로 수정
		Match match = matchBO.getMatchById(matchRelation.getMatchId());
		reservationBO.updateReservationById(match.getReservationId());
		
		
		return matchRelationMapper.updateMatchRelationById(id);
	}
}
