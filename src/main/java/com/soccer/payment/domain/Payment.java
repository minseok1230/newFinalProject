package com.soccer.payment.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class Payment {

	private int id;
	private int teamId;
	private String region;
	private String stadiumName;
	private String matchTime;
	private Date matchDate;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
