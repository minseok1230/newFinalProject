package com.soccer.board.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.soccer.board.domain.Board;

class BoardBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardBO boardBO;
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void 게시물조회() {
		Board board = boardBO.getBoardById(1);
		logger.info("##### user: {}), user");
		assertNotNull(board);
		
	}

}
