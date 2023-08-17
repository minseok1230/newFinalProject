package com.soccer.board.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.board.dao.BoardMapper;
import com.soccer.board.domain.Board;
import com.soccer.board.domain.BoardView;
import com.soccer.comment.bo.CommentBO;
import com.soccer.comment.domain.CommentView;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class BoardBO {

	private static final int POST_MAX_SIZE = 3;
	
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
	
	
	//boardViewList 만들기
	public List<BoardView> generateBoardViewList(String type, Integer pageNum){
		
		// 리턴 값 ( 여러개의 BoardView )
		List<BoardView> boardViewList = new ArrayList<>();
		
		// 글 목록 전부 가져온다 
		if (pageNum == null) {
			pageNum = 1;
		}
		int startPageNum = (pageNum - 1) * POST_MAX_SIZE;
		List<Board> boardList = boardMapper.selectBoardByTypeByPageNum(type, startPageNum, POST_MAX_SIZE);
		
		for (Board board : boardList) {
			BoardView boardView = new BoardView();
			
			// 글 세팅
			boardView.setBoard(board);
			
			// 사용자 정보 
			User user = userBO.getUserById(board.getUserId());
			boardView.setUser(user);
			
			// 댓글 세팅
			List<CommentView> commentViewList = commentBO.generateCommentViewList(board.getId());
			boardView.setCommentList(commentViewList);
			
			boardViewList.add(boardView);
		}
		
		return boardViewList;
	}
	
	// 글 1개
	public BoardView generateBoard(int boardId) {
		BoardView boardView = new BoardView();
		
		Board board = boardMapper.selectBoardById(boardId);
		boardView.setBoard(board);
		
		User user = userBO.getUserById(board.getUserId());
		boardView.setUser(user);
		
		List<CommentView> commentViewList = commentBO.generateCommentViewList(board.getId());
		boardView.setCommentList(commentViewList);
		
		return boardView;
	}
	
	
}

















