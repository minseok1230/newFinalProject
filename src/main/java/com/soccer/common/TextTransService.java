package com.soccer.common;

import org.springframework.stereotype.Component;

import com.ibm.icu.text.Transliterator;

@Component
// 텍스트 변환 
public class TextTransService {

	public  String convertToEnglish(String koreanText) { 
		Transliterator transliterator = Transliterator.getInstance("Any-Latin");
		return transliterator.transliterate(koreanText);
		
	}
}
