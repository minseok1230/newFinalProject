package com.soccer.board.domain;

import java.util.List;

import com.soccer.comment.domain.CommentView;
import com.soccer.user.domain.User;

import lombok.Data;

@Data 
public class BoardView {

		// 글 1개
		private Board board;
		
		// 글쓴이 정보
		private User user;
		
		// 댓글들
		private List<CommentView> commentList;
		
	
}
