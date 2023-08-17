package com.soccer.match.domain;

import java.util.List;

import com.soccer.board.domain.Board;
import com.soccer.comment.domain.CommentView;
import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class MatchCommentView {

			// 글 1개
			private Match match;
			
			// 글쓴이 정보
			private User user;
			
			// 댓글들
			private List<CommentView> commentList;
}
