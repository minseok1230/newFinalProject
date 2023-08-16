package com.soccer.member.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Member {
	
	private int teamId;
	private int userId;
	private boolean approval;
	private ZonedDateTime createdAt;
	
}
