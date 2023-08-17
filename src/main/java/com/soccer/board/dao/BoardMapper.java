package com.soccer.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.board.domain.Board;

@Repository
public interface BoardMapper {

	public List<Board> selectBoardByType(String type);
	
	public List<Board> selectBoardByTypeLimit(String type);
	
	public List<Board> selectBoardByTypeByPageNum(
			@Param("type") String type, 
			@Param("startPageNum") int startPageNum, 
			@Param("POST_MAX_SIZE") int POST_MAX_SIZE);
	
	public Board selectBoardById(int id); 
	
	public int selectBoardCount(String type);
	
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
	
	public int deleteBoardByIdAndUserId(
			@Param("boardId") int boardId,
			@Param("userId") int userId);
}
