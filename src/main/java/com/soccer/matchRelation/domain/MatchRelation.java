package com.soccer.matchRelation.domain;

import java.time.ZonedDateTime;

import lombok.Data;
@Data
public class MatchRelation {

	private int id;
	private int matchId;
	private int teamId;
	private int matchedTeamId;
	private String state;
	private ZonedDateTime createdAt;
}
