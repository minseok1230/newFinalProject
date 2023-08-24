package com.soccer.match.domain;

import com.soccer.matchRelation.domain.MatchRelation;
import com.soccer.reservation.domain.Reservation;

import lombok.Data;

@Data
public class MatchUpdateView {
	
	// 경기장 하나
	private Reservation reservation;
	
	// 매칭글 하나
	private Match match;
	
	// matchRelation 하나
	private MatchRelation matchRelation;

}
