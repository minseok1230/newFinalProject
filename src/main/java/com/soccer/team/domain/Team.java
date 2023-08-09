package com.soccer.team.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Team {

	private int id;
	private String name;
	private int leaderId;
	private String introduce;
	private String profileImagePath;
	private String skill;
	private Double rating;
	private String activeArea;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
