package com.soccer.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.comment.domain.Comment;

@Repository
public interface CommentMapper {

	public int insertComment(
			@Param("userId") int userId, 
			@Param("boardId") int boardId, 
			@Param("type") String type, 
			@Param("content") String content);
	
	public int deleteCommentById(int id);
	
	public void deleteCommentByBoardIdAndType(
			@Param("boardId") int boardId, 
			@Param("type") String type);
	
	public List<Comment> selectCommentListByBoardId(int boardId);
}
