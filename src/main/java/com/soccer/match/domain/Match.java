package com.soccer.match.domain;

import java.time.ZonedDateTime;

import lombok.Data;


@Data
public class Match {

	private int id;
	private int teamId;
	private int reservationId;
	private String title;
	private int price;
	private String content;
	private String state;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
