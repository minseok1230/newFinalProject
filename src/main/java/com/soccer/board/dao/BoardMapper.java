package com.soccer.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.board.domain.Board;

@Repository
public interface BoardMapper {

	public List<Board> selectBoardByType(String type);
	
	public List<Board> selectBoardByTypeLimit(String type);
	
	public Board selectBoardById(int id); 
	
	public int insertBoardByTitleContentUserId(
			@Param("userId") int userId, 
			@Param("type") String type, 
			@Param("title") String title, 
			@Param("content") String content);
	
	public int updateBoardByIdAndUserId(
			@Param("boardId") int boardId, 
			@Param("userId") int userId, 
			@Param("title") String title, 
			@Param("content") String content);
}
