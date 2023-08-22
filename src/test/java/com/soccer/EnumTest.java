package com.soccer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.soccer.test.Status;

public class EnumTest {

	Status getStatus() {
		return Status.Y;
	}
	
	@Test
	void Status테스트() {
		// given - 준비 
		
		/* converter를 구현해보자 */
		Status status = getStatus(); // Y
		
		// when - 실행
		int v1 = status.getValue1();
		boolean v2 = status.isValue2(); // 필드의 타입이 boolean이면 get이 아닌 is
		
		// then - 검증
		assertEquals(v1, 1);
		assertEquals(v2, true);
		assertEquals(status, Status.Y);
	}
}
