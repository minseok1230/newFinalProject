package com.soccer.team.domain;

import java.util.List;

import com.soccer.match.domain.Match;
import com.soccer.reservation.domain.Reservation;
import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class TeamView {
	
		// 팀장 정보
		private User leader;
	
		// 팀 한팀
		private Team team;
		
		// 팀원수
		private int totalTeamMember;
			
		// 매칭글 
		private List<Match> matchList;
			
		// 경기장들
		private List<Reservation> reservationList;
}
