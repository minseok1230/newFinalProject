package com.soccer.match.domain;


import java.util.List;

import com.soccer.comment.domain.CommentView;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class MatchView {
	
		// 매치 하나
		private Match match;
			
		// 팀 하나 
		private TeamEntity team;
		
		// 경기장 하나
		private Reservation reservation;
		
		// 팀장 정보
		private User leader;
		
		// 댓글들
		private List<CommentView> commentList;
}
