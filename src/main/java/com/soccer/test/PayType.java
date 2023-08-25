package com.soccer.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public enum PayType {
	//열거형 정의
	CASH("현금", List.of("REMIITANCE", "ACCOUNT_TRANSFER"))
	, CARD("카드", List.of("CREDIT", "KAKAO", "NAVER"))
	, EMPTY("없음", Collections.emptyList());
	
	// 필드
	private String title;
	private List<String> payList;
	
	// 생성자
	PayType(String title, List<String> payList){
		this.title = title;
		this.payList = payList;
	}
	
	// 결제 수단(예: 계좌이체)이 enum에 존재하는지 확인
	boolean hasPayMethhod(String payMethod) {
		return payList
		.stream()
		.anyMatch(pay -> pay.equals(payMethod));
	}
	
	// String으로 enum 상수(부모그룹)를 찾기 
	public static PayType findByPatMethod(String payMethod) {
		return Arrays
				.stream(PayType.values()) // PayType의 열거형 변수들을 stream으로 변환
				.filter(payType-> payType.hasPayMethhod(payMethod))
				.findAny() // 찾은 요소 반환
				.orElse(EMPTY); // 없으면 PayType.EMPTY
	}
}















