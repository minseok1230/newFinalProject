package com.soccer.matchRelation.domain;

import com.soccer.match.domain.MatchView;
import com.soccer.team.entity.TeamEntity;

import lombok.Data;

@Data
public class MatchRelationView {

	
	// 1개의 matchRelation
	private MatchRelation matchRelation;
	
	// machedTeam 정보 (요청한 팀 정보 가져오기 위해서)s
	private TeamEntity team;
	
	// matchView (올린 글 정보 가져오기 위해서)
	private MatchView matchView;
}
