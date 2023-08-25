package com.soccer.rating.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Rating {

	private int id;
	private int matchedId;
	private int teamId;
	private int ratedTeamId;
	private double temperature;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
