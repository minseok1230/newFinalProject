package com.soccer.reservation.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class Reservation {
	private int id;
	private String region;
	private String stadiumName;
	private int teamId;
	private String matchTime;
	private Date matchDate;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	
}
