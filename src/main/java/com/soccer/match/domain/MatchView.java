package com.soccer.match.domain;


import com.soccer.reservation.domain.Reservation;
import com.soccer.team.entity.TeamEntity;

import lombok.Data;

@Data
public class MatchView {
	
		// 매치 하나
		private Match match;
			
		// 팀 하나 
		private TeamEntity team;
		
		// 경기장 하나
		private Reservation reservation;
}
