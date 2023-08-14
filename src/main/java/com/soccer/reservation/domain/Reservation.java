package com.soccer.reservation.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Reservation {
	private int id;
	private String stadiumAddress;
	private int teamId;
	private String matchTime;
	private String matchDate;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
