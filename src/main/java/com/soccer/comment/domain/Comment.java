package com.soccer.comment.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Comment {
	private int id;
	private String type;
	private int boardId;
	private int userId;
	private String content;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
