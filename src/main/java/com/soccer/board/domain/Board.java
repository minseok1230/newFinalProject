package com.soccer.board.domain;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Board {
	private int id;
	private int userId;
	private String type;
	private String title;
	private String content;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	

}
