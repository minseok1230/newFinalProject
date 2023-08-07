package com.soccer.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.comment.dao.CommentMapper;
import com.soccer.comment.domain.Comment;
import com.soccer.comment.domain.CommentView;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	// 댓글 등록 
	public int addComment(int userId, int boardId, String type, String content) {
		return commentMapper.insertComment(userId, boardId, type, content);
	}
	
	// input: boardId
	// output: 가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int boardId) {
		// 결과 리스트 (return 할 값)
		List<CommentView> commentViewList = new ArrayList<>();
			
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByBoardId(boardId);
			
		// 반복문 순회   comment => commentView    => commentViewList에 담는다.
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView(); // commentView(comment / user 정보)
			commentView.setComment(comment);
				
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user); // 댓글쓴이
				
			commentViewList.add(commentView);
		}
			
		return commentViewList;
	}
}
