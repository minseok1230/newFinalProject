package com.soccer.board.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.soccer.board.dao.BoardMapper;
import com.soccer.board.domain.Board;
import com.soccer.comment.bo.CommentBO;
import com.soccer.user.bo.UserBO;

@Service
public class BoardBO {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	
	
	//board 전체 조회 + 페이징
	public List<Board> getBoardByType(String type){
		return boardMapper.selectBoardByType(type);
	}
	
	//board 최근 3개 조회 
	public List<Board> getBoardByTypeLimit(String type){
		return boardMapper.selectBoardByTypeLimit(type);
	}
	
	public int getBoardCount(String type) {
		return boardMapper.selectBoardCount(type);
	}
	
	public Board getBoardById(int id) {
		return boardMapper.selectBoardById(id);
	}
	
	// board 글쓰기
	public int addBoardByTitleContentUserId(int userId, String type, String title, String content) {
		return boardMapper.insertBoardByTitleContentUserId(userId, type, title, content);
	}
	
	// board 수정
	public int updateBoardByIdAndUserId(int boardId, int userId, String title, String content) {
		return boardMapper.updateBoardByIdAndUserId(boardId, userId, title, content);
	}
	
	// board 삭제
	public int deleteBoardByIdAndUserId(int boardId, int userId) {
		
		// 댓글 삭제 
		commentBO.deleteCommentByBoardId(boardId);
		
		// 글 삭제 
		return boardMapper.deleteBoardByIdAndUserId(boardId, userId);
	};
	
	public List<Board> getBoardByTypeByPageNum(String type, int startPage, int POST_MAX_SIZE){
		return boardMapper.selectBoardByTypeByPageNum(type, startPage, POST_MAX_SIZE);
	}
	
	
	
	
	
}

















