package com.soccer.comment.domain;

import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class CommentView {

	// 댓글 하나
	private Comment comment;
		
	// 댓글쓴이
	private User user;
}
