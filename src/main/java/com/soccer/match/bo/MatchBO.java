package com.soccer.match.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.match.dao.MatchMapper;
import com.soccer.match.domain.Match;
import com.soccer.match.domain.MatchView;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;

@Service
public class MatchBO {

	@Autowired
	private MatchMapper matchMapper;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private ReservationBO reservationBO;
	
	public List<Match> getMatchByTeamId(int teamId){
		return matchMapper.selectMatchByTeamId(teamId);
	}
	
	public int addMatch(int teamId, int reservationId, String title, int price, String content) {
		return matchMapper.insertMatch(teamId, reservationId, title, price, content);
	}
	
	public List<MatchView> generateMatchViewList(){
		
		// 리턴 값 ( 여러개의 MatchView )
		List<MatchView> teamViewList = new ArrayList<>();
		
		List<Match> matchList = matchMapper.selectMatch();
		
		for (Match match : matchList) {
			MatchView matchView = new MatchView();
			
			// match 넣기
			matchView.setMatch(match);

			// 팀 정보 넣기 
			TeamEntity team = teamBO.getTeamById(match.getTeamId());
			matchView.setTeam(team);
			
			// 경기장 정보 넣기
			Reservation reservation = reservationBO.getReservationById(match.getReservationId());
			matchView.setReservation(reservation);
			
			teamViewList.add(matchView);
		}
		
		return teamViewList;
	}
	
	public MatchView generateMatchView(int matchId) {
		
		MatchView matchView = new MatchView();
		
		Match match = matchMapper.selectMatchById(matchId);
		matchView.setMatch(match);
		
		// 팀 정보 넣기 
		TeamEntity team = teamBO.getTeamById(match.getTeamId());
		matchView.setTeam(team);
					
		// 경기장 정보 넣기
		Reservation reservation = reservationBO.getReservationById(match.getReservationId());
		matchView.setReservation(reservation);
		
		return matchView;
	}
	
	
	
}



















