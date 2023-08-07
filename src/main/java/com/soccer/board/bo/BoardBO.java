package com.soccer.board.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.board.dao.BoardMapper;
import com.soccer.board.domain.Board;

@Service
public class BoardBO {

	@Autowired
	private BoardMapper boardMapper;
	
	//board 전체 조회
	public List<Board> getBoardByType(String type){
		return boardMapper.selectBoardByType(type);
	}
	
	//board 최근 3개 조회 
	public List<Board> getBoardByTypeLimit(String type){
		return boardMapper.selectBoardByTypeLimit(type);
	}
	
	public Board getBoardById(int id) {
		return boardMapper.selectBoardById(id);
	}
	
	// board 글쓰기
	public int addBoardByTitleContentUserId(int userId, String type, String title, String content) {
		return boardMapper.insertBoardByTitleContentUserId(userId, type, title, content);
	}
	
	// board update 
	public int updateBoardByIdAndUserId(int boardId, int userId, String title, String content) {
		return boardMapper.updateBoardByIdAndUserId(boardId, userId, title, content);
	}
	
	
	
}
