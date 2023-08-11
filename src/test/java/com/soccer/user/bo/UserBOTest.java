package com.soccer.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.board.bo.BoardBO;
import com.soccer.board.domain.Board;


@SpringBootTest
class UserBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserBO userBO;
	
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	
	
	@Transactional // insert를 하지만 Rollback을 한다.
	//@Test
	void 회원추가테스트() {
		userBO.addUser("aaaaaa@naver.com", "123123123123", "name", "phoneNumber", "19960311", false, "이메일", "CAM");
		
		
	}

}
