package com.soccer;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soccer.common.EncryptUtils;

import lombok.ToString;

public class 람다테스트 {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	//@Test
	// b로 시작하는 과일명 출력 
	void test1() {
		List<String> list = List.of("apple", "banana", "grape");
		list
		.stream()
		.filter(element -> element.startsWith("b"))
		.forEach(element -> logger.info(element));
	}
	
	//@Test
	// 리스트의 모든 요소들 대분자로 변경
	void test2() {
		List<String> list = List.of("apple", "banana", "grape");
		list = list
		.stream()
		.map(fruit -> fruit.toUpperCase())
		.collect(Collectors.toList()); // stream to list
		
		logger.info(list.toString());
	}
	
	
	// 메소드 레퍼런스 - 리스트의 모든 요소들 대분자로 변경
	//@Test
	void test3() {
		List<String> list = List.of("apple", "banana", "grape");
		list = list
		.stream()
		.map(String::toUpperCase) // fruit -> fruit.toUpperCase()
		.collect(Collectors.toList()); // stream to list
		
		logger.info(list.toString());
	}
	
	//@Test
	void test4() {
		List<Person> people = List.of(
				new Person("신보람", 30),
				new Person("최민석", 26));
		
		// 객체 안에 있는 메소드 호출 
		// people.forEach(p -> p.print()); //lambda
		// people.forEach(Person::print);  // method reference
		
		// 객체를 println으로 출력하기 
		people.forEach(p -> System.out.println(p));  //lambda
		people.forEach(System.out::println);  // method reference
	}
	
	//@ToString
	class Person {
		private String name;
		private int age;
		
		Person(String name, int age){
			this.name = name;
			this.age = age;
		}
		
		void print() {
			System.out.println(this);
		}
	}
	
	@Test
	void aaa() {
		String aaa = EncryptUtils.sha256("123123");
		System.out.println(aaa);
	}
	
	
}















